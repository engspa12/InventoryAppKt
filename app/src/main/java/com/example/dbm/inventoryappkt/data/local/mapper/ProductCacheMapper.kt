package com.example.dbm.inventoryappkt.data.local.mapper

import com.example.dbm.inventoryappkt.data.local.model.ProductCache
import com.example.dbm.inventoryappkt.domain.model.ProductDomain
import com.example.dbm.inventoryappkt.domain.util.CacheMapper

class ProductCacheMapper: CacheMapper<ProductCache, ProductDomain> {

    override fun mapToDomainModel(dto: ProductCache): ProductDomain {
        return ProductDomain(
            id = dto.id,
            brand = dto.brand,
            warranty = dto.warranty,
            manufactureYear = dto.manufactureYear,
            weight = dto.weight,
            price = dto.price,
            quantity = dto.quantity,
            inStock = dto.inStock,
            name = dto.name,
            type = dto.type,
            imageUrl = dto.imageUrl,
            imageUrlStorageLocation = dto.imageUrlStorageLocation
        )
    }

    override fun mapFromDomainModel(domainModel: ProductDomain): ProductCache {
        return ProductCache(
            id = domainModel.id,
            brand = domainModel.brand,
            warranty = domainModel.warranty,
            manufactureYear = domainModel.manufactureYear,
            weight = domainModel.weight,
            price = domainModel.price,
            quantity = domainModel.quantity,
            inStock = domainModel.inStock,
            name = domainModel.name,
            type = domainModel.type,
            imageUrl = domainModel.imageUrl,
            imageUrlStorageLocation = domainModel.imageUrlStorageLocation
        )
    }

}