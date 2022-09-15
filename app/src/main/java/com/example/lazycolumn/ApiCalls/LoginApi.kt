package com.example.lazycolumn.ApiCalls

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.lazycolumn.Activity.MainActivity
import com.example.lazycolumn.Navigation.Screens
import com.example.lazycolumn.Network.ApiClient
import com.example.lazycolumn.Network.ApiHelper
import com.example.lazycolumn.Network.Status
import com.example.lazycolumn.Screens.RegisterPage
import com.example.lazycolumn.ViewModel.AuthViewModel
import com.example.lazycolumn.ViewModelFactory.AuthViewModelFactory

fun Loginuser(activity: MainActivity,email:String,password:String,navController: NavController){
    lateinit var authViewModel: AuthViewModel


    authViewModel= ViewModelProvider(activity, AuthViewModelFactory(ApiHelper(ApiClient.invoke()))
    )[AuthViewModel::class.java]


    authViewModel.getLogin(email,password).observe(activity, Observer {
        it.let { response->
            when(response.status){
                Status.LOADING->{

                }
                Status.SUCCESS->{
                    response.data.let { result->
                        if (!result!!.error){
                           navController.navigate(Screens.Login.route)
                        }
                        else if (result!!.error){

                        }

                    }
                }
                Status.ERROR->{

                }




            }
        }
    })




}