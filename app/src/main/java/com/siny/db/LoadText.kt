package com.siny.db

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.siny.bible.R
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader


object LoadText {
    private val tag = "LoadText"

    fun setFileLoad(context: Context) {
        setFileLoadSub(context, "1", "1", R.raw.a1)
        setFileLoadSub(context, "1", "2", R.raw.a2)
        setFileLoadSub(context, "1", "3", R.raw.a3)
        setFileLoadSub(context, "1", "4", R.raw.a4)
        setFileLoadSub(context, "1", "5", R.raw.a5)
        setFileLoadSub(context, "1", "6", R.raw.a6)
        setFileLoadSub(context, "1", "7", R.raw.a7)
        setFileLoadSub(context, "1", "8", R.raw.a8)
        setFileLoadSub(context, "1", "9", R.raw.a9)
        setFileLoadSub(context, "1", "10", R.raw.a10)
        setFileLoadSub(context, "1", "11", R.raw.a11)
        setFileLoadSub(context, "1", "12", R.raw.a12)
        setFileLoadSub(context, "1", "13", R.raw.a13)
        setFileLoadSub(context, "1", "14", R.raw.a14)
        setFileLoadSub(context, "1", "15", R.raw.a15)
        setFileLoadSub(context, "1", "16", R.raw.a16)
        setFileLoadSub(context, "1", "17", R.raw.a17)
        setFileLoadSub(context, "1", "18", R.raw.a18)
        setFileLoadSub(context, "1", "19", R.raw.a19)   //시편
        setFileLoadSub(context, "1", "20", R.raw.a20)
        setFileLoadSub(context, "1", "21", R.raw.a21)
        setFileLoadSub(context, "1", "22", R.raw.a22)
        setFileLoadSub(context, "1", "23", R.raw.a23)
        setFileLoadSub(context, "1", "24", R.raw.a24)
        setFileLoadSub(context, "1", "25", R.raw.a25)
        setFileLoadSub(context, "1", "26", R.raw.a26)
        setFileLoadSub(context, "1", "27", R.raw.a27)
        setFileLoadSub(context, "1", "28", R.raw.a28)
        setFileLoadSub(context, "1", "29", R.raw.a29)
        setFileLoadSub(context, "1", "30", R.raw.a30)
        setFileLoadSub(context, "1", "31", R.raw.a31)
        setFileLoadSub(context, "1", "32", R.raw.a32)
        setFileLoadSub(context, "1", "33", R.raw.a33)
        setFileLoadSub(context, "1", "34", R.raw.a34)
        setFileLoadSub(context, "1", "35", R.raw.a35)
        setFileLoadSub(context, "1", "36", R.raw.a36)
        setFileLoadSub(context, "1", "37", R.raw.a37)
        setFileLoadSub(context, "1", "38", R.raw.a38)
        setFileLoadSub(context, "1", "39", R.raw.a39)
        setFileLoadSub(context, "2", "1", R.raw.b1)
        setFileLoadSub(context, "2", "2", R.raw.b2)
        setFileLoadSub(context, "2", "3", R.raw.b3)
        setFileLoadSub(context, "2", "4", R.raw.b4)
        setFileLoadSub(context, "2", "5", R.raw.b5)
        setFileLoadSub(context, "2", "6", R.raw.b6)
        setFileLoadSub(context, "2", "7", R.raw.b7)
        setFileLoadSub(context, "2", "8", R.raw.b8)
        setFileLoadSub(context, "2", "9", R.raw.b9)
        setFileLoadSub(context, "2", "10", R.raw.b10)
        setFileLoadSub(context, "2", "11", R.raw.b11)
        setFileLoadSub(context, "2", "12", R.raw.b12)
        setFileLoadSub(context, "2", "13", R.raw.b13)
        setFileLoadSub(context, "2", "14", R.raw.b14)
        setFileLoadSub(context, "2", "15", R.raw.b15)
        setFileLoadSub(context, "2", "16", R.raw.b16)
        setFileLoadSub(context, "2", "17", R.raw.b17)
        setFileLoadSub(context, "2", "18", R.raw.b18)
        setFileLoadSub(context, "2", "19", R.raw.b19)
        setFileLoadSub(context, "2", "20", R.raw.b20)
        setFileLoadSub(context, "2", "21", R.raw.b21)
        setFileLoadSub(context, "2", "22", R.raw.b22)
        setFileLoadSub(context, "2", "23", R.raw.b23)
        setFileLoadSub(context, "2", "24", R.raw.b24)
        setFileLoadSub(context, "2", "25", R.raw.b25)
        setFileLoadSub(context, "2", "26", R.raw.b26)
        setFileLoadSub(context, "2", "27", R.raw.b27)
    }

    private fun setFileLoadSub(context: Context, cd1: String, cd2: String, raw: Int) {
        val dbSet: Map<String, String> =
            DBUtil.getMap("select nm, db_set from tb_list where cd1=? and cd2=?", cd1, cd2)

        Log.d(tag, "setFileLoadSub= $dbSet")

        if ("1" == dbSet["db_set"]) {
            return
        }
        val inputStream: InputStream = context.resources.openRawResource(raw)

        val db = DBUtil.DBAdapter.helper.writableDatabase
        var line: String? = null
        try {
            db.beginTransaction()

            val inputReader = InputStreamReader(inputStream)
            val bufferReader = BufferedReader(inputReader)
            while (bufferReader.readLine().also { line = it }  != null) {
                var p1 = line!!.split(":").toTypedArray()[0]
                p1 = p1.replace("\\D".toRegex(), "")
                line = line!!.split(":").toTypedArray()[1]
                val p2 = line!!.split(" ").toTypedArray()[0]
                line = line!!.substring(p2.length + 1)
                //Log.d(tag, "$p1, $p2, $line")

                val cv = ContentValues()
                cv.put("cd1", cd1)
                cv.put("cd2", cd2)
                cv.put("cd3", p1)
                cv.put("cd4", p2)
                cv.put("txt", line)
                db.insert("tb_list2", null, cv)
            }
            DBUtil.execSQL("update tb_list set db_set=1 where cd1=? and cd2=?", cd1, cd2)
            db.setTransactionSuccessful()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d(tag, "line=$line")
        } finally {
            inputStream.close()
            db.endTransaction()
        }
    }

}