package com.example.dbm.inventoryappkt.data.local.datasource

import androidx.room.*
import com.example.dbm.inventoryappkt.data.local.model.ProductCache

@Database(
    entities = [
        ProductCache::class
    ], version = 1, exportSchema = false)
abstract class ProductsRoomDatabase: RoomDatabase() {
    abstract fun productsDao(): ProductsDao
}

@Dao
interface ProductsDao{

    @Query("SELECT * FROM products")
    fun getProducts(): List<ProductCache>

    @Query("SELECT * FROM products WHERE id = :productId")
    fun getProductById(productId: Int): ProductCache

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProduct(vararg productCache: ProductCache)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateProduct(productCache: ProductCache)

    @Query("DELETE FROM products WHERE id = :productId")
    fun deleteProductById(productId: Int)

}