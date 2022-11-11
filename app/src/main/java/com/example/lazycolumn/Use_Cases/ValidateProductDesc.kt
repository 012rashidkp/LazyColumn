package com.example.lazycolumn.Use_Cases

class ValidateProductDesc {
    fun execute(productDesc:String):ValidateResult {
        if (productDesc.isBlank()) {
            return ValidateResult(
                successfull = false,
                ErrorMessage = "product description name Cannot be blank"
            )
        }
        return ValidateResult(successfull = true)
    }


}