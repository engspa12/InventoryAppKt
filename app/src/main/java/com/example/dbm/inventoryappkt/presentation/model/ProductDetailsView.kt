package com.example.dbm.inventoryappkt.presentation.model

import com.example.dbm.inventoryappkt.presentation.util.ProductType
import com.example.dbm.inventoryappkt.presentation.util.StockType

data class ProductDetailsView(
    val productId: Int,
    val productBrand: String,
    val productWarranty: Int,
    val productManufactureYear: Int,
    val productWeight: Double,
    val productPrice: Double,
    val productQuantity: Int,
    val productInStock: StockType,
    val productName: String,
    val productType: ProductType,
    val productImageUrl: String,
    val isDummyProduct: Boolean
)