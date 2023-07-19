package com.example.dbm.inventoryappkt.presentation.state

import com.example.dbm.inventoryappkt.presentation.model.ProductDetailsView

sealed class ProductDetailsState {
    data class Success(val value: ProductDetailsView?): ProductDetailsState()
    object LoadingProductDetails : ProductDetailsState()
    object LoadingUpdating : ProductDetailsState()
    object LoadingRemoving : ProductDetailsState()
}