package com.example.dbm.inventoryappkt.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbm.inventoryappkt.R
import com.example.dbm.inventoryappkt.di.DispatchersModule
import com.example.dbm.inventoryappkt.domain.service.IProductsService
import com.example.dbm.inventoryappkt.presentation.state.MainState
import com.example.dbm.inventoryappkt.util.StringWrapper
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
class MainViewModel @Inject constructor(
    private val productsService: IProductsService,
    @DispatchersModule.MainDispatcher private val mainDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _uiState = MutableStateFlow<MainState>(MainState.Loading(StringWrapper.ResourceStringWrapper(id = R.string.loading_products)))
    val uiState: StateFlow<MainState> = _uiState

    private val _listIsFull = Channel<Boolean>()
    val listIsFulls = _listIsFull.receiveAsFlow()

    fun getProducts() {
        viewModelScope.launch(mainDispatcher) {
            val products = productsService.getProducts()
            _uiState.value = MainState.Success(products)
        }
    }

    private fun showProgressBar(resourceId: Int){
        _uiState.value = MainState.Loading(StringWrapper.ResourceStringWrapper(id = resourceId))
    }

    fun updateProductQuantity(productId: Int){

        viewModelScope.launch(mainDispatcher) {
            productsService.reduceProductQuantity(productId)
            getProducts()
        }
    }

    fun deleteProduct(productId: Int) {

        showProgressBar(R.string.loading_deleting_product)

        viewModelScope.launch(mainDispatcher) {
            productsService.deleteProduct(productId)
            delay(600L)
            getProducts()
        }
    }

    fun insertDummyProduct() {

        showProgressBar(R.string.loading_adding_product)

        viewModelScope.launch(mainDispatcher) {
            productsService.insertDummyProduct()
            delay(600L)
            _listIsFull.send(true)
            getProducts()
        }
    }
}