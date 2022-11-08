package com.siny.bible

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.siny.bible.ui.app.SinyApp
import com.siny.bible.ui.theme.BibleTheme
import com.siny.db.DBUtil
import com.siny.db.LoadText

class MainActivity : ComponentActivity() {

    private val tag = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //DB처리 시작
        DBUtil.context = this

        val list = DBUtil.getList("select * from tb_list ")
        Log.d(tag, "onCreate.list=${list.size} $list") //66건

        val list2 = DBUtil.getList("select * from tb_list2 ")
        Log.d(tag, "onCreate.list2=${list2.size} $list2")


        val dbCnt = DBUtil.getInt("select count(1) cnt from tb_list where db_set = 0")
        Log.d(tag, "onCreate.dbCnt=$dbCnt")
        if(dbCnt > 0) {
            Thread {
                runOnUiThread {
                    LoadText.setFileLoad(this)
                }
            }.start()
        }

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