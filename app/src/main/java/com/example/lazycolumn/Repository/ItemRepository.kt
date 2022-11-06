package com.example.lazycolumn.Repository

import androidx.lifecycle.LiveData
import com.example.lazycolumn.Database.Products
import com.example.lazycolumn.Database.ProductsDao

class ItemRepository(private val productsDao: ProductsDao) {

    suspend fun insert(item: Products) = productsDao.insertproducts(item)
    suspend fun delete(item: Products) = productsDao.deleteproducts(item)
    suspend fun update(item: Products) = productsDao.updateproducts(item)
    fun allProductinfo() = productsDao.getAllproducts()
    fun detailproductinfo(prodid:Int)=productsDao.getproductById(prodid)


}