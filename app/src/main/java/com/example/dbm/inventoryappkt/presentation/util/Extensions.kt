package com.example.dbm.inventoryappkt.presentation.util

import android.content.Context
import com.example.dbm.inventoryappkt.R
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