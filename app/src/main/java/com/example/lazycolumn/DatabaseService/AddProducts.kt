package com.example.lazycolumn.DatabaseService

import com.example.lazycolumn.States.DataState
import com.example.lazycolumn.Database.Products
import com.example.lazycolumn.Repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AddProducts(private val itemRepository: ItemRepository) {
    operator fun invoke(products: Products): Flow<DataState<List<Products>>> = flow {

        emit(DataState.Loading)
        itemRepository.insertProducts(products)
        emit(DataState.Success(itemRepository.getProducts()))
    }
}