package com.example.lazycolumn.FormValidation

data class LoginValidationFormState(
    val email:String="",
    val emailError:String?=null,
    val password:String="",
    val passwordError:String?=null
)
