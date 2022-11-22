package com.example.lazycolumn.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lazycolumn.Model.AuthResponse
import com.example.lazycolumn.Network.AuthApiService
import com.example.lazycolumn.Repository.AuthRepository
import com.example.lazycolumn.Network.Resource
import com.example.lazycolumn.States.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor (private val authrepository: AuthRepository):ViewModel(){
    private val _state = mutableStateOf(RegisterState())
    val state: State<RegisterState> = _state





    suspend fun Registeruser(username:String, email:String, phone: String, city: String, password: String){
        authrepository.Registeruser(username = username, email = email, phone = phone, city = city, password = password).onEach { result->
            when (result) {
                is Resource.Success -> {
                    _state.value = RegisterState(register_response = result.data!!, loading = false, error = null)
                }

                is Resource.Loading -> {
                    _state.value = RegisterState(register_response  = null, loading = true, error = null)
                }

                is Resource.Error -> {
                    _state.value = RegisterState(register_response = null, loading = false, error = result.message)
                }
            }
        }.launchIn(viewModelScope)
    }
}