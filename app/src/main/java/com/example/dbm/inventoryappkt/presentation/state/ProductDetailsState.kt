package com.example.dbm.inventoryappkt.presentation.state

import com.example.dbm.inventoryappkt.presentation.model.ProductDetailsView
import com.example.dbm.inventoryappkt.util.StringWrapper

sealed class ProductDetailsState(
    val value: ProductDetailsView? = null,
    val errorMessage: StringWrapper = StringWrapper.SimpleString(""),
    val loadingMessage: StringWrapper = StringWrapper.SimpleString("")
) {
    class Success(value: ProductDetailsView?): ProductDetailsState(value)
    class Error(errorMessage: StringWrapper): ProductDetailsState(errorMessage = errorMessage)
    class Loading(loadingMessage: StringWrapper) : ProductDetailsState(loadingMessage = loadingMessage)
}