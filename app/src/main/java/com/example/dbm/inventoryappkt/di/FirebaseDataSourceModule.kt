package com.example.dbm.inventoryappkt.di

import com.example.dbm.inventoryappkt.data.network.datasource.FirebaseStorageDataSource
import com.example.dbm.inventoryappkt.data.network.datasource.IFirebaseDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseDataSourceModule {

    @Provides
    fun provideFirebaseDataSource(): IFirebaseDataSource {
        return FirebaseStorageDataSource()
    }
}