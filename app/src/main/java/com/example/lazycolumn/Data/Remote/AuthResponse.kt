package com.example.lazycolumn.Data.Remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    @SerialName("error")
    val error: Boolean?,
    @SerialName("message")
    val message: String?,
    @SerialName("userid")
    val userid: Int?,
    @SerialName("username")
    val username: String?,
    @SerialName("email")
    val email: String?,
    @SerialName("phone")
    val phone: String?,
    @SerialName("city")
    val city: String?,
    @SerialName("createdAt")
    val createdAt: String?,
    @SerialName("is_superuser")
    val isSuperuser: Boolean?,
    @SerialName("authtoken")
    val authtoken: String?,
    @SerialName("statuscode")
    val statuscode: Int?,
    @SerialName("status_description")
    val statusDescription: String?,
    @SerialName("current_time")
    val currentTime: String?
)