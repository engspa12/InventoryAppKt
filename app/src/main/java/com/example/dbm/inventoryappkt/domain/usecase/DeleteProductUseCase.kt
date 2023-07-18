package com.example.dbm.inventoryappkt.domain.usecase

import com.example.dbm.inventoryappkt.domain.repository.IProductsRepository
import com.example.dbm.inventoryappkt.domain.util.ProductDomainError
import com.example.dbm.inventoryappkt.util.ResultWrapper
import com.google.firebase.storage.StorageException
import javax.inject.Inject

interface IDeleteProductUseCase {
    suspend operator fun invoke(productId: Int): ResultWrapper<Unit, ProductDomainError>
}

class DeleteProductUseCase @Inject constructor(
    private val productsRepository: IProductsRepository
): IDeleteProductUseCase {

    override suspend fun invoke(productId: Int): ResultWrapper<Unit, ProductDomainError> {
        return when (val result = productsRepository.deleteProduct(productId)){
            is ResultWrapper.Success -> {
                ResultWrapper.Success(Unit)
            }
            is ResultWrapper.Failure -> {
                if(result.exception is StorageException){
                    ResultWrapper.Failure(
                        ProductDomainError.DELETING_FROM_STORAGE_SERVICE
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