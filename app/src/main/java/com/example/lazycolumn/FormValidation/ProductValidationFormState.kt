package com.example.lazycolumn.FormValidation

data class ProductValidationFormState(
    val productName:String="",
    val productNameError:String?=null,
    val productdesc:String="",
    val productdescError:String?=null,
    val productprice:String="",
    val productpriceError:String?=null,
    val productqty:String="",
    val productqtyError:String?=null

)
