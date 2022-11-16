package com.example.lazycolumn.ViewModel

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lazycolumn.DatabaseService.AddProducts
import com.example.lazycolumn.DatabaseService.DeleteProducts
import com.example.lazycolumn.DatabaseService.GetProducts
import com.example.lazycolumn.States.DataState
import com.example.lazycolumn.Database.Products
import com.example.lazycolumn.DatabaseService.GetproductsByid
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class ItemViewModel @Inject constructor(private val addProducts: AddProducts,private val getProducts: GetProducts,private val deleteProducts: DeleteProducts,private val getproductsByid: GetproductsByid):ViewModel() {
    private var _listproducts = SnapshotStateList<Products>()
    val listProducts = _listproducts
    private var detailItems=MutableLiveData<Products>()
    var listdetailproducts =detailItems


init {
    getProducts().onEach { dataState ->
        when (dataState) {
            is DataState.Success -> {
                _listproducts.clear()
                _listproducts.addAll(dataState.data)
            }
        }
    }.launchIn(viewModelScope)
}
    fun addProducts(productName:String,productdesc:String,productprice:Double,productqty:Long) {

        addProducts(Products(0, productName = productName, productdesc = productdesc, productprice = productprice, productqty = productqty)).onEach { dataState ->
            when (dataState) {
                is DataState.Success -> {
                    _listproducts.clear()
                    _listproducts.addAll(dataState.data)

                }
                is DataState.Loading -> {
                }
                is DataState.Error -> {
                }
            }
        }.launchIn(viewModelScope)
    }
    fun getproductsbyid(prodid: Int){
        getproductsByid(prodid = prodid).onEach {
            when (it) {
                is DataState.Success -> {
                    listdetailproducts.value=it.data

                }
                is DataState.Loading -> {
                }
                is DataState.Error -> {
                }
            }
        }.launchIn(viewModelScope)
    }
    fun deleteproduct(products: Products){
        deleteProducts(products).onEach {
            when (it) {
                is DataState.Success -> {
                    _listproducts.clear()
                    _listproducts.addAll(it.data)

                }
                is DataState.Loading -> {
                }
                is DataState.Error -> {
                }
            }
        }.launchIn(viewModelScope)
    }
}