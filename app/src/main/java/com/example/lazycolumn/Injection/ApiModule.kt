package com.example.lazycolumn.Injection

import com.example.lazycolumn.Network.AuthApiClient
import com.example.lazycolumn.Network.AuthApiHelper
import com.example.lazycolumn.Network.AuthApiService
import com.example.lazycolumn.Repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }

        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    @Provides
    @Singleton
    fun provideauthApi(client: HttpClient): AuthApiService = AuthApiClient(client)

    @Provides
    fun provideauthRepository(api: AuthApiService):AuthRepository = AuthApiHelper(api)

}