package com.example.lazycolumn.Data.Remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    @SerialName("userid")
    val userid: String?="",
    @SerialName("username")
    val username: String?="",
    @SerialName("email")
    val email: String?="",
    @SerialName("phone")
    val phone: String?="",
    @SerialName("city")
    val city: String?="",
    @SerialName("createdAt")
    val createdAt: String?="",
    @SerialName("is_superuser")
    val isSuperuser: Boolean?=false,
    @SerialName("authtoken")
    val authtoken: String?="",

):BaseResponse()