package com.example.lazycolumn.Data.Remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    @SerialName("created_at")
    val createdAt: String="",
    @SerialName("username")
    val username: String="",
    @SerialName("email")
    val email: String="",
    @SerialName("phone")
    val phone: String="",
    @SerialName("city")
   val city: String="",
   @SerialName("userid")
   val userid: String="",
   @SerialName("token")
   val token: String=""
): BaseResponse()