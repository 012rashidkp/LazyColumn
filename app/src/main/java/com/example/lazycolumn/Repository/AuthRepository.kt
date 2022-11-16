package com.example.lazycolumn.Repository

import com.example.lazycolumn.Model.AuthResponse
import com.example.lazycolumn.Network.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun Registeruser(username:String,email:String,phone:String,city:String,password:String):Flow<Resource<AuthResponse>>


}