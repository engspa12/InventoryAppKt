package com.example.dbm.inventoryappkt.presentation.state

import com.example.dbm.inventoryappkt.R
import com.example.dbm.inventoryappkt.util.StringWrapper

data class ProductInputState(
    var productName: String = "",
    var productPrice: String = "",
    var productManufactureYear: String = "",
    var productBrand: String = "",
    var productQuantity: String = "",
    var productWeight: String = "",
    var productTypeText: StringWrapper = StringWrapper.SimpleStringWrapper(""),
    var productType: String = "",
    var productStockStatusText: StringWrapper = StringWrapper.SimpleStringWrapper(""),
    var productStockStatus: String = "",
    var productWarranty: String = "",
    val categoryOptions: Map<String, StringWrapper> = mapOf(
        "sports" to StringWrapper.ResourceStringWrapper(id = R.string.sports_category),
        "technology" to StringWrapper.ResourceStringWrapper(id = R.string.technology_category),
        "furniture" to StringWrapper.ResourceStringWrapper(id = R.string.furniture_category),
        "clothing" to StringWrapper.ResourceStringWrapper(id = R.string.clothing_category),
        "other" to StringWrapper.ResourceStringWrapper(id = R.string.other_category),
    ),
    val stockStatusOptions: Map<String, StringWrapper> = mapOf(
        "in_stock" to StringWrapper.ResourceStringWrapper(id = R.string.in_stock_status),
        "without_stock" to StringWrapper.ResourceStringWrapper(id = R.string.without_stock_status)
    )
)