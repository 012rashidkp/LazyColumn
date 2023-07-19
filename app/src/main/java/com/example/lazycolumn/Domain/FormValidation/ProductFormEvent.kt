package com.example.lazycolumn.FormValidation

import android.net.Uri

sealed class ProductFormEvent{

    data class ProductImageChanged(val productImage:Uri):ProductFormEvent()
    data class ProductNameChanged(val productName:String): ProductFormEvent()
    data class productdescChanged(val productdesc:String): ProductFormEvent()
    data class productpricechanged(val productprice:String):ProductFormEvent()
    data class productqtychanged(val productqty: String):ProductFormEvent()
    object Saveproducts: ProductFormEvent()
}
