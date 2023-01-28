package com.example.lazycolumn.ViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lazycolumn.Data.Remote.DataOrException
import com.example.lazycolumn.Data.Remote.Products
import com.example.lazycolumn.Repository.Remote.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val productsRepository: ProductsRepository):ViewModel(){
    var loading = mutableStateOf(false)
    val data: MutableState<DataOrException<List<Products>, Exception>> = mutableStateOf(
        DataOrException(
            listOf(),
            Exception("")
        )
    )

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            loading.value = true
            data.value = productsRepository.getProductsFromFirestore()
            loading.value = false
        }
    }


}