package com.example.dbm.inventoryappkt.util

sealed class ResultWrapper<out T,out U> {
    data class Success<T>(val value: T): ResultWrapper<T, Nothing>()
    data class Failure<U>(val error: U? = null, val exception: Exception? = null): ResultWrapper<Nothing, U>()
}
