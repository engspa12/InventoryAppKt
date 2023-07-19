package com.example.dbm.inventoryappkt.domain.util

import com.example.dbm.inventoryappkt.domain.model.ProductDomain
import com.example.dbm.inventoryappkt.presentation.model.ProductDetailsView
import com.example.dbm.inventoryappkt.presentation.model.ProductListView
import com.example.dbm.inventoryappkt.presentation.util.ProductType
import com.example.dbm.inventoryappkt.presentation.util.StockType

interface CacheMapper<Dto, DomainModel> {
    fun mapToDomainModel(dto: Dto): DomainModel
    fun mapFromDomainModel(domainModel: DomainModel): Dto
}

fun ProductDomain.toListView(): ProductListView {
    return ProductListView(
        productId = this.id,
        productName = this.name,
        productQuantity = this.quantity,
        productPrice = this.price,
        productUrlImage = this.imageUrl,
        isDummyProduct = this.isDummyProduct
    )
}

fun ProductDomain.toDetailsView(): ProductDetailsView {

    return ProductDetailsView(
        productId = this.id,
        productBrand = this.brand,
        productWarranty = this.warranty,
        productManufactureYear = this.manufactureYear,
        productWeight = this.weight,
        productPrice = this.price,
        productQuantity = this.quantity,
        productInStock = this.inStock.getStockType(),
        productName = this.name,
        productType = this.type.getProductType(),
        productImageUrl = this.imageUrl,
        isDummyProduct = this.isDummyProduct
    )
}

fun String.getProductType(): ProductType{
    return when(this) {
        "sports" -> ProductType.SPORTS
        "technology" -> ProductType.TECHNOLOGY
        "furniture" -> ProductType.FURNITURE
        "clothing" -> ProductType.CLOTHING
        else -> ProductType.OTHER
    }
}

fun Boolean.getStockType(): StockType {
    return if(this) StockType.IN_STOCK else StockType.WITHOUT_STOCK
}