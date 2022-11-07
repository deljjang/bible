package com.siny.bible

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.siny.bible.ui.app.SinyApp
import com.siny.bible.ui.theme.BibleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BibleTheme {
                SinyApp()
                /*
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
                 */
            }
        }
    }
}

/*
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
*/

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BibleTheme {
        SinyApp()
        //Greeting("Android")
    }
}