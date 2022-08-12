package com.example.dbm.inventoryappkt.domain.util

import com.example.dbm.inventoryappkt.R
import com.example.dbm.inventoryappkt.domain.model.ProductDomain
import com.example.dbm.inventoryappkt.presentation.model.ProductDetailsView
import com.example.dbm.inventoryappkt.presentation.model.ProductListView
import com.example.dbm.inventoryappkt.util.StringWrapper

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

    val categoryOptions: Map<String, StringWrapper> = mapOf(
        "sports" to StringWrapper.ResourceStringWrapper(id = R.string.sports_category),
        "technology" to StringWrapper.ResourceStringWrapper(id = R.string.technology_category),
        "furniture" to StringWrapper.ResourceStringWrapper(id = R.string.furniture_category),
        "clothing" to StringWrapper.ResourceStringWrapper(id = R.string.clothing_category),
        "other" to StringWrapper.ResourceStringWrapper(id = R.string.other_category),
    )
    val stockStatusOptions: Map<String, StringWrapper> = mapOf(
        "in_stock" to StringWrapper.ResourceStringWrapper(id = R.string.in_stock_status),
        "without_stock" to StringWrapper.ResourceStringWrapper(id = R.string.without_stock_status)
    )

    return ProductDetailsView(
        productId = this.id,
        productBrand = this.brand,
        productWarranty = this.warranty,
        productManufactureYear = this.manufactureYear,
        productWeight = this.weight,
        productPrice = this.price,
        productQuantity = this.quantity,
        productInStock = (if(this.inStock) stockStatusOptions["in_stock"] else stockStatusOptions["without_stock"]) ?: StringWrapper.ResourceStringWrapper(id = R.string.no_data_available_message),
        productName = this.name,
        productType = categoryOptions[this.type] ?: StringWrapper.ResourceStringWrapper(id = R.string.no_data_available_message),
        productImageUrl = this.imageUrl,
        isDummyProduct = this.isDummyProduct
    )
}