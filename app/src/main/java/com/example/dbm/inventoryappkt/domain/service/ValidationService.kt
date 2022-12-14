package com.example.dbm.inventoryappkt.domain.service

import com.example.dbm.inventoryappkt.R
import com.example.dbm.inventoryappkt.domain.model.ProductDomain
import com.example.dbm.inventoryappkt.domain.util.ProductValidationResult
import com.example.dbm.inventoryappkt.presentation.state.ProductInputState
import com.example.dbm.inventoryappkt.util.StringWrapper
import java.util.*
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
        val stockStatusValidationResult = isStockStatusValid(inputState.productStockStatus)
        val typeValidationResult = isTypeValid(inputState.productType)
        val imageUrlStorageLocationValidationResult = isImageUriValid(inputState.productImageUriInDeviceString)

        if(!imageUrlStorageLocationValidationResult.first) {
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = imageUrlStorageLocationValidationResult.first,
                errorMessage = imageUrlStorageLocationValidationResult.second
            )
        }

        if(!nameValidationResult.first){
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = nameValidationResult.first,
                errorMessage = nameValidationResult.second
            )
        }

        if(!priceValidationResult.first){
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = priceValidationResult.first,
                errorMessage = priceValidationResult.second
            )
        }

        if(!quantityValidationResult.first) {
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = quantityValidationResult.first,
                errorMessage = quantityValidationResult.second
            )
        }

        if(!brandValidationResult.first) {
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = brandValidationResult.first,
                errorMessage = brandValidationResult.second
            )
        }

        if(!weightValidationResult.first){
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = weightValidationResult.first,
                errorMessage = weightValidationResult.second
            )
        }

        if(!typeValidationResult.first){
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = typeValidationResult.first,
                errorMessage = typeValidationResult.second
            )
        }

        if(!stockStatusValidationResult.first){
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = stockStatusValidationResult.first,
                errorMessage = stockStatusValidationResult.second
            )
        }

        if(!warrantyValidationResult.first) {
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = warrantyValidationResult.first,
                errorMessage = warrantyValidationResult.second
            )
        }

        if(!manufactureYearValidationResult.first){
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = manufactureYearValidationResult.first,
                errorMessage = manufactureYearValidationResult.second
            )
        }

        val productDomain = ProductDomain(
            brand = inputState.productBrand.trim(),
            warranty = inputState.productWarranty.trim().toInt(),
            manufactureYear = inputState.productManufactureYear.trim().toInt(),
            weight = inputState.productWeight.trim().toDouble(),
            price = inputState.productPrice.trim().toDouble(),
            quantity = inputState.productQuantity.trim().toInt(),
            inStock = inputState.productStockStatus == "in_stock",
            name = inputState.productName.trim(),
            type = inputState.productType,
            imageUrl = "",
            imageUriInDeviceString = inputState.productImageUriInDeviceString,
            isDummyProduct = false
        )

        return ProductValidationResult(
            productDomain = productDomain,
            validationSuccessful = true,
            errorMessage = null)
    }

    private fun isImageUriValid(productImageUri: String): Pair<Boolean, StringWrapper?>{
        if (productImageUri.isEmpty()) {
            return Pair(
                false,
                StringWrapper.ResourceStringWrapper(id = R.string.image_uri_empty_error_message)
            )
        }

        return Pair(true, null)
    }

    private fun isTypeValid(productType: String): Pair<Boolean, StringWrapper?> {
        if (productType.isEmpty()) {
            return Pair(
                false,
                StringWrapper.ResourceStringWrapper(id = R.string.type_empty_error_message)
            )
        }

        return Pair(true, null)
    }

    private fun isStockStatusValid(productStockStatus: String): Pair<Boolean, StringWrapper?> {

        if (productStockStatus.isEmpty()) {
            return Pair(
                false,
                StringWrapper.ResourceStringWrapper(id = R.string.stock_status_empty_error_message)
            )
        }

        return Pair(true, null)
    }

    private fun isQuantityValid(productQuantity: String): Pair<Boolean, StringWrapper?> {

        val quantity = productQuantity.trim()

        try {
            quantity.toInt()
        } catch (e: NumberFormatException) {
            return Pair(
                false,
                StringWrapper.ResourceStringWrapper(id = R.string.quantity_format_error_message)
            )
        }

        if(quantity.toInt() <= 0){
            return Pair(
                false,
                StringWrapper.ResourceStringWrapper(id = R.string.quantity_negative_number_error_message)
            )
        }

        return Pair(true, null)
    }

    private fun isWeightValid(productWeight: String): Pair<Boolean, StringWrapper?> {

        val weight = productWeight.trim()

        try {
            weight.toDouble()
        } catch (e: NumberFormatException) {
            return Pair(
                false,
                StringWrapper.ResourceStringWrapper(id = R.string.weight_format_error_message)
            )
        }

        if(weight.toDouble() <= 0.0){
            return Pair(
                false,
                StringWrapper.ResourceStringWrapper(id = R.string.weight_negative_number_error_message)
            )
        }

        return Pair(true, null)
    }

    private fun isWarrantyValid(productWarranty: String): Pair<Boolean, StringWrapper?> {

        val warranty = productWarranty.trim()

        try {
            warranty.toInt()
        } catch (e: NumberFormatException) {
            return Pair(
                false,
                StringWrapper.ResourceStringWrapper(id = R.string.warranty_format_error_message)
            )
        }

        if(warranty.toInt() <= 0){
            return Pair(
                false,
                StringWrapper.ResourceStringWrapper(id = R.string.warranty_negative_number_error_message)
            )
        }

        return Pair(true, null)
    }

    private fun isBrandValid(productBrand: String): Pair<Boolean, StringWrapper?> {

        val brand = productBrand.trim()

        if (brand.isEmpty()) {
            return Pair(
                false,
                StringWrapper.ResourceStringWrapper(id = R.string.brand_empty_error_message)
            )
        }

        if (brand.length < 3) {
            return Pair(
                false,
                StringWrapper.ResourceStringWrapper(id = R.string.brand_not_enough_characters_error_message)
            )
        }

        return Pair(true, null)
    }

    private fun isPriceValid(productPrice: String): Pair<Boolean, StringWrapper?> {

        val price = productPrice.trim()

        try {
            price.toDouble()
        } catch (e: NumberFormatException) {
            return Pair(
                false,
                StringWrapper.ResourceStringWrapper(id = R.string.price_format_error_message)
            )
        }

        if(price.toDouble() <= 0.0){
            return Pair(
                false,
                StringWrapper.ResourceStringWrapper(id = R.string.price_negative_number_error_message)
            )
        }

        return Pair(true, null)
    }

    private fun isManufactureYearValid(productManufactureYear: String): Pair<Boolean, StringWrapper?> {

        val manufactureYear = productManufactureYear.trim()

        try {
            manufactureYear.toInt()
        } catch (e: NumberFormatException) {
            return Pair(
                false,
                StringWrapper.ResourceStringWrapper(id = R.string.manufacture_year_format_error_message)
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
                StringWrapper.ResourceStringWrapper(id = R.string.manufacture_year_number_digits_error_message)
            )
        }

        if(manufactureYear.toInt() < 1900 || manufactureYear.toInt() > Calendar.getInstance().get(Calendar.YEAR)){
            return Pair(
                false,
                StringWrapper.ResourceStringWrapper(id = R.string.manufacture_year_range_error_message,
                    arrayOf(Calendar.getInstance().get(Calendar.YEAR))
                )
            )
        }

        return Pair(true, null)
    }

    private fun isNameInValid(productName: String): Pair<Boolean, StringWrapper?> {

        val name = productName.trim()

        if (name.isEmpty()) {
            return Pair(
                false,
                StringWrapper.ResourceStringWrapper(id = R.string.name_empty_error_message)
            )
        }

        if (name.length < 3) {
            return Pair(
                false,
                StringWrapper.ResourceStringWrapper(id = R.string.name_not_enough_characters_error_message)
            )
        }

        return Pair(true, null)
    }

}