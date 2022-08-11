package com.example.dbm.inventoryappkt.domain.service

import com.example.dbm.inventoryappkt.domain.model.ProductDomain
import com.example.dbm.inventoryappkt.domain.usecase.*
import com.example.dbm.inventoryappkt.domain.util.ProductModification
import com.example.dbm.inventoryappkt.domain.util.toDetailsView
import com.example.dbm.inventoryappkt.domain.util.toListView
import com.example.dbm.inventoryappkt.presentation.model.ProductDetailsView
import com.example.dbm.inventoryappkt.presentation.model.ProductListView
import com.example.dbm.inventoryappkt.presentation.state.ProductInputState
import javax.inject.Inject

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
            price = 25.00,
            quantity = 10,
            inStock = true,
            name = "Men's Shirt",
            type = "Clothing",
            imageUrl = "",
            imageUrlStorageLocation = "",
            isDummyProduct = true
        )
        addProductUseCase(product = productDomain)
    }

    override suspend fun addProduct(product: ProductInputState) : String {
        val productDomain = ProductDomain(
            brand = "DBM",
            warranty = 180,
            manufactureYear = 2022,
            weight = 0.15,
            price = 25.00,
            quantity = 10,
            inStock = true,
            name = "Men's Shirt",
            type = "Clothing",
            imageUrl = "",
            imageUrlStorageLocation = "",
            isDummyProduct = true
        )
        //addProductUseCase()
        return "Success"
    }

    override suspend fun reduceProductQuantity(productId: Int) {
        val product = getProductByIdUseCase(productId)
        val newQuantity = product.quantity - 1
        if(newQuantity > 0){
            val newProduct = product.copy(quantity = product.quantity - 1)
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
                if(tempProductDetailsView.productQuantity < 10){
                    val newQuantity = tempProductDetailsView.productQuantity + 1
                    val newProduct = tempProductDetailsView.copy(productQuantity = newQuantity)
                    tempProductDetailsView = newProduct
                    newProduct
                } else {
                    tempProductDetailsView
                }
            }
            ProductModification.DECREASE_QUANTITY -> {
                if(tempProductDetailsView.productQuantity > 1){
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

    override suspend fun deleteProduct(productId: Int) {
        deleteProductUseCase(productId)
    }

}