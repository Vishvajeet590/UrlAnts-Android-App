package com.example.urlants

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.urlants.common.Constants
import com.example.urlants.common.SessionManager
//import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.example.urlants.presentation.Screen
import com.example.urlants.presentation.homeScreen.HomeScreenViewModel
import com.example.urlants.presentation.homeScreen.component.HomeScreen
import com.example.urlants.presentation.loginScreen.component.LoginScreen
import com.example.urlants.presentation.profileScreen.component.ProfileScreen
import com.example.urlants.presentation.signupScreen.component.SignUpScreen
import com.example.urlants.presentation.ui.theme.UrlAntsTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import dagger.hilt.android.AndroidEntryPoint
//import com.google.accompanist.navigation.animation.composable
//import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  //  @OptIn(ExperimentalMaterialApi::class)


    var homeScreenArg: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkLogedIn()

        setContent {
            UrlAntsTheme(darkTheme = true) {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "${Screen.HomeScreen.route}/{${Constants.PARAM_Home_Screen}}"
                ) {

                    composable(
                        route = "${Screen.HomeScreen.route}/{${Constants.PARAM_Home_Screen}}",
                        arguments = listOf(
                            navArgument(Constants.PARAM_Home_Screen) {
                                defaultValue = Constants.PARAM_HOME_SCREEN_Logout_ARG
                                type = NavType.StringType
                            }
                        )

                    ) { backStackEntry->
                        val homViewModel = hiltViewModel<HomeScreenViewModel>()
                        HomeScreen(navController = navController, arg = homeScreenArg,viewModel = homViewModel)
                    }

                    composable(
                        route = Screen.LoginScreen.route,
                    ) {
                        LoginScreen(navController = navController)
                    }

                    composable(
                        route = Screen.SignupScreen.route,
                    ) {
                        SignUpScreen(navController = navController)
                    }

                    composable(
                        route = Screen.ProfileScreen.route,
                    ) {
                        ProfileScreen(navController = navController)
                    }


                }


            }


        }
    }


    private fun checkLogedIn() {
        val sessionManager = SessionManager(applicationContext)
        var user = sessionManager.fetchUserName()
        var jwt = sessionManager.fetchAuthToken()
        if (user.isNullOrEmpty() || jwt.isNullOrEmpty()) {
            Log.d("MainActivity", "Setting route to Login")
            homeScreenArg = Constants.PARAM_HOME_SCREEN_Logout_ARG
            //  _state.value = HomeScreenState(login_profile_nav = Screen.LoginScreen.route)
        } else {
            homeScreenArg = Constants.PARAM_HOME_SCREEN_Login_ARG
        }
    }

}


//
//composable(
//route = "${Screen.HomeScreen.route}/{${Constants.PARAM_Home_Screen}}",
//enterTransition = {
//    when (initialState.destination.route) {
//        Screen.SignupScreen.route ->
//            slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
//        else -> null
//    }
//},
//exitTransition = {
//    when (targetState.destination.route) {
//        Screen.SignupScreen.route ->
//            slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
//        else -> null
//    }
//},
//popEnterTransition = {
//    when (initialState.destination.route) {
//        Screen.SignupScreen.route ->
//            slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
//        else -> null
//    }
//},
//popExitTransition = {
//    when (targetState.destination.route) {
//        Screen.SignupScreen.route ->
//            slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
//        else -> null
//    }
//},
//arguments = listOf(
//navArgument(Constants.PARAM_Home_Screen){
//    defaultValue = Constants.PARAM_HOME_SCREEN_Logout_ARG
//    type = NavType.StringType
//}
//)
//
//){
//
//    HomeScreen(navController = navController,arg = homeScreenArg)
//}
//
//composable(
//route = Screen.LoginScreen.route,
//enterTransition = {
//    when (initialState.destination.route) {
//        Screen.SignupScreen.route ->
//            slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
//        else -> null
//    }
//},
//exitTransition = {
//    when (targetState.destination.route) {
//        Screen.SignupScreen.route ->
//            slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
//        else -> null
//    }
//},
//popEnterTransition = {
//    when (initialState.destination.route) {
//        Screen.SignupScreen.route ->
//            slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
//        else -> null
//    }
//},
//popExitTransition = {
//    when (targetState.destination.route) {
//        Screen.SignupScreen.route ->
//            slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
//        else -> null
//    }
//}
//
//){
//    LoginScreen(navController = navController)
//}
//
//composable(
//Screen.SignupScreen.route,
//enterTransition = {
//    when (initialState.destination.route) {
//        Screen.LoginScreen.route->
//            slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
//        else -> null
//    }
//},
//exitTransition = {
//    when (targetState.destination.route) {
//        Screen.LoginScreen.route ->
//            slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
//        else -> null
//    }
//},
//popEnterTransition = {
//    when (initialState.destination.route) {
//        Screen.LoginScreen.route ->
//            slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
//        else -> null
//    }
//},
//popExitTransition = {
//    when (targetState.destination.route) {
//        Screen.LoginScreen.route ->
//            slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
//        else -> null
//    }
//}
//){
//    SignUpScreen(navController = navController)
//}
//
//
//
//composable(
//Screen.ProfileScreen.route,
//enterTransition = {
//    when (initialState.destination.route) {
//        Screen.LoginScreen.route->
//            slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
//        else -> null
//    }
//},
//exitTransition = {
//    when (targetState.destination.route) {
//        Screen.LoginScreen.route ->
//            slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
//        else -> null
//    }
//},
//popEnterTransition = {
//    when (initialState.destination.route) {
//        Screen.LoginScreen.route ->
//            slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
//        else -> null
//    }
//},
//popExitTransition = {
//    when (targetState.destination.route) {
//        Screen.LoginScreen.route ->
//            slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
//        else -> null
//    }
//}
//){
//    ProfileScreen(navController = navController)
//}
//
//
//}
//
//
//}
