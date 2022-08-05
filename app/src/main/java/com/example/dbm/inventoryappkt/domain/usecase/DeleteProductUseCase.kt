package com.example.dbm.inventoryappkt.domain.usecase

import com.example.dbm.inventoryappkt.domain.repository.IProductsRepository
import javax.inject.Inject

interface IDeleteProductUseCase {
    suspend operator fun invoke(productId: Int)
}

class DeleteProductUseCase @Inject constructor(
    private val productsRepository: IProductsRepository
): IDeleteProductUseCase {

    override suspend fun invoke(productId: Int) {
        productsRepository.removeProduct(productId)
    }
}