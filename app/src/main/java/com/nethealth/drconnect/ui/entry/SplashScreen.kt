package com.nethealth.drconnect.ui.entry

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nethealth.drconnect.R
import com.nethealth.drconnect.app.state.DataStorage
import com.nethealth.drconnect.ui.main.home.MainActivity
import com.nethealth.drconnect.ui.nav.Screen
import com.nethealth.drconnect.ui.theme.DrConnectTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    val context = LocalContext.current
    val dataStorage = remember { DataStorage(context) }
    val hasSession = dataStorage.hasSession.collectAsState(false)

    LaunchedEffect(key1 = true) {

        delay(1000L)

        if (hasSession.value){
            context.startActivity(Intent(context, MainActivity::class.java))
            (context as Activity).finish()
        }else{
            navController.navigate(Screen.Login.route){
                popUpTo(Screen.Splash.route){
                    inclusive = true
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter =  painterResource(id = R.drawable.img_logo), contentDescription = null)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DrConnectTheme {
        SplashScreen(navController = rememberNavController())
    }
}