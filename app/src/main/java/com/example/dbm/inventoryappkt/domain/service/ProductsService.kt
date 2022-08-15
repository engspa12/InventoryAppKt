package com.example.dbm.inventoryappkt.domain.service

import com.example.dbm.inventoryappkt.domain.model.ProductDomain
import com.example.dbm.inventoryappkt.domain.usecase.*
import com.example.dbm.inventoryappkt.domain.util.ProductModification
import com.example.dbm.inventoryappkt.domain.util.toDetailsView
import com.example.dbm.inventoryappkt.domain.util.toListView
import com.example.dbm.inventoryappkt.global.Constants
import com.example.dbm.inventoryappkt.presentation.model.ProductDetailsView
import com.example.dbm.inventoryappkt.presentation.model.ProductListView
import com.example.dbm.inventoryappkt.util.ResultWrapper
import javax.inject.Inject

interface IProductsService {
    suspend fun getProducts(): List<ProductListView>
    suspend fun getProductDetails(productId: Int): ProductDetailsView
    suspend fun insertDummyProduct()
    suspend fun addProduct(product: ProductDomain): ResultWrapper
    suspend fun saveModifiedProduct(productId: Int)
    suspend fun reduceProductQuantity(productId: Int)
    fun modifyProductForView(action: ProductModification): ProductDetailsView
    suspend fun deleteProduct(productId: Int): ResultWrapper
}

class ProductsService @Inject constructor(
    private val getProductsUseCase: IGetProductsUseCase,
    private val getProductByIdUseCase: IGetProductByIdUseCase,
    private val addProductUseCase: IAddProductUseCase,
    private val updateProductUseCase: IUpdateProductUseCase,
    private val deleteProductUseCase: IDeleteProductUseCase,
): IProductsService {

    private lateinit var tempProductDetailsView: ProductDetailsView

    override suspend fun getProducts(): List<ProductListView> {
        return getProductsUseCase().map {
            it.toListView()
        }
    }

    override suspend fun getProductDetails(productId: Int): ProductDetailsView {
        val productDetailsView = getProductByIdUseCase(productId = productId).toDetailsView()
        tempProductDetailsView = productDetailsView
        return getProductByIdUseCase(productId = productId).toDetailsView()
    }

    override suspend fun insertDummyProduct() {
        val productDomain = ProductDomain(
            brand = "DBM",
            warranty = 180,
            manufactureYear = 2022,
            weight = 0.15,
            price = 24.00,
            quantity = 10,
            inStock = true,
            name = "Men's Shirt",
            type = "clothing",
            imageUrl = "",
            imageUriInDeviceString = "",
            isDummyProduct = true
        )
        addProductUseCase(product = productDomain)
    }

    override suspend fun addProduct(product: ProductDomain) : ResultWrapper {
        return addProductUseCase(product)
    }

    override suspend fun reduceProductQuantity(productId: Int) {
        val product = getProductByIdUseCase(productId)
        if(product.quantity > Constants.MIN_QUANTITY_ALLOWED){
            val newQuantity = product.quantity - 1
            val newProduct = product.copy(quantity = newQuantity)
            updateProductUseCase(newProduct)
        }
    }

    override suspend fun saveModifiedProduct(productId: Int) {
        val product = getProductByIdUseCase(productId)
        val newProduct = product.copy(quantity = tempProductDetailsView.productQuantity)
        updateProductUseCase(newProduct)
    }

    override fun modifyProductForView(action: ProductModification): ProductDetailsView {

        return when(action) {
            ProductModification.INCREASE_QUANTITY -> {
                if(tempProductDetailsView.productQuantity < Constants.MAX_QUANTITY_ALLOWED){
                    val newQuantity = tempProductDetailsView.productQuantity + 1
                    val newProduct = tempProductDetailsView.copy(productQuantity = newQuantity)
                    tempProductDetailsView = newProduct
                    newProduct
                } else {
                    tempProductDetailsView
                }
            }
            ProductModification.DECREASE_QUANTITY -> {
                if(tempProductDetailsView.productQuantity > Constants.MIN_QUANTITY_ALLOWED){
                    val newQuantity = tempProductDetailsView.productQuantity - 1
                    val newProduct = tempProductDetailsView.copy(productQuantity = newQuantity)
                    tempProductDetailsView = newProduct
                    newProduct
                } else {
                    tempProductDetailsView
                }
            }
        }
    }

    override suspend fun deleteProduct(productId: Int): ResultWrapper {
        return deleteProductUseCase(productId)
    }

}