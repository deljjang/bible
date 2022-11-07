package com.siny.bible.ui.app

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun SinyApp(
    navItem: NavigationItem = NavigationItem.Home,
    routes: SinyDestinations = SinyDestinations
) {
    val tag = "SinyApp"

    val navController = rememberNavController()
    val navigationActions = remember(navController) {
        SinyNavigationActions(navController)
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute =
        navBackStackEntry?.destination?.route ?: navItem.route // RssDestinations.HOME_ROUTE

    Log.d(tag, "currentRoute=${currentRoute}")

    var navigationItem: NavigationItem = NavigationItem.Home
    when(currentRoute) {
        NavigationItem.Home.route -> {
            navigationItem = NavigationItem.Home
        }
        NavigationItem.Old.route -> {
            navigationItem = NavigationItem.Old
        }
        NavigationItem.New.route -> {
            navigationItem = NavigationItem.New
        }
    }
    Log.d(tag, "navitem.route=${navigationItem.route}, currentRoute=$currentRoute")

    SinyScaffold(
        navController = navController,
        navItem = navigationItem,
        bottomNavClick =  navigationActions.navClick,
    )
}
