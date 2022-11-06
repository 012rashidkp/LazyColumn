package com.example.lazycolumn.Repository

import com.example.lazycolumn.Model.DataOrException
import com.example.lazycolumn.Model.Products
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProductsRepository @Inject constructor(private val queryproductsByName:Query) {


    suspend fun getProductsFromFirestore(): DataOrException<List<Products>, Exception> {
        val dataOrException = DataOrException<List<Products>, Exception>()
        try {
            dataOrException.data = queryproductsByName.get().await().map { document ->
                document.toObject(Products::class.java)
            }
        } catch (e: FirebaseFirestoreException) {
            dataOrException.e = e
        }
        return dataOrException
    }


}