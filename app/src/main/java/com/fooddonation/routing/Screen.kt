package com.fooddonation.routing

sealed class Screen(val route: String) {

    object SplashScreen: Screen("splash_screen")
    object LoginScreen: Screen("login_screen")
    object RegisterScreen: Screen("register_screen")
    object MainScreen: Screen("main_screen")
    object ThankScreen: Screen("thank_screen")
    object ContactUsScreen: Screen("contact_us_screen")
    object AboutUsScreen: Screen("about_us_screen")

}