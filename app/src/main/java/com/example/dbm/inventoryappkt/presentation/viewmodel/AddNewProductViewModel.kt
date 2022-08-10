package com.example.dbm.inventoryappkt.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbm.inventoryappkt.di.DispatchersModule
import com.example.dbm.inventoryappkt.domain.service.IProductsService
import com.example.dbm.inventoryappkt.presentation.util.ProductChangeEvent
import com.example.dbm.inventoryappkt.presentation.state.ProductInputState
import com.example.dbm.inventoryappkt.presentation.util.ValidationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNewProductViewModel @Inject constructor(
    private val productsService: IProductsService,
    @DispatchersModule.MainDispatcher private val mainDispatcher: CoroutineDispatcher
): ViewModel() {

    var uiState by mutableStateOf(ProductInputState())
        private set

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvent = validationEventChannel.receiveAsFlow()

    fun onEvent(event: ProductChangeEvent) {
        when(event) {
            is ProductChangeEvent.NameChanged -> {
                uiState = uiState.copy(productName = event.name)
            }
            is ProductChangeEvent.BrandChanged -> {
                uiState = uiState.copy(productBrand = event.brand)
            }
            is ProductChangeEvent.ManufactureYearChanged -> {
                uiState = uiState.copy(productManufactureYear = event.manufactureYear)
            }
            is ProductChangeEvent.PriceChanged -> {
                uiState = uiState.copy(productPrice = event.price)
            }
            is ProductChangeEvent.QuantityChanged -> {
                uiState = uiState.copy(productQuantity = event.quantity)
            }
            is ProductChangeEvent.StockStatusChanged -> {
                uiState = uiState.copy(productStockStatus = event.stockStatus)
            }
            is ProductChangeEvent.TypeChanged -> {
                uiState = uiState.copy(productType = event.type)
            }
            is ProductChangeEvent.WarrantyChanged -> {
                uiState = uiState.copy(productWarranty = event.warranty)
            }
            is ProductChangeEvent.WeightChanged -> {
                uiState = uiState.copy(productWeight = event.weight)
            }
        }
    }

    fun addNewProduct() {
        viewModelScope.launch(mainDispatcher) {
            when(productsService.addProduct(uiState)) {
                "Success" -> {
                    validationEventChannel.send(ValidationEvent.Success)
                }
                else -> {
                    validationEventChannel.send(ValidationEvent.Failure)
                }
            }
        }
    }


}