package com.example.dbm.inventoryappkt.data.repository

import com.example.dbm.inventoryappkt.data.local.datasource.ProductsDao
import com.example.dbm.inventoryappkt.data.local.model.ProductCache
import com.example.dbm.inventoryappkt.data.network.datasource.IFirebaseDataSource
import com.example.dbm.inventoryappkt.di.DispatchersModule
import com.example.dbm.inventoryappkt.domain.model.ProductDomain
import com.example.dbm.inventoryappkt.domain.repository.IProductsRepository
import com.example.dbm.inventoryappkt.domain.util.CacheMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val networkDataSource: IFirebaseDataSource,
    private val localDataSource: ProductsDao,
    private val cacheMapper: CacheMapper<ProductCache, ProductDomain>,
    @DispatchersModule.IODispatcher private val dispatcher: CoroutineDispatcher
): IProductsRepository {

    override suspend fun getProducts(): List<ProductDomain> {
        return withContext(dispatcher) {
            val listProductsCache = localDataSource.getProducts()
            val listProductsDomain = listProductsCache.map {
                cacheMapper.mapToDomainModel(it)
            }
            listProductsDomain
        }
    }

    override suspend fun getProductById(productId: Int): ProductDomain {
        return withContext(dispatcher) {
            val productCache = localDataSource.getProductById(productId)
            cacheMapper.mapToDomainModel(productCache)
        }
    }

    override suspend fun addProduct(productDomain: ProductDomain) {
        withContext(dispatcher) {
            val productCache = cacheMapper.mapFromDomainModel(productDomain)
            localDataSource.saveProduct(productCache)
        }
    }

    override suspend fun updateProduct(productDomain: ProductDomain) {
        withContext(dispatcher) {
            val productCache = cacheMapper.mapFromDomainModel(productDomain)
            productCache.id = productDomain.id
            localDataSource.updateProduct(productCache)
        }
    }

    override suspend fun removeProduct(productId: Int) {
        withContext(dispatcher) {
            localDataSource.deleteProductById(productId)
        }
    }

}