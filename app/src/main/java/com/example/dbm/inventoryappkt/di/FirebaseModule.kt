package com.example.dbm.inventoryappkt.di

import com.example.dbm.inventoryappkt.data.network.datasource.FirebaseStorageDataSource
import com.example.dbm.inventoryappkt.data.network.datasource.IFirebaseDataSource
import com.example.dbm.inventoryappkt.domain.service.IUserService
import com.example.dbm.inventoryappkt.presentation.util.FirebaseAuthenticator
import com.example.dbm.inventoryappkt.presentation.util.IFirebaseAuthenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    fun provideFirebaseStorageDataSource(): IFirebaseDataSource {
        return FirebaseStorageDataSource()
    }

    @Provides
    @Singleton
    fun provideFirebaseAuthenticator(userService: IUserService): IFirebaseAuthenticator {
        return FirebaseAuthenticator(userService)
    }
}