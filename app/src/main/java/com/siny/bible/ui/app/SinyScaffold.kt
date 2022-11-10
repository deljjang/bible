package com.siny.bible.ui.app

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.siny.data.model.MainData
import kotlinx.coroutines.launch


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

    var detail by remember { mutableStateOf(false) }
    var mainData by remember { mutableStateOf(MainData()) }

    val onClick: (MainData) -> Unit = {
        detail = true
        mainData = it
        Log.d(tag, "SinyScaffold.onClick.mainData=$mainData")
    }
    Log.d(tag, "SinyScaffold.navItem=${navItem.route}")
    Log.d(tag, "SinyScaffold.detail=$detail")

    //백키 처리
    if(detail) {
        val coroutineScope = rememberCoroutineScope()
        BackHandler(enabled = true) {
            coroutineScope.launch {
                detail = false
                Log.d(tag, "$tag.BackHandler.detail=$detail")
            }
        }
    }

    Scaffold(
        bottomBar = {
            if(!detail) {
                SinyBottomNavigation(navController = navController, bottomNavClick = bottomNavClick)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        //네비게이션 호스트 처리
        SinyNavigation(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            mainData = mainData,
            detail = detail,
            onClick = onClick,
        )
    }
}