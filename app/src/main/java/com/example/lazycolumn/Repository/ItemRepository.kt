package com.example.lazycolumn.Repository

import androidx.lifecycle.LiveData
import com.example.lazycolumn.Database.Products
import com.example.lazycolumn.Database.ProductsDao
import kotlinx.coroutines.flow.Flow

class ItemRepository(var productsDao: ProductsDao) {

    suspend fun getProducts() = productsDao.getAllproducts()
    suspend fun getproductsbyid(prodid:Int)=productsDao.getproductById(prodid)
    suspend fun insertProducts(products: Products) = productsDao.insertproducts(products)
    suspend fun deleteProduct(products: Products) = productsDao.deleteproducts(products)


}