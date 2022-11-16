package com.example.lazycolumn.FormValidation

data class RegisterValidationFormState(
    val email:String="",
    val emailError:String?=null,
    val username:String="",
    val usernameError:String?=null,
    val phone:String="",
    val phoneError:String?=null,
    val city:String="",
    val cityError:String?=null,
    val password:String="",
    val passwordError:String?=null
)
