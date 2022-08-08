package com.example.dbm.inventoryappkt.presentation.model

import com.example.dbm.inventoryappkt.domain.util.toListView

data class ProductDetailsView(
    val productId: Int,
    val productBrand: String,
    val productWarranty: Int,
    val productManufactureYear: String,
    val productWeight: Double,
    val productPrice: Double,
    val productQuantity: Int,
    val productInStock: Boolean,
    val productName: String,
    val productType: Int,
    val productImageUrl: String,
    val isDummyProduct: Boolean
)