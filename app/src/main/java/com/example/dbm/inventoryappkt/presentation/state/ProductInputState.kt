package com.example.dbm.inventoryappkt.presentation.state

data class ProductInputState(
    var productName: String = "",
    var productPrice: String = "",
    var productManufactureYear: String = "",
    var productBrand: String = "",
    var productQuantity: String = "",
    var productWeight: String = "",
    var productType: String = "",
    var productStockStatus: String = "",
    var productWarranty: String = ""
)