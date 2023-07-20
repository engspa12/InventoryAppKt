package com.example.dbm.inventoryappkt.domain.usecase

import com.example.dbm.inventoryappkt.domain.model.ProductDomain
import com.example.dbm.inventoryappkt.domain.repository.IProductsRepository
import javax.inject.Inject

interface IGetProductsUseCase {
    suspend operator fun invoke(): List<ProductDomain>
}

class GetProductsUseCase @Inject constructor(
    private val productsRepository: IProductsRepository
): IGetProductsUseCase {

    override suspend fun invoke(): List<ProductDomain> {
        return productsRepository.getProducts()
    }
}