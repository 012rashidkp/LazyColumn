package com.example.lazycolumn.Data.Remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class BaseResponse {
    @SerialName("error")
    val error: Boolean=false
    @SerialName("message")
    val message: String=""
    @SerialName("status_code")
    val status_code:String=""
    @SerialName("time")
    val time:String=""

}