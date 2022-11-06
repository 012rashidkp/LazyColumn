package com.example.lazycolumn.Use_Cases

import android.util.Patterns

class ValidateEmail {
    fun execute(email:String):ValidateResult{
        if (email.isBlank()){
return ValidateResult(successfull = false, ErrorMessage = "The Email Cannot be blank")
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return ValidateResult(successfull = false, ErrorMessage = "That's not a valid Email")
        }
        return ValidateResult(successfull = true)

    }
}