package com.example.dbm.inventoryappkt.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductCache(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
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