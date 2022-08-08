package com.example.dbm.inventoryappkt.domain.util

import com.example.dbm.inventoryappkt.domain.model.ProductDomain
import com.example.dbm.inventoryappkt.presentation.model.ProductDetailsView
import com.example.dbm.inventoryappkt.presentation.model.ProductListView

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
        productInStock = this.inStock,
        productName = this.name,
        productType = this.type,
        productImageUrl = this.imageUrl,
        isDummyProduct = this.isDummyProduct
    )
}