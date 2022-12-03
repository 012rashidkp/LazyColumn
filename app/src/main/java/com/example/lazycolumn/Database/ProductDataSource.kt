package com.example.lazycolumn.Database

import demo.productdb.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductDataSource {
 fun getAllproducts():Flow<List<ProductEntity>>
 suspend fun getproductByid(id:Long):ProductEntity?

 suspend fun deleteproduct(id: Long)

 suspend fun insertproduct(id: Long?=null,productName:String,productdesc:String,prodprice:Double,prodqty:Long)




}