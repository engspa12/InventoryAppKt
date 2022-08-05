package com.example.dbm.inventoryappkt.di

import com.example.dbm.inventoryappkt.data.local.mapper.ProductCacheMapper
import com.example.dbm.inventoryappkt.data.local.model.ProductCache
import com.example.dbm.inventoryappkt.domain.model.ProductDomain
import com.example.dbm.inventoryappkt.domain.util.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MappersModule {

    @Provides
    fun provideMovieCacheMapper(): CacheMapper<ProductCache, ProductDomain> {
        return ProductCacheMapper()
    }
}