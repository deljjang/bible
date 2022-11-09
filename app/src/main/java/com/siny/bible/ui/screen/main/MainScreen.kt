package com.siny.bible.ui.screen.main

import android.content.res.Configuration
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.siny.bible.ui.theme.BibleTheme
import com.siny.data.model.MainData

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    mainList: List<MainData>,
    text: String
){
    Column(
        modifier = Modifier.padding(5.dp),
    ) {
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )

        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 128.dp)
        ) {
            itemsIndexed(mainList) {index, main ->
                Button(
                    onClick = {

                    },
                    modifier = Modifier.padding(5.dp),
                ) {
                    Text(text = "${main.cd2}.${main.nm}")
                }
                Log.d("MainScreen","${main.cd2}.${main.nm}")
            }
        }

    }

}

@ExperimentalAnimationApi
@Preview("MainScreen")
@Preview("MainScreen(dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainScreenPreview() {

    val mainList = listOf(
        MainData(
            cd1 = 1,
            cd2 = 1,
            nm = "창세기",
            nm2 = "창"
        ),
        MainData(
            cd1 = 1,
            cd2 = 2,
            nm = "출애굽기",
            nm2 = "출"
        )
    )
    BibleTheme {
        Surface {
            MainScreen(
                mainList = mainList,
                text = "타이틀",
            )
        }
    }
}
