package com.example.dbm.inventoryappkt.di

import com.example.dbm.inventoryappkt.domain.repository.IProductsRepository
import com.example.dbm.inventoryappkt.domain.repository.IUserRepository
import com.example.dbm.inventoryappkt.domain.usecase.AddProductUseCase
import com.example.dbm.inventoryappkt.domain.usecase.DeleteProductUseCase
import com.example.dbm.inventoryappkt.domain.usecase.GetProductByIdUseCase
import com.example.dbm.inventoryappkt.domain.usecase.GetProductsUseCase
import com.example.dbm.inventoryappkt.domain.usecase.GetUserIdUseCase
import com.example.dbm.inventoryappkt.domain.usecase.IAddProductUseCase
import com.example.dbm.inventoryappkt.domain.usecase.IDeleteProductUseCase
import com.example.dbm.inventoryappkt.domain.usecase.IGetProductByIdUseCase
import com.example.dbm.inventoryappkt.domain.usecase.IGetProductsUseCase
import com.example.dbm.inventoryappkt.domain.usecase.IGetUserIdUseCase
import com.example.dbm.inventoryappkt.domain.usecase.ISetUserIdUseCase
import com.example.dbm.inventoryappkt.domain.usecase.IUpdateProductUseCase
import com.example.dbm.inventoryappkt.domain.usecase.SetUserIdUseCase
import com.example.dbm.inventoryappkt.domain.usecase.UpdateProductUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    fun provideGetProductsUseCase(
        productsRepository: IProductsRepository
    ): IGetProductsUseCase {
        return GetProductsUseCase(productsRepository)
    }

    @Provides
    fun provideGetProductByIdUseCase(
        productsRepository: IProductsRepository
    ): IGetProductByIdUseCase {
        return GetProductByIdUseCase(productsRepository)
    }

    @Provides
    fun provideAddProductUseCase(
        productsRepository: IProductsRepository
    ): IAddProductUseCase {
        return AddProductUseCase(productsRepository)
    }

    @Provides
    fun provideUpdateProductUseCase(
        productsRepository: IProductsRepository
    ): IUpdateProductUseCase {
        return UpdateProductUseCase(productsRepository)
    }

    @Provides
    fun provideDeleteProductUseCase(
        productsRepository: IProductsRepository
    ): IDeleteProductUseCase {
        return DeleteProductUseCase(productsRepository)
    }

    @Provides
    fun provideSetUserIdUseCase(
        userRepository: IUserRepository
    ): ISetUserIdUseCase {
        return SetUserIdUseCase(userRepository)
    }

    @Provides
    fun provideGetUserIdUseCase(
        userRepository: IUserRepository
    ): IGetUserIdUseCase {
        return GetUserIdUseCase(userRepository)
    }
}