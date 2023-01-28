package com.example.lazycolumn.Injection

import com.example.lazycolumn.Common.Constants.Companion.NAME_PROPERTY
import com.example.lazycolumn.Common.Constants.Companion.PRODUCTS_COLLECTION
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideQueryProductsByName() = FirebaseFirestore.getInstance()
        .collection(PRODUCTS_COLLECTION)
        .orderBy(NAME_PROPERTY, Query.Direction.ASCENDING)



}