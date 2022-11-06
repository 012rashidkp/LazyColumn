package com.example.lazycolumn.Network

import com.example.lazycolumn.Model.AuthResponse

data class LoginState(
    val loginresult:AuthResponse?=null,
    val loading: Boolean = false,
    val error: String? = null
)
