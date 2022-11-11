package com.example.lazycolumn.Use_Cases

class ValidateProductName {

    fun execute(productName:String):ValidateResult {
        if (productName.isBlank()) {
            return ValidateResult(
                successfull = false,
                ErrorMessage = "product name Cannot be blank"
            )
        }
        return ValidateResult(successfull = true)
    }
}