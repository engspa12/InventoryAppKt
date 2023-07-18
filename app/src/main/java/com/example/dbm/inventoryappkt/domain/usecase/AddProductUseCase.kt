package com.example.dbm.inventoryappkt.domain.usecase

import com.example.dbm.inventoryappkt.domain.model.ProductDomain
import com.example.dbm.inventoryappkt.domain.repository.IProductsRepository
import com.example.dbm.inventoryappkt.domain.util.ProductDomainError
import com.example.dbm.inventoryappkt.util.ResultWrapper
import com.google.firebase.storage.StorageException
import javax.inject.Inject

interface IAddProductUseCase {
    suspend operator fun invoke(product: ProductDomain): ResultWrapper<Unit, ProductDomainError>
}

class AddProductUseCase @Inject constructor(
    private val productsRepository: IProductsRepository
): IAddProductUseCase {

    override suspend fun invoke(product: ProductDomain): ResultWrapper<Unit, ProductDomainError> {
        return when(val result = productsRepository.addProduct(product)){
            is ResultWrapper.Success -> {
                ResultWrapper.Success(Unit)
            }
            is ResultWrapper.Failure -> {
                if(result.exception is StorageException){
                    ResultWrapper.Failure(
                        ProductDomainError.UPLOADING_TO_STORAGE_SERVICE
                    )
                } else {
                    ResultWrapper.Failure(
                        ProductDomainError.GENERIC
                    )
                }
            }
        }
    }
}