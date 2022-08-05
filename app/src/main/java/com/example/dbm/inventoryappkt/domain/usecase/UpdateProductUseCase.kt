package com.example.dbm.inventoryappkt.domain.usecase

import com.example.dbm.inventoryappkt.domain.model.ProductDomain
import com.example.dbm.inventoryappkt.domain.repository.IProductsRepository
import javax.inject.Inject

interface IUpdateProductUseCase {
    suspend operator fun invoke(product: ProductDomain)
}

class UpdateProductUseCase @Inject constructor(
    private val productsRepository: IProductsRepository
): IUpdateProductUseCase {
    override suspend fun invoke(product: ProductDomain) {
        productsRepository.updateProduct(product)
    }

}