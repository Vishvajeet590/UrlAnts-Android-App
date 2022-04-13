package com.example.urlants.presentation

sealed class Screen(val route : String){
    object LoginScreen: Screen("login_screen")
    object SignupScreen: Screen("Signup_Screen")
    object HomeScreen : Screen("Home_Screen")
    object ProfileScreen : Screen("Profile_Screen")

}