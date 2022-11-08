package com.siny.bible.ui.app

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.siny.bible.R

object SinyDestinations {
    const val HOME_ROUTE = "home"
    const val OLD_ROUTE = "old"
    const val NEW_ROUTE = "new"
}

/**
 * 하단 버튼바 아이템 목록
 */
open class NavigationItem(var route: String, var drawable: Int = 0, var title: String) {
    object Home : NavigationItem(SinyDestinations.HOME_ROUTE, R.drawable.ic_home_24, title = "Home")
    object Old : NavigationItem(SinyDestinations.OLD_ROUTE, R.drawable.ic_old_24, title = "구약")
    object New : NavigationItem(SinyDestinations.NEW_ROUTE, R.drawable.ic_new_24, title = "신약")
}

@Composable
fun SinyNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = SinyDestinations.HOME_ROUTE
) {
    val tag = "SinyNavigation"

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        Log.d(tag, "SinyNavigation.NavHost.startDestination=$startDestination")

        composable(SinyDestinations.HOME_ROUTE) {
            Log.d(tag, "SinyNavigation.NavHost.HOME_ROUTE")
            HomeRoute(
                text = NavigationItem.Home.title
            )
        }
        composable(SinyDestinations.OLD_ROUTE) {
            Log.d(tag, "SinyNavigation.NavHost.OLD_ROUTE")
            HomeRoute(
                text = NavigationItem.Old.title
            )
        }
        composable(SinyDestinations.NEW_ROUTE) {
            Log.d(tag, "SinyNavigation.NavHost.NEW_ROUTE")
            HomeRoute(
                text = NavigationItem.New.title
            )
        }
    }
}

/**
 * 하단 버튼 클릭 처리
 */
class SinyNavigationActions(
    private val navController: NavHostController,
) {
    val TAG = "SinyNavigationActions"

    val navClick: (String) -> Unit = {
        Log.d(TAG, "onNavClick.it=$it")
        navController.navigate(it) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

}
