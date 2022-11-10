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
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DetailScreen(
    mainData: MainData,
    list: List<DetailData>,
) {
    var expanded1 by remember { mutableStateOf(false)}
    var expanded2 by remember { mutableStateOf(false)}

    val cd1 =
        if(mainData.cd1 == 1) "구약" else "신약"

    val lazyListState = rememberLazyListState()

    Column(
        modifier = Modifier.padding(5.dp),
    ) {

        Box {
            Text(
                text = cd1,
                //modifier = Modifier.fillMaxWidth(),
                //textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = mainData.nm,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )

            Row(
                Modifier
                    //.fillMaxWidth()
                    .align(Alignment.CenterEnd)
            ) {
                Box {
                    // Back arrow here
                    Row(Modifier.clickable { // Anchor view
                        expanded1 = !expanded1
                    }) { // Anchor view
                        Text(text = "1장",) // City name label
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
                    DropdownMenuItem(
                        onClick = { },
                        text = {
                            Text(text = "1장")
                        }
                    )
                }
                Box {
                    // Back arrow here
                    Row(Modifier.clickable { // Anchor view
                        expanded2 = !expanded2
                    }) { // Anchor view
                        Text(text = "1절",) // City name label
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = ""
                        )
                    }
                }
                DropdownMenu(
                    expanded = expanded2,
                    onDismissRequest = { expanded2 = false },
                ) {
                    DropdownMenuItem(
                        onClick = { },
                        text = {
                            Text(text = "1절")
                        }
                    )
                }
            }
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

