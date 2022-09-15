package com.example.lazycolumn.ViewModelFactory


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lazycolumn.Network.ApiHelper
import com.example.lazycolumn.Repository.AuthRepository
import com.example.lazycolumn.ViewModel.AuthViewModel


class AuthViewModelFactory(private val apihelper: ApiHelper):ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(AuthRepository(apihelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }



}