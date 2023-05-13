package com.nethealth.drconnect.ui.main.drawer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nethealth.drconnect.ui.main.drawer.component.DrawerContent
import com.nethealth.drconnect.ui.main.home.MessagesScreen
import com.nethealth.drconnect.ui.main.home.PatientScreen
import com.nethealth.drconnect.ui.util.Constant
import com.nethealth.drconnect.ui.views.AppBarMenu
import kotlinx.coroutines.launch

@Composable
fun NavigationPage() {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerContent = { DrawerContent(navController, drawerState) },
        drawerState = drawerState,
    ) {

        Scaffold(topBar = {

            AppBarMenu {
                if (drawerState.isClosed) {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                } else {
                    coroutineScope.launch {
                        drawerState.close()
                    }
                }
            }

        }) {
            Box(modifier = Modifier.padding(it)) {
                NavDestination(navController = navController)
            }
        }

    }
}

@Composable
fun NavDestination(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Constant.NAV_PATIENT
    ) {

        composable(route = Constant.NAV_PATIENT){
            PatientScreen()
        }

        composable(route = Constant.NAV_MESSAGES){
            MessagesScreen()
        }

        composable(route = Constant.NAV_MNG_ALERT){
            MessagesScreen()
        }

        composable(route = Constant.NAV_ALERT_HISTORY){
            MessagesScreen()
        }

        composable(route = Constant.NAV_ACNT_SETTINGS){
            MessagesScreen()
        }

        composable(route = Constant.NAV_HELP){
            MessagesScreen()
        }

        composable(route = Constant.NAV_CONTACT_US){
            MessagesScreen()
        }
    }
}
