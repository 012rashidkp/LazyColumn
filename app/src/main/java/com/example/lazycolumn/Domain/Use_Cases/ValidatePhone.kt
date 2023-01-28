package com.example.lazycolumn.Domain.Use_Cases

import android.util.Patterns

class ValidatePhone {
    fun execute(phone:String): ValidateResult {
        if (phone.isBlank()){
            return ValidateResult(successfull = false, ErrorMessage = "The phone Cannot be blank")
        }
        if (phone.length<10&&phone.length>12){
            return ValidateResult(successfull = false, ErrorMessage = "That's not a valid phone number")
        }
        return ValidateResult(successfull = true)

    }
}