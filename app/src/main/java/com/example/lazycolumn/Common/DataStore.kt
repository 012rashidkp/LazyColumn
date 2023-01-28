package com.example.lazycolumn.Common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStore(private val context: Context) {

    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("storedata")
        val USER_EMAIL_KEY = stringPreferencesKey("user_email")
        val USER_PHONE_KEY= stringPreferencesKey("user_phone")
        val USER_NAME_KEY= stringPreferencesKey("user_name")
        val USER_CITY_KEY= stringPreferencesKey("user_city")
        val USER_TOKEN_KEY= stringPreferencesKey("user_token")


    }
    suspend fun saveEmail(name: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_EMAIL_KEY] = name
        }
    }

    val getEmail: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_EMAIL_KEY] ?: ""
        }

    suspend fun savePhone(phone: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_PHONE_KEY] = phone
        }
    }
    val getPhone: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_PHONE_KEY] ?: ""
        }

    suspend fun saveusername(username: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = username
        }
    }
    val getusername: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_NAME_KEY] ?: ""
        }

    suspend fun saveusercity(city: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_CITY_KEY] = city
        }
    }
    val getusercity: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_CITY_KEY] ?: ""
        }

    suspend fun saveusertoken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_TOKEN_KEY] = token
        }
    }
    val getusertoken: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_TOKEN_KEY] ?: ""
        }


}