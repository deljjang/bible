package com.siny.bible.ui.app

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.siny.bible.ui.screen.main.MainScreen
import com.siny.bible.ui.screen.main.MainViewModel

@Composable
fun HomeRoute(text: String){
    Text(text = text)
}

@Composable
fun MainRoute(
    viewModel: MainViewModel,
    text: String
){
    MainScreen(
        mainList = viewModel.mainList,
        text = text
    )
}