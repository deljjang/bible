package com.siny.bible.ui.app

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.siny.bible.R
import com.siny.bible.ui.screen.main.MainViewModel
import com.siny.bible.ui.screen.main.MainViewModelFactory
import com.siny.data.model.MainData
import com.siny.data.repository.MainRepository

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
    mainData: MainData,
    detail: Boolean,
    onClick: (MainData) -> Unit,
    startDestination: String = SinyDestinations.HOME_ROUTE
) {
    val tag = "SinyNavigation"

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        Log.d(tag, "SinyNavigation.NavHost.startDestination=$startDestination")
        Log.d(tag, "SinyNavigation.NavHost.mainData=$mainData")

        composable(SinyDestinations.HOME_ROUTE) {
            Log.d(tag, "SinyNavigation.NavHost.HOME_ROUTE")
            HomeRoute(
                text = NavigationItem.Home.title
            )
        }
        composable(SinyDestinations.OLD_ROUTE) {
            Log.d(tag, "SinyNavigation.NavHost.OLD_ROUTE")
            val viewModel: MainViewModel = viewModel(
                factory = MainViewModelFactory(
                    repository = MainRepository(),
                    cd1 = "1",
                )
            )
            MainRoute(
                viewModel = viewModel,
                mainData = mainData,
                detail = detail,
                onClick = onClick,
                text = NavigationItem.Old.title
            )
        }
        composable(SinyDestinations.NEW_ROUTE) {
            Log.d(tag, "SinyNavigation.NavHost.NEW_ROUTE")
            val viewModel: MainViewModel = viewModel(
                factory = MainViewModelFactory(
                    repository = MainRepository(),
                    cd1 = "2",
                )
            )
            MainRoute(
                viewModel = viewModel,
                mainData = mainData,
                detail = detail,
                onClick = onClick,
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
