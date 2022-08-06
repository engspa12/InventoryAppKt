package com.example.dbm.inventoryappkt.domain.service

import com.example.dbm.inventoryappkt.domain.model.ProductDomain
import com.example.dbm.inventoryappkt.domain.usecase.*
import com.example.dbm.inventoryappkt.domain.util.toDetailsView
import com.example.dbm.inventoryappkt.domain.util.toListView
import com.example.dbm.inventoryappkt.presentation.model.ProductDetailsView
import com.example.dbm.inventoryappkt.presentation.model.ProductListView
import javax.inject.Inject

class ProductsService @Inject constructor(
    private val getProductsUseCase: IGetProductsUseCase,
    private val getProductByIdUseCase: IGetProductByIdUseCase,
    private val addProductUseCase: IAddProductUseCase,
    private val updateProductUseCase: IUpdateProductUseCase,
    private val deleteProductUseCase: IDeleteProductUseCase,
): IProductsService {

    override suspend fun getProducts(): List<ProductListView> {
        return getProductsUseCase().map {
            it.toListView()
        }
    }

    override suspend fun getProductDetails(productId: Int): ProductDetailsView {
        return getProductByIdUseCase(productId = productId).toDetailsView()
    }

    override suspend fun addProduct(productId: Int) {
        //addProductUseCase()
    }

    override suspend fun updateProduct(productId: Int) {
        //updateProductUseCase()
    }

    override suspend fun deleteProduct(productId: Int) {
        deleteProductUseCase(productId)
    }

}