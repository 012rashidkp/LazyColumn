package com.example.lazycolumn.Domain.Use_Cases

class ValidateProductDesc {
    fun execute(productDesc:String): ValidateResult {
        if (productDesc.isBlank()) {
            return ValidateResult(
                successfull = false,
                ErrorMessage = "product description Cannot be blank"
            )
        }
        return ValidateResult(successfull = true)
    }


}