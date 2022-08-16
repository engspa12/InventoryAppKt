package com.example.dbm.inventoryappkt.di

import com.example.dbm.inventoryappkt.domain.service.*
import com.example.dbm.inventoryappkt.domain.usecase.*
import com.example.dbm.inventoryappkt.util.IConnectionChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {

    @Provides
    fun provideProductsService(
        getProductsUseCase: IGetProductsUseCase,
        getProductByIdUseCase: IGetProductByIdUseCase,
        addProductUseCase: IAddProductUseCase,
        updateProductUseCase: IUpdateProductUseCase,
        deleteProductUseCase: IDeleteProductUseCase,
        connectionChecker: IConnectionChecker
    ): IProductsService {
        return ProductsService(
            getProductsUseCase,
            getProductByIdUseCase,
            addProductUseCase,
            updateProductUseCase,
            deleteProductUseCase,
            connectionChecker
        )
    }

    @Provides
    fun provideValidationService(): IValidationService {
        return ValidationService()
    }

    @Provides
    @Singleton
    fun provideUserService(
        setUserIdUseCase: ISetUserIdUseCase,
        getUserIdUseCase: IGetUserIdUseCase
    ): IUserService {
        return UserService(setUserIdUseCase, getUserIdUseCase)
    }
}