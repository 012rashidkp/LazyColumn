package com.example.lazycolumn.States

import com.example.lazycolumn.Model.AuthResponse

data class RegisterState (
    val register_response: AuthResponse?=null,
    val loading: Boolean = false,
    val error: String? = null
)