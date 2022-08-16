package com.example.dbm.inventoryappkt.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbm.inventoryappkt.R
import com.example.dbm.inventoryappkt.di.DispatchersModule
import com.example.dbm.inventoryappkt.domain.service.IProductsService
import com.example.dbm.inventoryappkt.domain.service.IUserService
import com.example.dbm.inventoryappkt.domain.service.IValidationService
import com.example.dbm.inventoryappkt.presentation.state.MainState
import com.example.dbm.inventoryappkt.presentation.util.ProductDetailsChangeEvent
import com.example.dbm.inventoryappkt.presentation.state.ProductInputState
import com.example.dbm.inventoryappkt.presentation.util.ValidationEvent
import com.example.dbm.inventoryappkt.util.ResultWrapper
import com.example.dbm.inventoryappkt.util.StringWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNewProductViewModel @Inject constructor(
    private val productsService: IProductsService,
    private val validationService: IValidationService,
    private val userService: IUserService,
    @DispatchersModule.MainDispatcher private val mainDispatcher: CoroutineDispatcher
): ViewModel() {

    var uiState by mutableStateOf(ProductInputState())
        private set

    private val _progressBar = MutableStateFlow<StringWrapper?>(null)
    val progressBar: StateFlow<StringWrapper?> = _progressBar

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
                uiState = uiState.copy(productImageUriInDeviceString = event.uri)
            }
        }
    }

    private fun showProgressBar(){
        _progressBar.value = StringWrapper.ResourceStringWrapper(id = R.string.loading_adding_product)
    }

    private fun hideProgressBar(){
        _progressBar.value = null
    }

    fun addNewProduct() {
        val result = validationService.isValidProduct(uiState)
        if (result.validationSuccessful){
            if(result.productDomain != null){

                showProgressBar()

                viewModelScope.launch(mainDispatcher) {
                    userService.getUserId().collect { userId ->
                        if(userId != null && userId != ""){
                            when(val resultAddition = productsService.addProduct(result.productDomain)) {
                                is ResultWrapper.Success -> {
                                    validationEventChannel.send(ValidationEvent.Success)
                                }
                                is ResultWrapper.Failure -> {
                                    validationEventChannel.send(ValidationEvent.Failure(errorMessage = resultAddition.errorMessage))
                                }
                            }
                        } else {
                            validationEventChannel.send(ValidationEvent.Failure(StringWrapper.ResourceStringWrapper(id = R.string.user_not_authenticated)))
                        }

                        hideProgressBar()
                    }
                }
            }
        } else {
            viewModelScope.launch(mainDispatcher) {
                validationEventChannel.send(ValidationEvent.Failure(result.errorMessage))
            }
        }
    }


}