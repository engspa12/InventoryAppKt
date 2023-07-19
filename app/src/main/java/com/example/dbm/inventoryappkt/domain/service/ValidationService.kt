package com.example.dbm.inventoryappkt.domain.service

import com.example.dbm.inventoryappkt.domain.model.ProductDomain
import com.example.dbm.inventoryappkt.domain.util.ProductValidationError
import com.example.dbm.inventoryappkt.domain.util.ProductValidationResult
import com.example.dbm.inventoryappkt.presentation.state.ProductInputState
import com.example.dbm.inventoryappkt.presentation.util.ProductType
import com.example.dbm.inventoryappkt.presentation.util.StockType
import java.util.Calendar
import javax.inject.Inject

interface IValidationService {
    fun isValidProduct(inputState: ProductInputState): ProductValidationResult
}

class ValidationService @Inject constructor() : IValidationService {

    override fun isValidProduct(inputState: ProductInputState): ProductValidationResult {

        val nameValidationResult = isNameInValid(inputState.productName)
        val priceValidationResult = isPriceValid(inputState.productPrice)
        val brandValidationResult = isBrandValid(inputState.productBrand)
        val manufactureYearValidationResult = isManufactureYearValid(inputState.productManufactureYear)
        val warrantyValidationResult = isWarrantyValid(inputState.productWarranty)
        val weightValidationResult = isWeightValid(inputState.productWeight)
        val quantityValidationResult = isQuantityValid(inputState.productQuantity)
        val imageUrlStorageLocationValidationResult = isImageUriValid(inputState.productImageUriInDeviceString)

        if(!imageUrlStorageLocationValidationResult.first) {
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = false,
                errorType = imageUrlStorageLocationValidationResult.second
            )
        }

        if(!nameValidationResult.first){
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = false,
                errorType = nameValidationResult.second
            )
        }

        if(!priceValidationResult.first){
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = false,
                errorType = priceValidationResult.second
            )
        }

        if(!quantityValidationResult.first) {
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = false,
                errorType = quantityValidationResult.second
            )
        }

        if(!brandValidationResult.first) {
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = false,
                errorType = brandValidationResult.second
            )
        }

        if(!weightValidationResult.first){
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = false,
                errorType = weightValidationResult.second
            )
        }

        if(!warrantyValidationResult.first) {
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = false,
                errorType = warrantyValidationResult.second
            )
        }

        if(!manufactureYearValidationResult.first){
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = false,
                errorType = manufactureYearValidationResult.second
            )
        }

        val productDomain = ProductDomain(
            brand = inputState.productBrand.trim(),
            warranty = inputState.productWarranty.trim().toInt(),
            manufactureYear = inputState.productManufactureYear.trim().toInt(),
            weight = inputState.productWeight.trim().toDouble(),
            price = inputState.productPrice.trim().toDouble(),
            quantity = inputState.productQuantity.trim().toInt(),
            inStock = inputState.productStockStatus == StockType.IN_STOCK,
            name = inputState.productName.trim(),
            type = getStringForProductType(inputState.productType),
            imageUrl = "",
            imageUriInDeviceString = inputState.productImageUriInDeviceString,
            isDummyProduct = false
        )

        return ProductValidationResult(
            productDomain = productDomain,
            validationSuccessful = true,
            errorType = ProductValidationError.NONE)
    }

    private fun getStringForProductType(productType: ProductType): String {
        return when(productType) {
            ProductType.SPORTS -> "sports"
            ProductType.FURNITURE -> "furniture"
            ProductType.CLOTHING -> "clothing"
            ProductType.TECHNOLOGY -> "technology"
            else -> "other"
        }
    }

    private fun isImageUriValid(productImageUri: String): Pair<Boolean, ProductValidationError>{
        if (productImageUri.isEmpty()) {
            return Pair(
                false,
                ProductValidationError.MUST_SELECT_IMAGE_FOR_PRODUCT
            )
        }

        return Pair(true, ProductValidationError.NONE)
    }

    private fun isQuantityValid(productQuantity: String): Pair<Boolean, ProductValidationError> {

        val quantity = productQuantity.trim()

        try {
            quantity.toInt()
        } catch (e: NumberFormatException) {
            return Pair(
                false,
                ProductValidationError.QUANTITY_MUST_BE_WHOLE_NUMBER
            )
        }

        if(quantity.toInt() <= 0){
            return Pair(
                false,
                ProductValidationError.QUANTITY_CANNOT_BE_NEGATIVE
            )
        }

        return Pair(true, ProductValidationError.NONE)
    }

    private fun isWeightValid(productWeight: String): Pair<Boolean, ProductValidationError> {

        val weight = productWeight.trim()

        try {
            weight.toDouble()
        } catch (e: NumberFormatException) {
            return Pair(
                false,
                ProductValidationError.WEIGHT_MUST_BE_DECIMAL_NUMBER
            )
        }

        if(weight.toDouble() <= 0.0){
            return Pair(
                false,
                ProductValidationError.WEIGHT_CANNOT_BE_NEGATIVE
            )
        }

        return Pair(true, ProductValidationError.NONE)
    }

    private fun isWarrantyValid(productWarranty: String): Pair<Boolean, ProductValidationError> {

        val warranty = productWarranty.trim()

        try {
            warranty.toInt()
        } catch (e: NumberFormatException) {
            return Pair(
                false,
                ProductValidationError.WARRANTY_MUST_BE_WHOLE_NUMBER
            )
        }

        if(warranty.toInt() <= 0){
            return Pair(
                false,
                ProductValidationError.WARRANTY_CANNOT_BE_NEGATIVE
            )
        }

        return Pair(true, ProductValidationError.NONE)
    }

    private fun isBrandValid(productBrand: String): Pair<Boolean, ProductValidationError> {

        val brand = productBrand.trim()

        if (brand.isEmpty()) {
            return Pair(
                false,
                ProductValidationError.BRAND_CANNOT_BE_EMPTY
            )
        }

        if (brand.length < 3) {
            return Pair(
                false,
                ProductValidationError.BRAND_CANNOT_BE_LESS_THAN_THREE_CHARACTERS
            )
        }

        return Pair(true, ProductValidationError.NONE)
    }

    private fun isPriceValid(productPrice: String): Pair<Boolean, ProductValidationError> {

        val price = productPrice.trim()

        try {
            price.toDouble()
        } catch (e: NumberFormatException) {
            return Pair(
                false,
                ProductValidationError.PRICE_MUST_BE_DECIMAL_NUMBER
            )
        }

        if(price.toDouble() <= 0.0){
            return Pair(
                false,
                ProductValidationError.PRICE_CANNOT_BE_NEGATIVE
            )
        }

        return Pair(true, ProductValidationError.NONE)
    }

    private fun isManufactureYearValid(productManufactureYear: String): Pair<Boolean, ProductValidationError> {

        val manufactureYear = productManufactureYear.trim()

        try {
            manufactureYear.toInt()
        } catch (e: NumberFormatException) {
            return Pair(
                false,
                ProductValidationError.MANUFACTURE_YEAR_MUST_BE_WHOLE_NUMBER
            )
        }

        var year = manufactureYear.toInt()
        var countDigits = 0

        while (year != 0) {
            year /= 10
            ++countDigits
        }

        if(countDigits != 4){
            return Pair(
                false,
                ProductValidationError.MANUFACTURE_YEAR_MUST_HAVE_FOUR_DIGITS
            )
        }

        if(manufactureYear.toInt() < 1900 || manufactureYear.toInt() > Calendar.getInstance().get(Calendar.YEAR)){
            return Pair(
                false,
                ProductValidationError.MANUFACTURE_YEAR_OUT_OF_RANGE
            )
        }

        return Pair(true, ProductValidationError.NONE)
    }

    private fun isNameInValid(productName: String): Pair<Boolean, ProductValidationError> {

        val name = productName.trim()

        if (name.isEmpty()) {
            return Pair(
                false,
                ProductValidationError.NAME_CANNOT_BE_EMPTY
            )
        }

        if (name.length < 3) {
            return Pair(
                false,
                ProductValidationError.NAME_CANNOT_BE_LESS_THAN_THREE_CHARACTERS
            )
        }

        return Pair(true, ProductValidationError.NONE)
    }

}