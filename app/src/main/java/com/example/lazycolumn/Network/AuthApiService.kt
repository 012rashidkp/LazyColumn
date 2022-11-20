package com.example.lazycolumn.Network

import com.example.lazycolumn.Model.AuthResponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*


interface AuthApiService {
    suspend fun Registeruser(username:String,email:String,phone:String,city:String,password:String): AuthResponse




//    companion object {
//
//        fun create(): AuthApiService {
//            return AuthApiClient(
//                client = HttpClient(Android) {
//                    install(Logging){
//                        level=LogLevel.ALL
//                    }
//                    install(JsonFeature) {
//                        serializer = KotlinxSerializer()
//                    }
//                }
//            )
//        }
//    }
}