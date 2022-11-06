package com.example.lazycolumn.Injection

import android.content.Context
import com.example.lazycolumn.Database.ProductsDao
import com.example.lazycolumn.Database.ProductsDatabase
import com.example.lazycolumn.Repository.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDao(@ApplicationContext context : Context) = ProductsDatabase.getDatabase(context).productsDao()

    @Singleton
    @Provides
    fun provideDbRepository(productsDao: ProductsDao) = ItemRepository(productsDao)
}