package com.example.dbm.inventoryappkt.domain.repository

import com.example.dbm.inventoryappkt.domain.model.ProductDomain

interface IProductsRepository {

    suspend fun getProducts(): List<ProductDomain>
    suspend fun getProductById(productId: Int): ProductDomain
    suspend fun addProduct(productDomain: ProductDomain)
    suspend fun updateProduct(productDomain: ProductDomain)
    suspend fun removeProduct(productId: Int)
}