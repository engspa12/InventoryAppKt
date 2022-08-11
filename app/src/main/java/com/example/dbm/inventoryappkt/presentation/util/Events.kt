package com.example.dbm.inventoryappkt.presentation.util

sealed class ValidationEvent {
    object Success: ValidationEvent()
    object Failure: ValidationEvent()
}

sealed class ProductDetailsChangeEvent {
    data class NameChanged(val name: String): ProductDetailsChangeEvent()
    data class PriceChanged(val price: String): ProductDetailsChangeEvent()
    data class ManufactureYearChanged(val manufactureYear: String): ProductDetailsChangeEvent()
    data class BrandChanged(val brand: String): ProductDetailsChangeEvent()
    data class QuantityChanged(val quantity: String): ProductDetailsChangeEvent()
    data class WeightChanged(val weight: String): ProductDetailsChangeEvent()
    data class TypeChanged(val type: String): ProductDetailsChangeEvent()
    data class StockStatusChanged(val stockStatus: String): ProductDetailsChangeEvent()
    data class WarrantyChanged(val warranty: String): ProductDetailsChangeEvent()
}

sealed class ProductActionEvent {
    object ProductUpdated: ProductActionEvent()
    object ProductDeleted: ProductActionEvent()
}
