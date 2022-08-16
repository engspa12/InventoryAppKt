package com.example.dbm.inventoryappkt.di

import com.example.dbm.inventoryappkt.domain.repository.IProductsRepository
import com.example.dbm.inventoryappkt.domain.repository.IUserRepository
import com.example.dbm.inventoryappkt.domain.usecase.*
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