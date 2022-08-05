package com.example.dbm.inventoryappkt.domain.service

import com.example.dbm.inventoryappkt.domain.model.ProductDomain
import com.example.dbm.inventoryappkt.presentation.model.ProductDetailsView
import com.example.dbm.inventoryappkt.presentation.model.ProductListView

interface IProductsService {
    suspend fun getProducts(): List<ProductListView>
    suspend fun getProductDetails(productId: Int): ProductDetailsView
    suspend fun addProduct(productId: Int)
    suspend fun updateProduct(productId: Int)
    suspend fun deleteProduct(productId: Int)
}