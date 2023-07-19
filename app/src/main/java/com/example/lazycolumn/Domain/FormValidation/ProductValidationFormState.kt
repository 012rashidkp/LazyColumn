package com.example.lazycolumn.FormValidation

import android.net.Uri

data class ProductValidationFormState(
    val productimage: Uri? = null,
    val productImageError:String?=null,
    val productName:String="",
    val productNameError:String?=null,
    val productdesc:String="",
    val productdescError:String?=null,
    val productprice:String="",
    val productpriceError:String?=null,
    val productqty:String="",
    val productqtyError:String?=null

)
