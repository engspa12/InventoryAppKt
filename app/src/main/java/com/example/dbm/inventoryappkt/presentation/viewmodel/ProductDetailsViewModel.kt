package com.example.dbm.inventoryappkt.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbm.inventoryappkt.R
import com.example.dbm.inventoryappkt.di.DispatchersModule
import com.example.dbm.inventoryappkt.domain.service.IProductsService
import com.example.dbm.inventoryappkt.presentation.state.ProductDetailsState
import com.example.dbm.inventoryappkt.util.StringWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val productsService: IProductsService,
    @DispatchersModule.MainDispatcher private val mainDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _uiState = MutableStateFlow<ProductDetailsState>(ProductDetailsState.Loading(StringWrapper.SimpleString("")))
    val uiState: StateFlow<ProductDetailsState> = _uiState

    fun getProductDetails(productId: Int) {
        showProgressBar(R.string.loading_product_details)
        viewModelScope.launch(mainDispatcher) {
            val productDetails = productsService.getProductDetails(productId)
            _uiState.value = ProductDetailsState.Success(productDetails)
        }
    }

    fun addProduct(productId: Int) {
        showProgressBar(R.string.loading_adding_product)
        viewModelScope.launch(mainDispatcher) {
            productsService.addProduct(productId)
        }
    }

    fun deleteProduct(productId: Int){
        showProgressBar(R.string.loading_deleting_product)
        viewModelScope.launch(mainDispatcher) {
            productsService.deleteProduct(productId)
        }
    }

    fun updateProduct(productId: Int){
        showProgressBar(R.string.loading_updating_product)
        viewModelScope.launch(mainDispatcher) {
            productsService.updateProduct(productId)
        }
    }

    private fun showProgressBar(resourceId: Int){
        _uiState.value = ProductDetailsState.Loading(StringWrapper.ResourceString(id = resourceId))
    }

}