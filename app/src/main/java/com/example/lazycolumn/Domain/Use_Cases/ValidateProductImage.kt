package com.example.lazycolumn.Domain.Use_Cases

import android.net.Uri

class ValidateProductImage {
    fun execute(prodimage:Uri?): ValidateResult {
        if (prodimage==null){
            return ValidateResult(successfull = false, ErrorMessage = "product image Cannot be blank")
        }

        return ValidateResult(successfull = true)

    }
}