package com.example.dbm.inventoryappkt.di

import com.example.dbm.inventoryappkt.domain.service.IProductsService
import com.example.dbm.inventoryappkt.domain.service.IUserService
import com.example.dbm.inventoryappkt.domain.service.IValidationService
import com.example.dbm.inventoryappkt.domain.service.ProductsService
import com.example.dbm.inventoryappkt.domain.service.UserService
import com.example.dbm.inventoryappkt.domain.service.ValidationService
import com.example.dbm.inventoryappkt.domain.usecase.IAddProductUseCase
import com.example.dbm.inventoryappkt.domain.usecase.IDeleteProductUseCase
import com.example.dbm.inventoryappkt.domain.usecase.IGetProductByIdUseCase
import com.example.dbm.inventoryappkt.domain.usecase.IGetProductsUseCase
import com.example.dbm.inventoryappkt.domain.usecase.IGetUserIdUseCase
import com.example.dbm.inventoryappkt.domain.usecase.ISetUserIdUseCase
import com.example.dbm.inventoryappkt.domain.usecase.IUpdateProductUseCase
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