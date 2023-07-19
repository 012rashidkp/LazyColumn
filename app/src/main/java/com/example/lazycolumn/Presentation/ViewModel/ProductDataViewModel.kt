package com.example.lazycolumn.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lazycolumn.Repository.Local.ProductDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import demo.productdb.ProductEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDataViewModel @Inject constructor (private val productDataSource: ProductDataSource):ViewModel() {
    val products=productDataSource.getAllproducts()

    var productsdetails by mutableStateOf<ProductEntity?>(null)
        private set

  fun insertproducts(productName:String,productdesc:String,productprice:Double,productqty:Long,prod_image:ByteArray?){
      viewModelScope.launch {
          productDataSource.insertproduct(productName = productName, productdesc = productdesc, prodprice = productprice, prodqty = productqty, prod_image = prod_image!!)
      }
  }

  fun deleteproduct(id:Long){
      viewModelScope.launch {
          productDataSource.deleteproduct(id)
      }
  }

 fun getproductbyid(id:Long){
     viewModelScope.launch {
         productsdetails=productDataSource.getproductByid(id)
     }
 }





}