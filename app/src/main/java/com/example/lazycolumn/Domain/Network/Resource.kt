package com.example.lazycolumn.Domain.Network

sealed class Resource<out T>(val status: Status, val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(status = Status.SUCCESS, data = data,message = null)
    class Error<T>(data: T? = null, message: String) : Resource<T>(status = Status.ERROR, data = data, message = message)
    class Loading<T>(data: T? = null) : Resource<T>(status = Status.LOADING, data = data,message = null)
}
