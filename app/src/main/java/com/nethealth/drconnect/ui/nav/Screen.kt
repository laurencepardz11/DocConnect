package com.nethealth.drconnect.ui.nav

sealed class Screen(val route: String) {
    object Splash : Screen(route = "splash_screen")
    object Login : Screen(route = "login_screen")
    object Forgot: Screen(route = "forgot_pw_screen")
    object Home: Screen(route = "home_screen")
}