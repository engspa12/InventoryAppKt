package com.example.dbm.inventoryappkt.data.repository

import android.net.Uri
import com.example.dbm.inventoryappkt.data.local.datasource.ProductsDao
import com.example.dbm.inventoryappkt.data.local.model.ProductCache
import com.example.dbm.inventoryappkt.data.network.datasource.FirebaseStorageResult
import com.example.dbm.inventoryappkt.data.network.datasource.IFirebaseDataSource
import com.example.dbm.inventoryappkt.di.DispatchersModule
import com.example.dbm.inventoryappkt.domain.model.ProductDomain
import com.example.dbm.inventoryappkt.domain.repository.IProductsRepository
import com.example.dbm.inventoryappkt.domain.util.CacheMapper
import com.example.dbm.inventoryappkt.util.ResultWrapper
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

    override suspend fun addProduct(productDomain: ProductDomain): ResultWrapper<Unit, Nothing> {
        return withContext(dispatcher) {
            if(productDomain.isDummyProduct){
                val productCache = cacheMapper.mapFromDomainModel(productDomain)
                localDataSource.saveProduct(productCache)
                ResultWrapper.Success(Unit)
            } else {
                when(val result = networkDataSource.uploadImageToStorage(Uri.parse(productDomain.imageUriInDeviceString))) {
                    is FirebaseStorageResult.Success -> {
                        productDomain.imageUrl = result.downloadUrl.orEmpty()
                        val productCache = cacheMapper.mapFromDomainModel(productDomain)
                        localDataSource.saveProduct(productCache)
                        ResultWrapper.Success(Unit)
                    }
                    is FirebaseStorageResult.Error -> {
                        ResultWrapper.Failure(exception = result.exception)
                    }
                }
            }
        }
    }

    override suspend fun updateProduct(productDomain: ProductDomain) {
        withContext(dispatcher) {
            val productCache = cacheMapper.mapFromDomainModel(productDomain)
            productCache.id = productDomain.id
            localDataSource.updateProduct(productCache)
        }
    }

    override suspend fun deleteProduct(productId: Int): ResultWrapper<Unit, Nothing> {
        return withContext(dispatcher) {
            val product = getProductById(productId)
            if(product.isDummyProduct){
                localDataSource.deleteProductById(productId)
                ResultWrapper.Success(Unit)
            } else {
                when(val result = networkDataSource.deleteProductInStorage(Uri.parse(product.imageUriInDeviceString))){
                    is FirebaseStorageResult.Success -> {
                        localDataSource.deleteProductById(productId)
                        ResultWrapper.Success(Unit)
                    }
                    is FirebaseStorageResult.Error -> {
                        ResultWrapper.Failure(exception = result.exception)
                    }
                }
            }
        }
    }

}