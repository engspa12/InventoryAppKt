package com.example.dbm.inventoryappkt.presentation.state

import com.example.dbm.inventoryappkt.presentation.model.ProductListView
import com.example.dbm.inventoryappkt.util.StringWrapper

sealed class MainState(
    val value: List<ProductListView>? = null,
    val loadingMessage: StringWrapper = StringWrapper.SimpleStringWrapper("")
) {
    class Success(value: List<ProductListView>): MainState(value)
    class Loading(loadingMessage: StringWrapper) : MainState(loadingMessage = loadingMessage)
}