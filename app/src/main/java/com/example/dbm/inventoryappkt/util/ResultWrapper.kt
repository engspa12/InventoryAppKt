package com.example.dbm.inventoryappkt.util

sealed class ResultWrapper<T> {
    data class Success<T>(val value: T): ResultWrapper<T>()
    data class Failure<T>(val exception: Exception? = null, val errorMessage: StringWrapper = StringWrapper.SimpleStringWrapper("")): ResultWrapper<T>()
}
