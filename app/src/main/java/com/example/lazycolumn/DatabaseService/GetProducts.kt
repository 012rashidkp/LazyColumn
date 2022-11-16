package com.example.lazycolumn.DatabaseService

import com.example.lazycolumn.States.DataState
import com.example.lazycolumn.Database.Products
import com.example.lazycolumn.Repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetProducts(private val itemRepository: ItemRepository) {

    operator fun invoke(): Flow<DataState<List<Products>>> = flow {

        emit(DataState.Loading)
        emit(DataState.Success(itemRepository.getProducts()))
    }
}