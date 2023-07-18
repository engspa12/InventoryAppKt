package com.example.dbm.inventoryappkt.domain.util

import com.example.dbm.inventoryappkt.domain.model.ProductDomain

data class ProductValidationResult(
    val productDomain: ProductDomain? = null,
    val validationSuccessful: Boolean,
    val errorType: ProductValidationError = ProductValidationError.NONE
)
