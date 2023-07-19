package com.example.lazycolumn.Data.Remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class BaseResponse {
    @SerialName("error")
    val error: Boolean?=false
    @SerialName("message")
    val message: String?=""
    @SerialName("statuscode")
    val statuscode:String=""
    @SerialName("status_description")
    val status_description:String?=""
    @SerialName("current_time")
    val current_time:String?=""
}