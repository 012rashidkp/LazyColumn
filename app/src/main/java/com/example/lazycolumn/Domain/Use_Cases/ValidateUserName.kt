package com.example.lazycolumn.Domain.Use_Cases

import android.util.Patterns

class ValidateUserName {
    fun execute(userName:String): ValidateResult {
        if (userName.isBlank()){
            return ValidateResult(successfull = false, ErrorMessage = "The username Cannot be blank")
        }
        return ValidateResult(successfull = true)

    }
}