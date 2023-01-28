package com.example.lazycolumn.Repository.Remote

import com.example.lazycolumn.Data.Remote.AuthResponse
import com.example.lazycolumn.Domain.Network.AuthApiService
import com.example.lazycolumn.Domain.Network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepositoryImpl(private val authApiService: AuthApiService):
    AuthRepository {
    override suspend fun Registeruser(
        username: String,
        email: String,
        phone: String,
        city: String,
        password: String
    ): Flow<Resource<AuthResponse>> = flow {
        emit(Resource.Loading(data = null))
       try {
        //  val authresult=authApiService.Registeruser(username,email,phone,city,password)
           emit(
               Resource.Success(
                   data = authApiService.Registeruser(
                       username,
                       email,
                       phone,
                       city,
                       password
                   )
               )
           )
       }catch (e:Exception){
           emit(Resource.Error(data = null, message = e.message ?: "Error Occurred!"))
       }

    }
}