package com.example.lazycolumn.Repository

import com.example.lazycolumn.Database.ProductDataSource
import com.example.lazycolumn.ItemsDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import demo.productdb.ProductEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ProductDataRepository(database:ItemsDatabase): ProductDataSource {
    private val queries=database.productEntityQueries
    override fun getAllproducts(): Flow<List<ProductEntity>> {
        return queries.getAllproducts().asFlow().mapToList()
    }

    override suspend fun getproductByid(id: Long): ProductEntity? {
      return withContext(Dispatchers.IO){
          queries.getproductbyid(id).executeAsOneOrNull()
      }
    }

    override suspend fun deleteproduct(id: Long) {
        withContext(Dispatchers.IO){
            queries.deleteproduct(id)
        }
    }

    override suspend fun insertproduct(
        id: Long?,
        productName: String,
        productdesc: String,
        prodprice: Double,
        prodqty: Long
    ) {
      withContext(Dispatchers.IO){
          queries.insertproduct(id,productName,productdesc,prodprice,prodqty)
      }


    }


}