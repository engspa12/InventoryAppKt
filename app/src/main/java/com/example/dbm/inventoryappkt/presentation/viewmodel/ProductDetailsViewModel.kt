package com.example.dbm.inventoryappkt.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbm.inventoryappkt.R
import com.example.dbm.inventoryappkt.di.DispatchersModule
import com.example.dbm.inventoryappkt.domain.service.IProductsService
import com.example.dbm.inventoryappkt.domain.service.IUserService
import com.example.dbm.inventoryappkt.domain.util.ProductModification
import com.example.dbm.inventoryappkt.presentation.state.ProductDetailsState
import com.example.dbm.inventoryappkt.presentation.util.ProductActionEvent
import com.example.dbm.inventoryappkt.util.ResultWrapper
import com.example.dbm.inventoryappkt.util.StringWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val productsService: IProductsService,
    private val userService: IUserService,
    @DispatchersModule.MainDispatcher private val mainDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _uiState = MutableStateFlow<ProductDetailsState>(ProductDetailsState.Loading(StringWrapper.ResourceStringWrapper(id = R.string.loading_product_details)))
    val uiState: StateFlow<ProductDetailsState> = _uiState

    private val _productActionEvent = Channel<ProductActionEvent>()
    val productActionEvent = _productActionEvent.receiveAsFlow()

    fun getProductDetails(productId: Int) {
        viewModelScope.launch(mainDispatcher) {
            val productDetails = productsService.getProductDetails(productId)
            _uiState.value = ProductDetailsState.Success(productDetails)
        }
    }

    fun deleteProduct(productId: Int){
        showProgressBar(R.string.loading_deleting_product)
        viewModelScope.launch(mainDispatcher) {
            userService.getUserId().collect { userId ->
                if(userId != null && userId != "") {
                    when(val resultDeletion = productsService.deleteProduct(productId)){
                        is ResultWrapper.Success -> {
                            _productActionEvent.send(ProductActionEvent.ProductDeleted)
                        }
                        is ResultWrapper.Failure -> {
                            _productActionEvent.send(ProductActionEvent.Error(errorMessage = resultDeletion.errorMessage))
                            delay(200L)
                            getProductDetails(productId)
                        }
                    }
                } else {
                    _productActionEvent.send(ProductActionEvent.Error(StringWrapper.ResourceStringWrapper(id = R.string.user_not_authenticated)))
                }
            }
        }
    }

    fun updateProduct(productId: Int){
        showProgressBar(R.string.loading_updating_product)
        viewModelScope.launch(mainDispatcher) {
            productsService.saveModifiedProduct(productId)
            delay(1000L)
            _productActionEvent.send(ProductActionEvent.ProductUpdated)
        }
    }

    private fun showProgressBar(resourceId: Int){
        _uiState.value = ProductDetailsState.Loading(StringWrapper.ResourceStringWrapper(id = resourceId))
    }

    fun increaseProductQuantity() {
        val newProductView = productsService.modifyProductForView(ProductModification.INCREASE_QUANTITY)
        _uiState.value = ProductDetailsState.Success(newProductView)
    }

    fun decreaseProductQuantity() {
        val newProductView = productsService.modifyProductForView(ProductModification.DECREASE_QUANTITY)
        _uiState.value = ProductDetailsState.Success(newProductView)
    }

}