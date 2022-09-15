package com.example.lazycolumn.Navigation

sealed class Screens(val route: String) {
    object Splash : Screens("splash_screen")
    object Home : Screens("home_screen")
    object Login:Screens("login")
    object Register:Screens("Register")
}
