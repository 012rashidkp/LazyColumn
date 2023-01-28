package com.example.lazycolumn.Domain.Network

import com.example.lazycolumn.Data.Remote.AuthResponse


interface AuthApiService {
    suspend fun Registeruser(username:String,email:String,phone:String,city:String,password:String): AuthResponse

}