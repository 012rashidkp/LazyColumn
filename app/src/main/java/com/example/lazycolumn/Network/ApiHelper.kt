package com.example.lazycolumn.Network

class ApiHelper(val apiService: APIService) {
    suspend fun Resgisteruser(username:String, email: String, phone:String, city:String, password: String)=apiService.RegisterUser(username,email,phone,city,password)

    suspend fun Loginuser(email:String,password:String)=apiService.LoginUser(email,password)
}