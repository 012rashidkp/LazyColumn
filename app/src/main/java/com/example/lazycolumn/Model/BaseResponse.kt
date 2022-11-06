package com.example.lazycolumn.Model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class BaseResponse {
    @SerialName("error")
    val error: Boolean=false
    @SerialName("message")
    val message: String=""
}