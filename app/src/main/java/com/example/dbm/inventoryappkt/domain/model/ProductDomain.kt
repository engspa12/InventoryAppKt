package com.example.dbm.inventoryappkt.domain.model

data class ProductDomain(
    val id: Int,
    val brand: String,
    val warranty: Int,
    val manufactureYear: String,
    val weight: Double,
    val price: Double,
    val quantity: Int,
    val inStock: Boolean,
    val name: String,
    val type: Int,
    val imageUrl: String,
    val imageUrlStorageLocation: String
)