package com.example.lazycolumn.Activity

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.navigation.compose.rememberNavController
import com.example.lazycolumn.Navigation.SetupNavGraph
import com.example.lazycolumn.ui.theme.AnimatedSplashScreenTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            AnimatedSplashScreenTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}







