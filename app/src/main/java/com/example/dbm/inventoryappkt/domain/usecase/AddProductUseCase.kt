package com.example.dbm.inventoryappkt.domain.usecase

import com.example.dbm.inventoryappkt.domain.model.ProductDomain
import com.example.dbm.inventoryappkt.domain.repository.IProductsRepository
import javax.inject.Inject

interface IAddProductUseCase {
    suspend operator fun invoke(product: ProductDomain)
}

class AddProductUseCase @Inject constructor(
    private val productsRepository: IProductsRepository
): IAddProductUseCase {

    override suspend fun invoke(product: ProductDomain) {
        productsRepository.addProduct(product)
    }
}