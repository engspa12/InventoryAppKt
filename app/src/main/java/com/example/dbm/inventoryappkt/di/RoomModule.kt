package com.example.dbm.inventoryappkt.di

import android.content.Context
import androidx.room.Room
import com.example.dbm.inventoryappkt.data.local.datasource.ProductsDao
import com.example.dbm.inventoryappkt.data.local.datasource.ProductsRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): ProductsRoomDatabase {
        return Room.databaseBuilder(
            context,
            ProductsRoomDatabase::class.java,
            "InventoryDB"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(database: ProductsRoomDatabase): ProductsDao {
        return database.productsDao()
    }
}