package com.example.lazycolumn.Domain.Use_Cases

import android.util.Patterns

class ValidateCityName {
    fun execute(cityName:String): ValidateResult {
        if (cityName.isBlank()){
            return ValidateResult(successfull = false, ErrorMessage = "The city Cannot be blank")
        }

        return ValidateResult(successfull = true)

    }
}