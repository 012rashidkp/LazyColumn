package com.example.lazycolumn.Navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lazycolumn.Screens.*


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
            EnterExitAnimation { LoginPage(navController = navController) }

        }
        composable(route=Screens.Register.route){
          EnterExitAnimation { RegisterPage(navController = navController) }

        }
        composable(route = Screens.Home.route) {
            EnterExitAnimation { HomeScreen(navController = navController) }

        }
        composable(route = Screens.ListPage.route) {
            EnterExitAnimation { ListScreen(navController=navController) }

        }
        composable(route = Screens.CreatePage.route) {
            EnterExitAnimation { CreateScreen(navController=navController) }

        }
    }
}