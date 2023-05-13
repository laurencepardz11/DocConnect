package com.nethealth.drconnect.ui.main.drawer.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.nethealth.drconnect.R
import com.nethealth.drconnect.ui.util.Constant
import kotlinx.coroutines.launch

@Composable
fun DrawerItems(navController: NavHostController, drawerState: DrawerState) {
    NavDrawerItem(
        navController = navController,
        drawerState = drawerState,
        icon = Icons.Filled.AccountCircle,
        label = "Patient", route =  Constant.NAV_PATIENT)

    NavDrawerItem(
        navController = navController,
        drawerState = drawerState,
        icon = Icons.Filled.Email,
        label = "Messages", route =  Constant.NAV_MESSAGES)

    NavDrawerItem(
        navController = navController,
        drawerState = drawerState,
        icon = Icons.Filled.Notifications,
        label = "Manage Alert", route =  Constant.NAV_MNG_ALERT)

    NavDrawerItem(
        navController = navController,
        drawerState = drawerState,
        icon = Icons.Filled.Info,
        label = "Alert History", route =  Constant.NAV_ALERT_HISTORY)

    NavDrawerItem(
        navController = navController,
        drawerState = drawerState,
        icon = Icons.Filled.Settings,
        label = "Account Settings", route =  Constant.NAV_ACNT_SETTINGS)

    NavDrawerItem(
        navController = navController,
        drawerState = drawerState,
        icon = Icons.Filled.Info,
        label = "Help", route =  Constant.NAV_HELP)

    NavDrawerItem(
        navController = navController,
        drawerState = drawerState,
        icon = Icons.Filled.Phone,
        label = "Contact Us", route =  Constant.NAV_CONTACT_US)
}

@Composable
fun NavDrawerItem(
    navController: NavHostController,
    drawerState: DrawerState,
    icon: ImageVector,
    label:String,
    route:String
) {

    val scope = rememberCoroutineScope()
    val currentBackStackEntryAsState = navController.currentBackStackEntryAsState()
    val destination = currentBackStackEntryAsState.value?.destination
    val selected = destination?.route == route && route != Constant.NAV_LOGOUT

    NavigationDrawerItem(
        icon = { Icon(icon, contentDescription = label,
            tint = if (selected) colorResource(id = R.color.theme_green) else  colorResource(id = R.color.light_text)) },

        label = { Text(text = label,
            color = if (selected)colorResource(id = R.color.theme_green)  else colorResource(id = R.color.light_text)) },

        selected = selected,

        onClick = {
            navController.navigate(route, navOptions {
                this.launchSingleTop = true
                this.restoreState = route != Constant.NAV_LOGOUT
            })

            scope.launch {
                if (route != Constant.NAV_LOGOUT){
                    drawerState.close()
                }
            }
        },
        modifier = Modifier
            .padding(NavigationDrawerItemDefaults.ItemPadding),
        shape = RoundedCornerShape(4.dp),
        colors = NavigationDrawerItemDefaults.colors(
            selectedContainerColor = colorResource(id = R.color.highlight_light)
        )
    )
}