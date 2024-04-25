package com.fooddonation.routing

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fooddonation.ui.about_us.AboutUsScreen
import com.fooddonation.ui.contact_us.ContactUsScreen
import com.fooddonation.ui.login.LoginScreen
import com.fooddonation.ui.main.MainScreen
import com.fooddonation.ui.register.RegisterScreen
import com.fooddonation.ui.splash.SplashScreen
import com.fooddonation.ui.thankScreen.ThankScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.RegisterScreen.route) {
            RegisterScreen(navController = navController)
        }
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }

        composable(route = Screen.ContactUsScreen.route) {
            ContactUsScreen(navController = navController)
        }
        composable(route = Screen.ThankScreen.route) {
            ThankScreen(navController = navController)
        }
        composable(route = Screen.AboutUsScreen.route) {
            AboutUsScreen(navController = navController)
        }

    }

}