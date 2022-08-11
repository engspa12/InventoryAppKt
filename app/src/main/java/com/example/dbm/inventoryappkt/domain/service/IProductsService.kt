package com.example.dbm.inventoryappkt.domain.service

import com.example.dbm.inventoryappkt.domain.util.ProductModification
import com.example.dbm.inventoryappkt.presentation.model.ProductDetailsView
import com.example.dbm.inventoryappkt.presentation.model.ProductListView
import com.example.dbm.inventoryappkt.presentation.state.ProductInputState

interface IProductsService {
    suspend fun getProducts(): List<ProductListView>
    suspend fun getProductDetails(productId: Int): ProductDetailsView
    suspend fun insertDummyProduct()
    suspend fun addProduct(product: ProductInputState): String
    suspend fun saveModifiedProduct(productId: Int)
    suspend fun reduceProductQuantity(productId: Int)
    fun modifyProductForView(action: ProductModification): ProductDetailsView
    suspend fun deleteProduct(productId: Int)
}