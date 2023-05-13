package com.nethealth.drconnect.ui.main.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.nethealth.drconnect.ui.theme.DrConnectTheme

@Composable
fun MessagesScreen() {
    DrConnectTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "Messages",
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
