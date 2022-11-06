package com.example.lazycolumn.Use_Cases



class ValidatePassword {
    fun execute(password:String):ValidateResult{
        if (password.isBlank()){
    return ValidateResult(successfull = false, ErrorMessage = "The password Cannot be blank")
        }
      else  if (password.length<6){
            return ValidateResult(successfull = false, ErrorMessage = "password length atleast 6 character")
        }
        return ValidateResult(successfull = true)

    }
}