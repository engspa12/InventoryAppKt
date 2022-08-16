package com.example.dbm.inventoryappkt.domain.repository

import com.example.dbm.inventoryappkt.domain.model.ProductDomain
import com.example.dbm.inventoryappkt.util.ResultWrapper

interface IProductsRepository {

    suspend fun getProducts(): List<ProductDomain>
    suspend fun getProductById(productId: Int): ProductDomain
    suspend fun addProduct(productDomain: ProductDomain): ResultWrapper<Unit>
    suspend fun updateProduct(productDomain: ProductDomain)
    suspend fun deleteProduct(productId: Int): ResultWrapper<Unit>
}