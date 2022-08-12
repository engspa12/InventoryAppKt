package com.example.dbm.inventoryappkt.di

import com.example.dbm.inventoryappkt.domain.service.IProductsService
import com.example.dbm.inventoryappkt.domain.service.IValidationService
import com.example.dbm.inventoryappkt.domain.service.ProductsService
import com.example.dbm.inventoryappkt.domain.service.ValidationService
import com.example.dbm.inventoryappkt.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

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
    ): IProductsService {
        return ProductsService(
            getProductsUseCase,
            getProductByIdUseCase,
            addProductUseCase,
            updateProductUseCase,
            deleteProductUseCase
        )
    }

    @Provides
    fun provideValidationService(): IValidationService {
        return ValidationService()
    }
}