package com.example.lazycolumn.ViewModel

import androidx.lifecycle.ViewModel
import com.example.lazycolumn.Database.Products
import com.example.lazycolumn.Repository.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ItemViewModel @Inject constructor(private val repository: ItemRepository):ViewModel() {

    suspend fun insert(item: Products) = GlobalScope.launch {
        repository.insert(item)
    }

    // In coroutines thread delete item in delete function.
    suspend  fun delete(item: Products) = GlobalScope.launch {
        repository.delete(item)
    }
    suspend fun update(item: Products) = GlobalScope.launch {
        repository.update(item)
    }
    //Here we initialized allGroceryItems function with repository
    fun allProductinfo() = repository.allProductinfo()

    fun detailproduct(prodid:Int)=repository.detailproductinfo(prodid)


}