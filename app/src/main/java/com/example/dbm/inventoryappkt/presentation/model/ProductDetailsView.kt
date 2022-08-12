package com.example.dbm.inventoryappkt.presentation.model

import com.example.dbm.inventoryappkt.util.StringWrapper

data class ProductDetailsView(
    val productId: Int,
    val productBrand: String,
    val productWarranty: Int,
    val productManufactureYear: Int,
    val productWeight: Double,
    val productPrice: Double,
    val productQuantity: Int,
    val productInStock: StringWrapper,
    val productName: String,
    val productType: StringWrapper,
    val productImageUrl: String,
    val isDummyProduct: Boolean
)