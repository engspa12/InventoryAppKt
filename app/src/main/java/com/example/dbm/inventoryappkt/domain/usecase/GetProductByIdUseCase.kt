package com.example.dbm.inventoryappkt.domain.usecase

import com.example.dbm.inventoryappkt.domain.model.ProductDomain
import com.example.dbm.inventoryappkt.domain.repository.IProductsRepository
import javax.inject.Inject

interface IGetProductByIdUseCase {
    suspend operator fun invoke(productId: Int): ProductDomain
}

class GetProductByIdUseCase @Inject constructor(
    private val productsRepository: IProductsRepository
): IGetProductByIdUseCase {

    override suspend fun invoke(productId: Int): ProductDomain {
        return productsRepository.getProductById(productId)
    }
}