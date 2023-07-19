package com.example.dbm.inventoryappkt.presentation.util

import android.content.Context
import com.example.dbm.inventoryappkt.R
import com.example.dbm.inventoryappkt.presentation.state.MainState
import com.example.dbm.inventoryappkt.presentation.state.ProductDetailsState
import java.util.Calendar

fun ProductDetailsActionEvent.mapToStringResource(): Int {
    return when(this) {
        ProductDetailsActionEvent.GenericError -> R.string.error_unknown
        ProductDetailsActionEvent.NoConnectionError -> R.string.no_internet_connection
        ProductDetailsActionEvent.DeletingFromStorageError -> R.string.error_deletion_firebase
        ProductDetailsActionEvent.NoAuthenticatedError -> R.string.user_not_authenticated
        else -> R.string.error_unknown
    }
}

fun MainEvent.mapToStringResource(): Int {
    return when(this) {
        MainEvent.GenericError -> R.string.error_unknown
        MainEvent.NoConnectionError -> R.string.no_internet_connection
        MainEvent.DeletingFromStorageError -> R.string.error_deletion_firebase
        else -> R.string.error_unknown
    }
}

fun ValidationEvent.mapToStringResource(context: Context): String {
    val error = this as ValidationEvent.Failure
    return when(error.errorType){
        ValidationEventError.NO_INTERNET_CONNECTION -> context.getString(R.string.no_internet_connection)
        ValidationEventError.GENERIC -> context.getString(R.string.error_unknown)
        ValidationEventError.UPLOADING_TO_STORAGE_SERVICE -> context.getString(R.string.error_upload_firebase)
        ValidationEventError.DELETING_FROM_STORAGE_SERVICE -> context.getString(R.string.error_deletion_firebase)
        ValidationEventError.USER_NOT_AUTHENTICATED -> context.getString(R.string.user_not_authenticated)
        ValidationEventError.MUST_SELECT_IMAGE_FOR_PRODUCT -> context.getString(R.string.image_uri_empty_error_message)
        ValidationEventError.TYPE_CANNOT_BE_EMPTY -> context.getString(R.string.type_empty_error_message)
        ValidationEventError.STOCK_STATUS_CANNOT_BE_EMPTY -> context.getString(R.string.stock_status_empty_error_message)
        ValidationEventError.QUANTITY_MUST_BE_WHOLE_NUMBER -> context.getString(R.string.quantity_format_error_message)
        ValidationEventError.QUANTITY_CANNOT_BE_NEGATIVE -> context.getString(R.string.quantity_negative_number_error_message)
        ValidationEventError.WEIGHT_MUST_BE_DECIMAL_NUMBER -> context.getString(R.string.weight_format_error_message)
        ValidationEventError.WEIGHT_CANNOT_BE_NEGATIVE -> context.getString(R.string.weight_negative_number_error_message)
        ValidationEventError.WARRANTY_MUST_BE_WHOLE_NUMBER -> context.getString(R.string.warranty_format_error_message)
        ValidationEventError.WARRANTY_CANNOT_BE_NEGATIVE -> context.getString(R.string.warranty_negative_number_error_message)
        ValidationEventError.BRAND_CANNOT_BE_EMPTY -> context.getString(R.string.brand_empty_error_message)
        ValidationEventError.BRAND_CANNOT_BE_LESS_THAN_THREE_CHARACTERS -> context.getString(R.string.brand_not_enough_characters_error_message)
        ValidationEventError.PRICE_MUST_BE_DECIMAL_NUMBER -> context.getString(R.string.price_format_error_message)
        ValidationEventError.PRICE_CANNOT_BE_NEGATIVE -> context.getString(R.string.price_negative_number_error_message)
        ValidationEventError.MANUFACTURE_YEAR_MUST_BE_WHOLE_NUMBER -> context.getString(R.string.manufacture_year_format_error_message)
        ValidationEventError.MANUFACTURE_YEAR_MUST_HAVE_FOUR_DIGITS -> context.getString(R.string.manufacture_year_number_digits_error_message)
        ValidationEventError.MANUFACTURE_YEAR_OUT_OF_RANGE -> context.getString(R.string.manufacture_year_range_error_message, Calendar.getInstance().get(Calendar.YEAR))
        ValidationEventError.NAME_CANNOT_BE_EMPTY -> context.getString(R.string.name_empty_error_message)
        ValidationEventError.NAME_CANNOT_BE_LESS_THAN_THREE_CHARACTERS -> context.getString(R.string.name_not_enough_characters_error_message)
    }
}

fun ProductType.mapToStringResource(): Int {
    return when(this) {
        ProductType.SPORTS -> R.string.sports_category
        ProductType.TECHNOLOGY -> R.string.technology_category
        ProductType.CLOTHING -> R.string.clothing_category
        ProductType.FURNITURE -> R.string.furniture_category
        ProductType.OTHER -> R.string.other_category
    }
}

fun StockType.mapToStringResource(): Int {
    return when(this) {
        StockType.IN_STOCK -> R.string.in_stock_status
        StockType.WITHOUT_STOCK -> R.string.without_stock_status
    }
}

fun MainState.mapToStringResource(): Int {
    return when(this){
        is MainState.LoadingDummyProduct -> R.string.loading_adding_dummy_product
        is MainState.LoadingProducts -> R.string.loading_products
        is MainState.LoadingRemovingProduct-> R.string.loading_deleting_product
        else -> R.string.error_unknown
    }
}

fun ProductDetailsState.mapToStringResource(): Int {
    return when(this) {
        is ProductDetailsState.LoadingProductDetails -> R.string.loading_product_details
        is ProductDetailsState.LoadingUpdating -> R.string.loading_updating_product
        is ProductDetailsState.LoadingRemoving -> R.string.loading_deleting_product
        else -> R.string.error_unknown
    }
}

fun <T> T.getStringResourceForType(): Int {
    return when(this){
        is ProductType -> mapToStringResource()
        is StockType -> mapToStringResource()
        else -> R.string.error_unknown
    }
}

inline fun <reified T> String.toType(input: T): T {
    return when(input) {
        is ProductType -> toProductType() as T
        is StockType -> toStockType() as T
        else -> input
    }
}

fun String.toProductType(): ProductType {
    return when(this) {
        "sports" -> ProductType.SPORTS
        "technology" -> ProductType.TECHNOLOGY
        "furniture" -> ProductType.FURNITURE
        "clothing" -> ProductType.CLOTHING
        else -> ProductType.OTHER
    }
}

fun String.toStockType(): StockType {
    return when(this){
        "in_stock" -> StockType.IN_STOCK
        else -> StockType.WITHOUT_STOCK
    }
}