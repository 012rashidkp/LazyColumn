package com.example.lazycolumn.Repository

import com.example.lazycolumn.Network.ApiHelper

class AuthRepository(val apiHelper: ApiHelper) {
    suspend fun Resgisteruser(username:String, email: String, phone:String, city:String, password: String)=apiHelper.Resgisteruser(username,email,phone,city,password)

    suspend fun LoginUser(email:String,password:String)=apiHelper.Loginuser(email,password)
}