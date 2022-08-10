package com.example.dbm.inventoryappkt.domain.model

data class ProductDomain(
    var id: Int = -1,
    val brand: String,
    val warranty: Int,
    val manufactureYear: Int,
    val weight: Double,
    val price: Double,
    val quantity: Int,
    val inStock: Boolean,
    val name: String,
    val type: String,
    val imageUrl: String,
    val imageUrlStorageLocation: String,
    val isDummyProduct: Boolean
)