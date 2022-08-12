package com.example.dbm.inventoryappkt.domain.service

import com.example.dbm.inventoryappkt.R
import com.example.dbm.inventoryappkt.domain.model.ProductDomain
import com.example.dbm.inventoryappkt.domain.util.ProductValidationResult
import com.example.dbm.inventoryappkt.presentation.state.ProductInputState
import com.example.dbm.inventoryappkt.util.StringWrapper
import java.util.*
import javax.inject.Inject

interface IValidationService {
    fun isValidProduct(product: ProductInputState): ProductValidationResult
    /*fun isValidName(): ProductValidationResult
    fun isValidBrand(): ProductValidationResult
    fun isValidQuantity(): ProductValidationResult
    fun isValidWeight(): ProductValidationResult
    fun isValidManufactureYear(): ProductValidationResult
    fun isValidPrice(): ProductValidationResult
    fun isValidProductType(): ProductValidationResult
    fun isValidStockStatus(): ProductValidationResult
    fun isValidWarranty(): ProductValidationResult*/
}

class ValidationService @Inject constructor() : IValidationService {

    override fun isValidProduct(product: ProductInputState): ProductValidationResult {

        val name = product.productName
        val brand = product.productBrand
        val price: Double
        val manufactureYear: Int
        val warranty: Int
        val weight: Double
        val quantity: Int
        val inStock: Boolean
        val type: String = product.productType
        val imageUrl: String
        val imageUrlStorageLocation: String
        val isDummyProduct: Boolean


        if (name.isEmpty()) {
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = false,
                errorMessage = StringWrapper.ResourceStringWrapper(id = R.string.name_empty_error_message)
            )
        }

        if (name.length < 3) {
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = false,
                errorMessage = StringWrapper.ResourceStringWrapper(id = R.string.name_not_enough_characters_error_message)
            )
        }

        try {
            price = product.productPrice.toDouble()
            if(price <= 0.0){
                return ProductValidationResult(
                    productDomain = null,
                    validationSuccessful = false,
                    errorMessage = StringWrapper.ResourceStringWrapper(id = R.string.price_format_error_message)
                )
            }
        } catch (e: NumberFormatException) {
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = false,
                errorMessage = StringWrapper.ResourceStringWrapper(id = R.string.price_format_error_message)
            )
        }

        try {
            manufactureYear = product.productManufactureYear.toInt()
            if(!isManufactureYearValid(manufactureYear)){
                return ProductValidationResult(
                    productDomain = null,
                    validationSuccessful = false,
                    errorMessage = StringWrapper.ResourceStringWrapper(id = R.string.manufacture_year_format_error_message)
                )
            }
        } catch (e: NumberFormatException) {
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = false,
                errorMessage = StringWrapper.ResourceStringWrapper(id = R.string.manufacture_year_format_error_message)
            )
        }

        try {
            quantity = product.productQuantity.toInt()
            if(quantity <= 0){
                return ProductValidationResult(
                    productDomain = null,
                    validationSuccessful = false,
                    errorMessage = StringWrapper.ResourceStringWrapper(id = R.string.quantity_format_error_message)
                )
            }
        } catch (e: NumberFormatException) {
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = false,
                errorMessage = StringWrapper.ResourceStringWrapper(id = R.string.quantity_format_error_message)
            )
        }

        if (brand.isEmpty()) {
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = false,
                errorMessage = StringWrapper.ResourceStringWrapper(id = R.string.brand_empty_error_message)
            )
        }

        if (brand.length < 3) {
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = false,
                errorMessage = StringWrapper.ResourceStringWrapper(id = R.string.brand_not_enough_characters_error_message)
            )
        }

        try {
            weight = product.productWeight.toDouble()
            if(weight <= 0.0){
                return ProductValidationResult(
                    productDomain = null,
                    validationSuccessful = false,
                    errorMessage = StringWrapper.ResourceStringWrapper(id = R.string.weight_format_error_message)
                )
            }
        } catch (e: NumberFormatException) {
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = false,
                errorMessage = StringWrapper.ResourceStringWrapper(id = R.string.weight_format_error_message)
            )
        }

        if (type.isEmpty()) {
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = false,
                errorMessage = StringWrapper.ResourceStringWrapper(id = R.string.type_empty_error_message)
            )
        }

        if (product.productStockStatus.isEmpty()) {
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = false,
                errorMessage = StringWrapper.ResourceStringWrapper(id = R.string.stock_status_empty_error_message)
            )
        } else {
            inStock = product.productStockStatus == "in_stock"
        }

        try {
            warranty = product.productWarranty.toInt()
            if(warranty <= 0){
                return ProductValidationResult(
                    productDomain = null,
                    validationSuccessful = false,
                    errorMessage = StringWrapper.ResourceStringWrapper(id = R.string.warranty_format_error_message)
                )
            }
        } catch (e: NumberFormatException) {
            return ProductValidationResult(
                productDomain = null,
                validationSuccessful = false,
                errorMessage = StringWrapper.ResourceStringWrapper(id = R.string.warranty_format_error_message)
            )
        }

        val productDomain = ProductDomain(
            brand = name,
            warranty = warranty,
            manufactureYear = manufactureYear,
            weight = weight,
            price = price,
            quantity = quantity,
            inStock = inStock,
            name = name,
            type = type,
            imageUrl = "https://organicthemes.com/demo/profile/files/2018/05/profile-pic.jpg",
            imageUrlStorageLocation = "",
            isDummyProduct = false
        )

        return ProductValidationResult(
            productDomain = productDomain,
            validationSuccessful = true,
            errorMessage = null)
    }

    private fun isManufactureYearValid(inputYear: Int): Boolean {

        var number = inputYear
        var count = 0

        while (number != 0) {
            number /= 10
            ++count
        }

        if(count != 4){
            return false
        }

        if(inputYear < 1900 || inputYear > Calendar.getInstance().get(Calendar.YEAR)){
            return false
        }

        return true
    }

}