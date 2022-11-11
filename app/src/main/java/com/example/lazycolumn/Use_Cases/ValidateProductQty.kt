package com.example.lazycolumn.Use_Cases

class ValidateProductQty {
    fun execute(productqty:String):ValidateResult {
        if (productqty.isBlank()) {
            return ValidateResult(
                successfull = false,
                ErrorMessage = "product qty Cannot be blank"
            )
        }
        else if (productqty.equals("0")){
            return ValidateResult(
                successfull = false,
                ErrorMessage = "product qty atleast one"
            )
        }

        return ValidateResult(successfull = true)
    }
}