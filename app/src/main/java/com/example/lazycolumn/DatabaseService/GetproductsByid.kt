package com.example.lazycolumn.DatabaseService

import com.example.lazycolumn.Database.DataState
import com.example.lazycolumn.Database.Products
import com.example.lazycolumn.Repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetproductsByid(private val itemRepository: ItemRepository) {
    operator fun invoke(prodid:Int) : Flow<DataState<Products>> = flow {
        emit(DataState.Loading)
        itemRepository.getproductsbyid(prodid)
        emit(DataState.Success(itemRepository.getproductsbyid(prodid)))
    }

}