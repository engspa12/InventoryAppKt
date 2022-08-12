package com.example.dbm.inventoryappkt.domain.util

import com.example.dbm.inventoryappkt.domain.model.ProductDomain
import com.example.dbm.inventoryappkt.util.StringWrapper

data class ProductValidationResult(
    val productDomain: ProductDomain? = null,
    val validationSuccessful: Boolean,
    val errorMessage: StringWrapper? = null
)
