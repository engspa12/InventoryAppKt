package com.example.dbm.inventoryappkt.presentation.util

import com.example.dbm.inventoryappkt.util.StringWrapper

sealed class ValidationEvent {
    object Success: ValidationEvent()
    data class Failure(val errorMessage: StringWrapper? = null) : ValidationEvent()
}

sealed class ProductDetailsChangeEvent {
    data class NameChanged(val name: String): ProductDetailsChangeEvent()
    data class PriceChanged(val price: String): ProductDetailsChangeEvent()
    data class ManufactureYearChanged(val manufactureYear: String): ProductDetailsChangeEvent()
    data class BrandChanged(val brand: String): ProductDetailsChangeEvent()
    data class QuantityChanged(val quantity: String): ProductDetailsChangeEvent()
    data class WeightChanged(val weight: String): ProductDetailsChangeEvent()
    data class TypeChanged(val type: Map.Entry<String, StringWrapper>): ProductDetailsChangeEvent()
    data class StockStatusChanged(val stockStatus: Map.Entry<String, StringWrapper>): ProductDetailsChangeEvent()
    data class WarrantyChanged(val warranty: String): ProductDetailsChangeEvent()
    data class ImageSelectedChanged(val uri: String): ProductDetailsChangeEvent()
}

sealed class ProductDetailsActionEvent {
    object ProductDetailsUpdated: ProductDetailsActionEvent()
    object ProductDetailsDeleted: ProductDetailsActionEvent()
    data class Error(val errorMessage: StringWrapper): ProductDetailsActionEvent()
}

sealed class MainEvent {
    data class ListChanged(val listHasItems: Boolean): MainEvent()
    data class Error(val errorMessage: StringWrapper): MainEvent()
}