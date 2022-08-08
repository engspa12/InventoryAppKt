package com.example.dbm.inventoryappkt.presentation.state

import com.example.dbm.inventoryappkt.presentation.model.ProductListView
import com.example.dbm.inventoryappkt.util.StringWrapper

sealed class MainState(
    val value: List<ProductListView>? = null,
    val errorMessage: StringWrapper = StringWrapper.SimpleString(""),
    val loadingMessage: StringWrapper = StringWrapper.SimpleString("")
) {
    class Success(value: List<ProductListView>): MainState(value)
    class Error(errorMessage: StringWrapper): MainState(errorMessage = errorMessage)
    class Loading(loadingMessage: StringWrapper) : MainState(loadingMessage = loadingMessage)
}