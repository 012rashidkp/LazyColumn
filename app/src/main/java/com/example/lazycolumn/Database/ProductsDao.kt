package com.example.lazycolumn.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.concurrent.Flow

@Dao
interface ProductsDao {
    @Query("SELECT * FROM PRODUCTS")
    suspend  fun getAllproducts():List<Products>

    @Query("SELECT * from products where productid = :prodid")
    suspend fun getproductById(prodid: Int): Products

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertproducts(products: Products)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateproducts(products: Products)

    @Delete
    suspend  fun deleteproducts(products: Products)

    @Query("DELETE FROM products")
     fun deleteAllProducts()


}