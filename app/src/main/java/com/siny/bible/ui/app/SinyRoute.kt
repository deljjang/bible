package com.siny.bible.ui.app

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.siny.bible.ui.screen.detail.DetailScreen
import com.siny.bible.ui.screen.main.MainScreen
import com.siny.bible.ui.screen.main.MainViewModel
import com.siny.data.model.MainData

@Composable
fun HomeRoute(text: String){
    Text(text = text)
}

@Composable
fun MainRoute(
    viewModel: MainViewModel,
    mainData: MainData,
    detail: Boolean,
    onClick: (MainData) -> Unit,
    text: String
){
    if(detail) {
        viewModel.getDetailList(mainData)
        DetailScreen(
            mainData = mainData,
            list = viewModel.detailList!!,
            getCd3Count = viewModel.cd3Count,
            getCd4Count = viewModel.cd4Count,
        )
    } else {
        MainScreen(
            mainList = viewModel.mainList,
            onClick = onClick,
            text = text,
        )
    }
}