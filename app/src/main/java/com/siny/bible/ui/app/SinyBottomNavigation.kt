package com.siny.bible.ui.app

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun SinyBottomNavigation(
    navController: NavHostController,
    bottomNavClick: (String)-> Unit
) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Old,
        NavigationItem.New,
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.background)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.drawable),
                        contentDescription = item.route,
                        modifier = Modifier
                            .width(26.dp)
                            .height(26.dp)
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                alwaysShowLabel = true,
                onClick = {
                    bottomNavClick(item.route)
                }
            )
        }
    }
}
