package com.example.lazycolumn.Domain.Network

import com.example.lazycolumn.Data.Remote.AuthResponse
import com.example.lazycolumn.Domain.Network.API.Companion.Registerusers
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*

class AuthApiServiceImpl (private val client: HttpClient): AuthApiService {
    override suspend fun Registeruser(
        username: String,
        email: String,
        phone: String,
        city: String,
        password: String
    ): AuthResponse {
        return client.post(Registerusers){

            body= FormDataContent(Parameters.build {
                append("username",username)
                append("email",email)
                append("phone",phone)
                append("city",city)
                append("password",password)
            })

        }
    }
}