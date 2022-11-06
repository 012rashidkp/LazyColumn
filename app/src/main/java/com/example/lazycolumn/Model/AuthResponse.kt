package com.example.lazycolumn.Model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class AuthResponse(
    @SerialName("username")
    val username: String,
    @SerialName("email")
    val email: String,
    @SerialName("phone")
    val phone: String,
    @SerialName("city")
    val city: String,
    @SerialName("token")
    val token: String,
    @SerialName("userid")
    val userid: String
):BaseResponse()
