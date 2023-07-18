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
import com.example.dbm.inventoryappkt.domain.util.ProductDomainError
import com.example.dbm.inventoryappkt.domain.util.ProductValidationError
import com.example.dbm.inventoryappkt.presentation.util.ProductDetailsChangeEvent
import com.example.dbm.inventoryappkt.presentation.state.ProductInputState
import com.example.dbm.inventoryappkt.presentation.util.ValidationEvent
import com.example.dbm.inventoryappkt.presentation.util.ValidationEventError
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

    private val _progressBarMessage = MutableStateFlow<StringWrapper?>(null)
    val progressBarMessage: StateFlow<StringWrapper?> = _progressBarMessage

    private val _validationEvent = Channel<ValidationEvent>()
    val validationEvent = _validationEvent.receiveAsFlow()

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
        _progressBarMessage.value = StringWrapper.ResourceStringWrapper(id = R.string.loading_adding_product)
    }

    private fun hideProgressBar(){
        _progressBarMessage.value = null
    }

    fun addNewProduct() {
        val result = validationService.isValidProduct(uiState)
        if (result.validationSuccessful){
            if(result.productDomain != null){

                showProgressBar()

                viewModelScope.launch(mainDispatcher) {
                    userService.getUserId().collect { userId ->
                        if(!userId.isNullOrEmpty()){
                            when(val resultAddition = productsService.addProduct(result.productDomain)) {
                                is ResultWrapper.Success -> {
                                    _validationEvent.send(ValidationEvent.Success)
                                }
                                is ResultWrapper.Failure -> {
                                    processDomainError(resultAddition.error)
                                }
                            }
                        } else {
                            _validationEvent.send(ValidationEvent.Failure(errorType = ValidationEventError.USER_NOT_AUTHENTICATED))
                        }

                        hideProgressBar()
                    }
                }
            }
        } else {
            viewModelScope.launch(mainDispatcher) {
                processValidationError(result.errorType)
            }
        }
    }

    private suspend fun processDomainError(error: ProductDomainError?){
        _validationEvent.send(
            ValidationEvent.Failure(errorType = when(error) {
                    ProductDomainError.NO_INTERNET_CONNECTION -> ValidationEventError.NO_INTERNET_CONNECTION
                    ProductDomainError.GENERIC -> ValidationEventError.GENERIC
                    ProductDomainError.UPLOADING_TO_STORAGE_SERVICE -> ValidationEventError.UPLOADING_TO_STORAGE_SERVICE
                    ProductDomainError.DELETING_FROM_STORAGE_SERVICE -> ValidationEventError.DELETING_FROM_STORAGE_SERVICE
                    else -> ValidationEventError.GENERIC
                }
            )
        )
    }

    private suspend fun processValidationError(error: ProductValidationError) {
        _validationEvent.send(
            ValidationEvent.Failure(errorType = when(error) {
                    ProductValidationError.MUST_SELECT_IMAGE_FOR_PRODUCT -> ValidationEventError.MUST_SELECT_IMAGE_FOR_PRODUCT
                    ProductValidationError.TYPE_CANNOT_BE_EMPTY -> ValidationEventError.TYPE_CANNOT_BE_EMPTY
                    ProductValidationError.STOCK_STATUS_CANNOT_BE_EMPTY -> ValidationEventError.STOCK_STATUS_CANNOT_BE_EMPTY
                    ProductValidationError.QUANTITY_MUST_BE_WHOLE_NUMBER -> ValidationEventError.QUANTITY_MUST_BE_WHOLE_NUMBER
                    ProductValidationError.QUANTITY_CANNOT_BE_NEGATIVE -> ValidationEventError.QUANTITY_CANNOT_BE_NEGATIVE
                    ProductValidationError.WEIGHT_MUST_BE_DECIMAL_NUMBER -> ValidationEventError.WEIGHT_MUST_BE_DECIMAL_NUMBER
                    ProductValidationError.WEIGHT_CANNOT_BE_NEGATIVE-> ValidationEventError.WEIGHT_CANNOT_BE_NEGATIVE
                    ProductValidationError.WARRANTY_MUST_BE_WHOLE_NUMBER -> ValidationEventError.WARRANTY_MUST_BE_WHOLE_NUMBER
                    ProductValidationError.WARRANTY_CANNOT_BE_NEGATIVE -> ValidationEventError.WARRANTY_CANNOT_BE_NEGATIVE
                    ProductValidationError.BRAND_CANNOT_BE_EMPTY -> ValidationEventError.BRAND_CANNOT_BE_EMPTY
                    ProductValidationError.BRAND_CANNOT_BE_LESS_THAN_THREE_CHARACTERS -> ValidationEventError.BRAND_CANNOT_BE_LESS_THAN_THREE_CHARACTERS
                    ProductValidationError.PRICE_MUST_BE_DECIMAL_NUMBER -> ValidationEventError.PRICE_MUST_BE_DECIMAL_NUMBER
                    ProductValidationError.PRICE_CANNOT_BE_NEGATIVE -> ValidationEventError.PRICE_CANNOT_BE_NEGATIVE
                    ProductValidationError.MANUFACTURE_YEAR_MUST_BE_WHOLE_NUMBER -> ValidationEventError.MANUFACTURE_YEAR_MUST_BE_WHOLE_NUMBER
                    ProductValidationError.MANUFACTURE_YEAR_MUST_HAVE_FOUR_DIGITS -> ValidationEventError.MANUFACTURE_YEAR_MUST_HAVE_FOUR_DIGITS
                    ProductValidationError.MANUFACTURE_YEAR_OUT_OF_RANGE -> ValidationEventError.MANUFACTURE_YEAR_OUT_OF_RANGE
                    ProductValidationError.NAME_CANNOT_BE_EMPTY -> ValidationEventError.NAME_CANNOT_BE_EMPTY
                    ProductValidationError.NAME_CANNOT_BE_LESS_THAN_THREE_CHARACTERS -> ValidationEventError.NAME_CANNOT_BE_LESS_THAN_THREE_CHARACTERS
                    else -> ValidationEventError.GENERIC
                }
            )
        )
    }


}