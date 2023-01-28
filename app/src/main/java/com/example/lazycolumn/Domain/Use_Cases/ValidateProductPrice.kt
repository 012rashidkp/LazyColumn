package com.example.lazycolumn.Domain.Use_Cases

class ValidateProductPrice {
    fun execute(productprice:String): ValidateResult {
        if (productprice.isBlank()) {
            return ValidateResult(
                successfull = false,
                ErrorMessage = "product price Cannot be blank"
            )
        }
        else if (productprice.equals("0")){
            return ValidateResult(
                successfull = false,
                ErrorMessage = "product price atleast 1"
            )
        }
        return ValidateResult(successfull = true)
    }
}