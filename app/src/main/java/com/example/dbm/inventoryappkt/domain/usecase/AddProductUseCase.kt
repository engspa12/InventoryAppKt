package com.example.dbm.inventoryappkt.domain.usecase

import com.example.dbm.inventoryappkt.R
import com.example.dbm.inventoryappkt.domain.model.ProductDomain
import com.example.dbm.inventoryappkt.domain.repository.IProductsRepository
import com.example.dbm.inventoryappkt.util.ResultWrapper
import com.example.dbm.inventoryappkt.util.StringWrapper
import com.google.firebase.storage.StorageException
import javax.inject.Inject

interface IAddProductUseCase {
    suspend operator fun invoke(product: ProductDomain): ResultWrapper
}

class AddProductUseCase @Inject constructor(
    private val productsRepository: IProductsRepository
): IAddProductUseCase {

    override suspend fun invoke(product: ProductDomain): ResultWrapper {
        return when(val result = productsRepository.addProduct(product)){
            is ResultWrapper.Success -> {
                ResultWrapper.Success
            }
            is ResultWrapper.Failure -> {
                if(result.exception is StorageException){
                    ResultWrapper.Failure(
                        null,
                        StringWrapper.ResourceStringWrapper(id = R.string.error_upload_firebase)
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