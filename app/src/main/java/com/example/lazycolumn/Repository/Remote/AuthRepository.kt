package com.example.lazycolumn.Repository.Remote

import com.example.lazycolumn.Data.Remote.AuthResponse
import com.example.lazycolumn.Domain.Network.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun Registeruser(username:String,email:String,phone:String,city:String,password:String):Flow<Resource<AuthResponse>>


}