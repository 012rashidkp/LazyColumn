package com.example.lazycolumn.FormValidation

sealed class RegisterFormEvent{
    data class EmailChanged(val email:String):RegisterFormEvent()
    data class UserNameChanged(val username:String):RegisterFormEvent()
    data class PhoneChanged(val phone:String):RegisterFormEvent()
    data class CityChanged(val city:String):RegisterFormEvent()
    data class PasswordChanged(val password:String): RegisterFormEvent()
    object Register:RegisterFormEvent()
}
