package com.example.lazycolumn.Navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lazycolumn.Screens.HomeScreen
import com.example.lazycolumn.Screens.LoginPage
import com.example.lazycolumn.Screens.RegisterPage
import com.example.lazycolumn.Screens.SplashScreen


@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route
    ) {
        composable(route = Screens.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route=Screens.Login.route){
            LoginPage(navController = navController)
        }
        composable(route=Screens.Register.route){
            RegisterPage(navController = navController)
        }
        composable(route = Screens.Home.route) {
           HomeScreen(navController = navController)
        }
    }
}