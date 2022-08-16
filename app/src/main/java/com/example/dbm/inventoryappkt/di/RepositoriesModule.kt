package com.example.dbm.inventoryappkt.di

import com.example.dbm.inventoryappkt.data.local.datasource.ProductsDao
import com.example.dbm.inventoryappkt.data.local.datasource.UserDataStore
import com.example.dbm.inventoryappkt.data.local.model.ProductCache
import com.example.dbm.inventoryappkt.data.network.datasource.IFirebaseDataSource
import com.example.dbm.inventoryappkt.data.repository.ProductsRepository
import com.example.dbm.inventoryappkt.data.repository.UserRepository
import com.example.dbm.inventoryappkt.domain.model.ProductDomain
import com.example.dbm.inventoryappkt.domain.repository.IProductsRepository
import com.example.dbm.inventoryappkt.domain.repository.IUserRepository
import com.example.dbm.inventoryappkt.domain.util.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    fun provideProductsRepository(
        networkDataSource: IFirebaseDataSource,
        localDataSource: ProductsDao,
        cacheMapper: CacheMapper<ProductCache, ProductDomain>,
        @DispatchersModule.IODispatcher dispatcher: CoroutineDispatcher
    ): IProductsRepository {
        return ProductsRepository(networkDataSource, localDataSource, cacheMapper, dispatcher)
    }

    @Provides
    fun provideUserRepository(
        userDataStore: UserDataStore
    ): IUserRepository {
        return UserRepository(userDataStore)
    }
}