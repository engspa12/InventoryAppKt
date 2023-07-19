package com.example.dbm.inventoryappkt.presentation.state

import com.example.dbm.inventoryappkt.R
import com.example.dbm.inventoryappkt.presentation.util.ProductType
import com.example.dbm.inventoryappkt.presentation.util.StockType

data class ProductInputState(
    var productName: String = "",
    var productPrice: String = "",
    var productManufactureYear: String = "",
    var productBrand: String = "",
    var productQuantity: String = "",
    var productWeight: String = "",
    var productType: ProductType = ProductType.OTHER,
    var productStockStatus: StockType = StockType.WITHOUT_STOCK,
    var productWarranty: String = "",
    var productImageUriInDeviceString: String = "",
    val categoryOptions: Map<String, Int> = mapOf(
        "sports" to R.string.sports_category,
        "technology" to R.string.technology_category,
        "furniture" to R.string.furniture_category,
        "clothing" to R.string.clothing_category,
        "other" to R.string.other_category,
    ),
    val stockStatusOptions: Map<String, Int> = mapOf(
        "in_stock" to R.string.in_stock_status,
        "without_stock" to R.string.without_stock_status
    )
)