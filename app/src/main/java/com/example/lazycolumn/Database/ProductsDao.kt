package com.example.lazycolumn.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductsDao {
    @Query("SELECT * FROM PRODUCTS")
    fun getAllproducts():LiveData<List<Products>>

    @Query("SELECT * from products where productid = :prodid")
    fun getproductById(prodid: Int): Products?

    @Insert
    suspend fun insertproducts(products: Products)

    @Update
    suspend fun updateproducts(products: Products)

    @Delete
    suspend fun deleteproducts(products: Products)

    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()


}