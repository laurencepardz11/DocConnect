package com.nethealth.drconnect.ui.auth.pw

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nethealth.drconnect.ui.theme.DrConnectTheme
import com.nethealth.drconnect.ui.views.AppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPwScreen(navigation: NavHostController) {

    DrConnectTheme{
        Scaffold( topBar = {
            AppBar("Forgot Password") {
                navigation.popBackStack()
            }
        }) {
            Box(modifier = Modifier.padding(it)) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 50.dp)
                ) {
                    Text(
                        text = "This is forgot pw screen",
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DrConnectTheme {
        ForgotPwScreen(rememberNavController())
    }
}