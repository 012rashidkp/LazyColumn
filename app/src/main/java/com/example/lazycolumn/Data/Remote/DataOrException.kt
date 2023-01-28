package com.example.lazycolumn.Data.Remote

data class DataOrException<T, E : Exception?>(
    var data: T? = null,
    var e: E? = null
)

