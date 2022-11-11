package com.example.lazycolumn.Injection

import com.example.lazycolumn.DatabaseService.AddProducts
import com.example.lazycolumn.DatabaseService.DeleteProducts
import com.example.lazycolumn.DatabaseService.GetProducts
import com.example.lazycolumn.DatabaseService.GetproductsByid
import com.example.lazycolumn.Repository.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorModule {
    @Singleton
    @Provides
    fun getAdProducts(itemRepository: ItemRepository) = AddProducts(itemRepository)

    @Singleton
    @Provides
    fun getAllproducts(itemRepository: ItemRepository) = GetProducts(itemRepository)

    @Singleton
    @Provides
    fun getProductsByid(itemRepository: ItemRepository) = GetproductsByid(itemRepository)


    @Singleton
    @Provides
    fun getDeleteProducts(itemRepository: ItemRepository) = DeleteProducts(itemRepository)


}