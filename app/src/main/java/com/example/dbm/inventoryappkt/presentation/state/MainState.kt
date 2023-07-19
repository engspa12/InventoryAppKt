package com.example.dbm.inventoryappkt.presentation.state

import com.example.dbm.inventoryappkt.presentation.model.ProductListView

sealed class MainState {
    data class Success(val value: List<ProductListView>): MainState()
    object LoadingDummyProduct : MainState()
    object LoadingRemovingProduct : MainState()
    object LoadingProducts : MainState()
}