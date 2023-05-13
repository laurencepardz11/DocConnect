package com.nethealth.drconnect.ui.main.drawer.component

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nethealth.drconnect.R
import com.nethealth.drconnect.app.state.DataStorage
import com.nethealth.drconnect.ui.entry.AuthActivity


@Composable
fun DrawerContent(
    navController: NavHostController,
    drawerState: DrawerState
) {

    val showLogoutDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .width(350.dp)
            .background(color = Color.White),
        horizontalAlignment = Alignment.Start
    ) {
        DrawerHeader()
        Spacer(modifier = Modifier.height(10.dp))
        Divider(color = Color.LightGray)
        Spacer(modifier = Modifier.height(10.dp))
        DrawerItems(navController, drawerState)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = {
                    showLogoutDialog.value = true
                })

        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Image(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Person icon",
                    colorFilter = ColorFilter.tint(colorResource(id = R.color.light_text)),
                    modifier = Modifier
                        .width(38.dp)
                        .height(38.dp)
                        .padding(start = 10.dp)
                )

                Text(
                    text = "Logout",
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.light_text),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 12.dp)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .align(Alignment.End)
                .padding(16.dp)
        ) {
            Text(
                text = "App version 1.0.0",
                fontSize = 13.sp,
                color = colorResource(id = R.color.light_text),
            )
        }
    }

    if (showLogoutDialog.value) {
        LogoutDialog { showLogoutDialog.value = false }
    }
}

@Composable
fun LogoutDialog(onDismiss: () -> Unit) {
    val context = LocalContext.current
    val dataStorage = remember { DataStorage(context) }
    val confirmLogout = remember { mutableStateOf(false) }

    if (confirmLogout.value) {
        LaunchedEffect(Unit) {
            dataStorage.clearUserData()
            context.startActivity(Intent(context, AuthActivity::class.java))
            (context as Activity).finish()
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        if (!confirmLogout.value) {
            AlertDialog(
                onDismissRequest = onDismiss,
                dismissButton = {
                    TextButton(onClick = {
                        onDismiss.invoke()
                    }) {
                        Text(text = "NO")
                    }
                },
                confirmButton = {
                    TextButton(onClick = {
                        confirmLogout.value = true
                    }) {
                        Text(text = "YES")
                    }
                },
                title = { Text(text = stringResource(R.string.logout)) },
                text = { Text(text = stringResource(R.string.are_you_sure_you_want_to_logout), fontSize = 16.sp) }
            )
        }
    }
}
