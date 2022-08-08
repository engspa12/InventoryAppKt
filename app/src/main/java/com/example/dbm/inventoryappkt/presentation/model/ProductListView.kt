package com.example.dbm.inventoryappkt.presentation.model

data class ProductListView(
    val productId: Int,
    val productName: String,
    val productQuantity: Int,
    val productPrice: Double,
    val productUrlImage: String,
    val isDummyProduct: Boolean
)