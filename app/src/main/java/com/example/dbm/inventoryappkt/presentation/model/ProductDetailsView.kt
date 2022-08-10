package com.example.dbm.inventoryappkt.presentation.model

data class ProductDetailsView(
    val productId: Int,
    val productBrand: String,
    val productWarranty: Int,
    val productManufactureYear: Int,
    val productWeight: Double,
    val productPrice: Double,
    val productQuantity: Int,
    val productInStock: Boolean,
    val productName: String,
    val productType: String,
    val productImageUrl: String,
    val isDummyProduct: Boolean
)