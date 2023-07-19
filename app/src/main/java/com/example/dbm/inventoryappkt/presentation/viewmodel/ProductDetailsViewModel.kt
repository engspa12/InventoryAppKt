package com.example.dbm.inventoryappkt.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbm.inventoryappkt.di.DispatchersModule
import com.example.dbm.inventoryappkt.domain.service.IProductsService
import com.example.dbm.inventoryappkt.domain.service.IUserService
import com.example.dbm.inventoryappkt.domain.util.ProductDomainError
import com.example.dbm.inventoryappkt.domain.util.ProductModification
import com.example.dbm.inventoryappkt.presentation.state.ProductDetailsState
import com.example.dbm.inventoryappkt.presentation.util.ProductDetailsActionEvent
import com.example.dbm.inventoryappkt.util.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val productsService: IProductsService,
    private val userService: IUserService,
    @DispatchersModule.MainDispatcher private val mainDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _uiState = MutableStateFlow<ProductDetailsState>(ProductDetailsState.LoadingProductDetails)
    val uiState: StateFlow<ProductDetailsState> = _uiState

    private val _productDetailsActionEvent = Channel<ProductDetailsActionEvent>()
    val productActionEvent = _productDetailsActionEvent.receiveAsFlow()

    fun getProductDetails(productId: Int) {
        viewModelScope.launch(mainDispatcher) {
            val productDetails = productsService.getProductDetails(productId)
            _uiState.value = ProductDetailsState.Success(productDetails)
        }
    }

    fun deleteProduct(productId: Int){
        showProgressBar(isProductUpdate = false)
        viewModelScope.launch(mainDispatcher) {
            userService.getUserId().collect { userId ->
                if(!userId.isNullOrEmpty()) {
                    when(val resultDeletion = productsService.deleteProduct(productId)){
                        is ResultWrapper.Success -> {
                            _productDetailsActionEvent.send(ProductDetailsActionEvent.ProductDetailsDeleted)
                        }
                        is ResultWrapper.Failure -> {
                            processDomainError(resultDeletion.error)
                            delay(200L)
                            getProductDetails(productId)
                        }
                    }
                } else {
                    _productDetailsActionEvent.send(ProductDetailsActionEvent.NoAuthenticatedError)
                }
            }
        }
    }

    private suspend fun processDomainError(error: ProductDomainError?) {
        _productDetailsActionEvent.send(
            when(error){
                ProductDomainError.DELETING_FROM_STORAGE_SERVICE -> ProductDetailsActionEvent.DeletingFromStorageError
                ProductDomainError.GENERIC -> ProductDetailsActionEvent.GenericError
                ProductDomainError.NO_INTERNET_CONNECTION -> ProductDetailsActionEvent.NoConnectionError
                else -> ProductDetailsActionEvent.GenericError
            }
        )
    }

    fun updateProduct(productId: Int){
        showProgressBar(isProductUpdate = true)
        viewModelScope.launch(mainDispatcher) {
            productsService.saveModifiedProduct(productId)
            delay(1000L)
            _productDetailsActionEvent.send(ProductDetailsActionEvent.ProductDetailsUpdated)
        }
    }

    private fun showProgressBar(isProductUpdate: Boolean){
        _uiState.value = if(isProductUpdate) ProductDetailsState.LoadingUpdating else ProductDetailsState.LoadingRemoving
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