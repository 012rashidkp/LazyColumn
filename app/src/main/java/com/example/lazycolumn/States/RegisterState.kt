package com.example.lazycolumn.States

import com.example.lazycolumn.Model.AuthResponse

data class RegisterState (
    val error:Boolean?=null,
    val message:String?=null,
    val register_response: AuthResponse?=null,
    val loading: Boolean = false,
    val failure: String? = null
)