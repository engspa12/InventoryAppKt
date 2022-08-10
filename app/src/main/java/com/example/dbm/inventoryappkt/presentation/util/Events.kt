package com.example.dbm.inventoryappkt.presentation.util

sealed class ValidationEvent {
    object Success: ValidationEvent()
    object Failure: ValidationEvent()
}

sealed class ListChangedEvent {
    object FullListEvent: ListChangedEvent()
}

sealed class ProductChangeEvent {
    data class NameChanged(val name: String): ProductChangeEvent()
    data class PriceChanged(val price: String): ProductChangeEvent()
    data class ManufactureYearChanged(val manufactureYear: String): ProductChangeEvent()
    data class BrandChanged(val brand: String): ProductChangeEvent()
    data class QuantityChanged(val quantity: String): ProductChangeEvent()
    data class WeightChanged(val weight: String): ProductChangeEvent()
    data class TypeChanged(val type: String): ProductChangeEvent()
    data class StockStatusChanged(val stockStatus: String): ProductChangeEvent()
    data class WarrantyChanged(val warranty: String): ProductChangeEvent()

}