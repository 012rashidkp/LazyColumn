package com.example.lazycolumn.Model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class BaseResponse {
    @SerialName("error")
    val error: Boolean=false
    @SerialName("status_code")
    val status_code:String=""
    @SerialName("message")
    val message: String=""
}