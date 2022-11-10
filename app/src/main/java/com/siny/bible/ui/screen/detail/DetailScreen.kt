package com.siny.bible.ui.screen.detail

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.siny.bible.ui.theme.BibleTheme
import com.siny.data.model.DetailData
import com.siny.data.model.MainData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

val tag = "DetailScreen"

@Composable
fun DetailTitle(
    mainData: MainData,
    list: List<DetailData>,
    lazyListState: LazyListState,
    getCd3Count: (MainData) -> Int,
    getCd4Count: (Int, Int, Int) -> Int,
    getPos: (DetailData) -> Int,
    setPos: (Int, Int, Int) -> Unit,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
){
    val cd1 =
        if(mainData.cd1 == 1) "구약" else "신약"

    var detail = list[mainData.pos-1]
    var pos by remember { mutableStateOf(mainData.pos-1)}

    Log.d(tag, "DetailTitle.detail=" + detail)

    var expanded1 by remember { mutableStateOf(false)}
    var expanded2 by remember { mutableStateOf(false)}

    var cd3 by remember { mutableStateOf(detail.cd3)}
    var cd4 by remember { mutableStateOf(detail.cd4)}

    var cd3Count by remember { mutableStateOf(1)}
    var cd4Count by remember { mutableStateOf(1)}

    val index = remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }

    if(pos != index.value && index.value > 0) {
        Log.d(tag, "DetailTitle.pos=$pos / ${index.value}")
        pos = index.value
        detail = list[pos]
        cd3 = detail.cd3
        cd4 = detail.cd4
        setPos(detail.cd1, detail.cd2, pos+1)
        mainData.pos = pos+1
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "$cd1 - ${mainData.nm}",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodyLarge
        )

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
            //.fillMaxWidth()
            .align(Alignment.CenterEnd)
        ) {
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

            Row(Modifier.defaultMinSize(minWidth = 50.dp).clickable {
                cd4Count = getCd4Count(mainData.cd1, mainData.cd2, cd3)
                expanded2 = !expanded2
            }) {
                Text(text = "${cd4}절",
                    modifier = Modifier.defaultMinSize(minWidth = 50.dp),
                    textAlign = TextAlign.End
                )
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = ""
                )
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

                            val pos = getPos(DetailData(
                                cd1 = mainData.cd1,
                                cd2 = mainData.cd2,
                                cd3 = i,
                                cd4 = cd4
                            ))

                            coroutineScope.launch {
                                lazyListState.scrollToItem(pos-1)
                            }

                            mainData.pos = pos
                            Log.d(tag, "DetailTitle.mainData=$mainData")
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

                            val pos = getPos(DetailData(
                                cd1 = mainData.cd1,
                                cd2 = mainData.cd2,
                                cd3 = cd3,
                                cd4 = cd4
                            ))

                            coroutineScope.launch {
                                lazyListState.scrollToItem(pos-1)
                            }

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
    getCd4Count: (Int, Int, Int) -> Int,
    getPos: (DetailData) -> Int,
    setPos: (Int, Int, Int) -> Unit,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
){
    val lazyListState = rememberLazyListState()

    Column(
        modifier = Modifier.padding(5.dp)
    ) {
        DetailTitle(
            mainData = mainData,
            list = list,
            lazyListState = lazyListState,
            getCd3Count = getCd3Count,
            getCd4Count = getCd4Count,
            getPos = getPos,
            setPos = setPos,
        )

        coroutineScope.launch {
            lazyListState.scrollToItem(mainData.pos-1)
        }

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

@ExperimentalAnimationApi
@Preview("DetailTitlePreview")
@Preview("DetailTitlePreview(dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailTitlePreview() {

    val mainData = MainData(
        cd1 = 1,
        cd2 = 1,
        nm = "창세기",
        nm2 = "창"
    )

    val list = listOf(
        DetailData(cd1=1, cd2=1, cd3=49, cd4=31, txt="아브라함과 그의 아내 사라가 거기 장사되었고 이삭과 그의 아내 리브가도 거기 장사되었으며 나도 레아를 그 곳에 장사하였노라", pos=1506, favorite=0)
    )

    val lazyListState = rememberLazyListState()
    BibleTheme {
        Surface {
            DetailScreen(
                mainData = mainData,
                list = list,
                //lazyListState = lazyListState,
                getCd3Count = {a -> 0},
                getCd4Count = {a, b,c -> 0},
                getPos = {1},
                setPos = {a,b,c ->}
            )
        }
    }
}

