package com.siny.bible.ui.screen.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.siny.data.model.DetailData
import com.siny.data.model.MainData

@Composable
fun DetailTitle(
    mainData: MainData,
    list: List<DetailData>,
    getCd3Count: (MainData) -> Int,
    getCd4Count: (Int, Int, Int) -> Int
){
    val cd1 =
        if(mainData.cd1 == 1) "구약" else "신약"

    var expanded1 by remember { mutableStateOf(false)}
    var expanded2 by remember { mutableStateOf(false)}

    var cd3 by remember { mutableStateOf(1)}
    var cd4 by remember { mutableStateOf(1)}

    var cd3Count by remember { mutableStateOf(1)}
    var cd4Count by remember { mutableStateOf(1)}

    Box {
        Text(
            text = "$cd1 - ${mainData.nm}",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodyLarge
        )

        Row(modifier = Modifier.fillMaxWidth().align(Alignment.CenterEnd)) {
            Box {
                Row(Modifier.clickable {
                    cd3Count = getCd3Count(mainData)
                    expanded1 = !expanded1
                }) {
                    Text(text = "${cd3}장")
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = ""
                    )
                }
            }
            Box {
                Row(Modifier.clickable {
                    cd4Count = getCd4Count(mainData.cd1, mainData.cd2, cd3)
                    expanded2 = !expanded2
                }) {
                    Text(text = "${cd4}절")
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = ""
                    )
                }
            }

            DropdownMenu(
                expanded = expanded1,
                onDismissRequest = { expanded1 = false },
            ) {
                for(i: Int in 1 .. cd3Count) {
                    DropdownMenuItem(
                        onClick = {
                            cd3 = i
                            cd4 = 1
                            expanded1 = false
                        },
                        text = {
                            Text(text = "${i}장")
                        }
                    )
                }
            }
            DropdownMenu(
                expanded = expanded2,
                onDismissRequest = { expanded2 = false },
            ) {
                for(i: Int in 1 .. cd4Count) {
                    DropdownMenuItem(
                        onClick = {
                            cd4 = i
                            expanded2 = false
                        },
                        text = {
                            Text(text = "${i}절")
                        }
                    )
                }
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DetailScreen(
    mainData: MainData,
    list: List<DetailData>,
    getCd3Count: (MainData) -> Int,
    getCd4Count: (Int, Int, Int) -> Int
){
    val lazyListState = rememberLazyListState()

    Column(
        modifier = Modifier.padding(5.dp)
    ) {
        DetailTitle(
            mainData = mainData,
            list = list,
            getCd3Count = getCd3Count,
            getCd4Count = getCd4Count
        )

        LazyColumn(
            modifier = Modifier.padding(5.dp),
            state = lazyListState,
        ) {
            items(list.size) { index ->
                val detail = list[index]
                Row(
                    Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "${mainData.nm2} ${detail.cd3}:${detail.cd4}")
                    Spacer(Modifier.width(10.dp))
                    Text(text = detail.txt)
                    Spacer(Modifier.height(5.dp))
                }
            }
        }
    }
}

