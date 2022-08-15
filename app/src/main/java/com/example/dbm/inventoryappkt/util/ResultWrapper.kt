package com.example.dbm.inventoryappkt.util

sealed class ResultWrapper {
    object Success: ResultWrapper()
    data class Failure(val exception: Exception? = null, val errorMessage: StringWrapper = StringWrapper.SimpleStringWrapper("")): ResultWrapper()
}
