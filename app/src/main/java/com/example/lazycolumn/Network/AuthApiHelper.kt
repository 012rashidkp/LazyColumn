package com.example.lazycolumn.Network

import com.example.lazycolumn.Model.AuthResponse
import com.example.lazycolumn.Repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthApiHelper(private val authApiService: AuthApiService):
    AuthRepository {
    override suspend fun Registeruser(
        username: String,
        email: String,
        phone: String,
        city: String,
        password: String
    ): Flow<Resource<AuthResponse>> = flow {
       try {
         emit(Resource.Loading())
          val authresult=authApiService.Registeruser(username,email,phone,city,password)
           emit(Resource.Success(data =authresult))
       }catch (e:Exception){
           emit(Resource.Error(e.message ?: "Error"))
       }

    }
}