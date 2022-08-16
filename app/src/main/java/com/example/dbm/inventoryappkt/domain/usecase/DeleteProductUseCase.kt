package com.example.dbm.inventoryappkt.domain.usecase

import com.example.dbm.inventoryappkt.R
import com.example.dbm.inventoryappkt.domain.repository.IProductsRepository
import com.example.dbm.inventoryappkt.util.ResultWrapper
import com.example.dbm.inventoryappkt.util.StringWrapper
import com.google.firebase.storage.StorageException
import javax.inject.Inject

interface IDeleteProductUseCase {
    suspend operator fun invoke(productId: Int): ResultWrapper<Unit>
}

class DeleteProductUseCase @Inject constructor(
    private val productsRepository: IProductsRepository
): IDeleteProductUseCase {

    override suspend fun invoke(productId: Int): ResultWrapper<Unit> {
        return when (val result = productsRepository.deleteProduct(productId)){
            is ResultWrapper.Success -> {
                ResultWrapper.Success(Unit)
            }
            is ResultWrapper.Failure -> {
                if(result.exception is StorageException){
                    ResultWrapper.Failure(
                        null,
                        StringWrapper.ResourceStringWrapper(id = R.string.error_deletion_firebase)
                    )
                } else {
                    ResultWrapper.Failure(
                        null,
                        StringWrapper.ResourceStringWrapper(id = R.string.error_unknown_firebase, args = arrayOf(result.exception?.message ?: ""))
                    )
                }
            }
        }
    }
}