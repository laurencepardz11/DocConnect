package com.nethealth.drconnect.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nethealth.drconnect.ui.auth.login.LoginScreen
import com.nethealth.drconnect.ui.auth.pw.ForgotPwScreen
import com.nethealth.drconnect.ui.entry.SplashScreen
import com.nethealth.drconnect.ui.main.home.PatientScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(route = Screen.Splash.route){
            SplashScreen(navController)
        }

        composable(route = Screen.Login.route){
            LoginScreen(navController)
        }

        composable(route = Screen.Forgot.route){
            ForgotPwScreen(navController)
        }

        composable(route = Screen.Home.route){
            PatientScreen()
        }

    }
}
