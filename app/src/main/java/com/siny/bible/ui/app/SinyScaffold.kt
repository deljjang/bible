package com.siny.bible.ui.app

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController


/**
 * 하단 메뉴 처리
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SinyScaffold(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    navItem: NavigationItem,
    bottomNavClick: (String) -> Unit,
) {
    val tag = "SinyScaffold"
    Log.d(tag, "SinyScaffold.navItem=$navItem")

    Scaffold(
        bottomBar = { SinyBottomNavigation(navController = navController, bottomNavClick = bottomNavClick) },
        modifier = modifier
    ) { innerPadding ->
        //네비게이션 호스트 처리
        SinyNavigation(
            navController = navController,
        )
    }
}