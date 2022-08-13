package com.example.dbm.inventoryappkt.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbm.inventoryappkt.di.DispatchersModule
import com.example.dbm.inventoryappkt.domain.service.IProductsService
import com.example.dbm.inventoryappkt.domain.service.IValidationService
import com.example.dbm.inventoryappkt.presentation.util.ProductDetailsChangeEvent
import com.example.dbm.inventoryappkt.presentation.state.ProductInputState
import com.example.dbm.inventoryappkt.presentation.util.ValidationEvent
import com.example.dbm.inventoryappkt.util.StringWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNewProductViewModel @Inject constructor(
    private val productsService: IProductsService,
    private val validationService: IValidationService,
    @DispatchersModule.MainDispatcher private val mainDispatcher: CoroutineDispatcher
): ViewModel() {

    var uiState by mutableStateOf(ProductInputState())
        private set

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvent = validationEventChannel.receiveAsFlow()

    fun onEvent(event: ProductDetailsChangeEvent) {
        when(event) {
            is ProductDetailsChangeEvent.NameChanged -> {
                uiState = uiState.copy(productName = event.name)
            }
            is ProductDetailsChangeEvent.BrandChanged -> {
                uiState = uiState.copy(productBrand = event.brand)
            }
            is ProductDetailsChangeEvent.ManufactureYearChanged -> {
                uiState = uiState.copy(productManufactureYear = event.manufactureYear)
            }
            is ProductDetailsChangeEvent.PriceChanged -> {
                uiState = uiState.copy(productPrice = event.price)
            }
            is ProductDetailsChangeEvent.QuantityChanged -> {
                uiState = uiState.copy(productQuantity = event.quantity)
            }
            is ProductDetailsChangeEvent.StockStatusChanged -> {
                uiState = uiState.copy(productStockStatus = event.stockStatus.key, productStockStatusText = event.stockStatus.value)
            }
            is ProductDetailsChangeEvent.TypeChanged -> {
                uiState = uiState.copy(productType = event.type.key, productTypeText = event.type.value)
            }
            is ProductDetailsChangeEvent.WarrantyChanged -> {
                uiState = uiState.copy(productWarranty = event.warranty)
            }
            is ProductDetailsChangeEvent.WeightChanged -> {
                uiState = uiState.copy(productWeight = event.weight)
            }
            is ProductDetailsChangeEvent.ImageSelectedChanged -> {
                uiState = uiState.copy(productImageUri = event.uri)
            }
        }
    }

    fun addNewProduct() {
        val result = validationService.isValidProduct(uiState)
        if (result.validationSuccessful){
            if(result.productDomain != null){
                viewModelScope.launch(mainDispatcher) {
                    when(productsService.addProduct(result.productDomain)) {
                        "Success" -> {
                            validationEventChannel.send(ValidationEvent.Success)
                        }
                        else -> {
                            validationEventChannel.send(ValidationEvent.Failure(errorMessage = result.errorMessage))
                        }
                    }
                    //println(result.productDomain)
                    //validationEventChannel.send(ValidationEvent.Success)
                }
            }
        } else {
            viewModelScope.launch(mainDispatcher) {
                validationEventChannel.send(ValidationEvent.Failure(result.errorMessage))
            }
        }
    }


}