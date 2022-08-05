package com.example.dbm.inventoryappkt.domain.service

import com.example.dbm.inventoryappkt.domain.model.ProductDomain
import com.example.dbm.inventoryappkt.domain.usecase.IAddProductUseCase
import com.example.dbm.inventoryappkt.domain.usecase.IDeleteProductUseCase
import com.example.dbm.inventoryappkt.domain.usecase.IGetProductsUseCase
import com.example.dbm.inventoryappkt.domain.usecase.IUpdateProductUseCase
import com.example.dbm.inventoryappkt.presentation.model.ProductDetailsView
import com.example.dbm.inventoryappkt.presentation.model.ProductListView
import javax.inject.Inject

class ProductsService @Inject constructor(
    private val getProductsUseCase: IGetProductsUseCase,
    private val addProductUseCase: IAddProductUseCase,
    private val updateProductUseCase: IUpdateProductUseCase,
    private val deleteProduct: IDeleteProductUseCase,
): IProductsService {

    override suspend fun getProducts(): List<ProductListView> {
        TODO("Not yet implemented")
    }

    override suspend fun getProductDetails(productId: Int): ProductDetailsView {
        TODO("Not yet implemented")
    }

    override suspend fun addProduct(productId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun updateProduct(productId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProduct(productId: Int) {
        TODO("Not yet implemented")
    }

}