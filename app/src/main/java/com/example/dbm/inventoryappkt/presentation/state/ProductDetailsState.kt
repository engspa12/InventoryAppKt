package com.example.dbm.inventoryappkt.presentation.state

import com.example.dbm.inventoryappkt.presentation.model.ProductDetailsView
import com.example.dbm.inventoryappkt.util.StringWrapper

sealed class ProductDetailsState(
    val value: ProductDetailsView? = null,
    val loadingMessage: StringWrapper = StringWrapper.SimpleStringWrapper("")
) {
    class Success(value: ProductDetailsView?): ProductDetailsState(value)
    class Loading(loadingMessage: StringWrapper) : ProductDetailsState(loadingMessage = loadingMessage)
}