package com.siny.data.repository

import android.database.Cursor
import android.util.Log
import com.siny.data.model.MainData
import com.siny.db.DBUtil

class MainRepository {

    private val tag: String = javaClass.simpleName

    fun getList(cd1: String): List<MainData> {
        val sql = "select cd1, cd2, nm, nm2 from tb_list where cd1 = ? order by 1, 2"
        return getList(sql, cd1)
    }

    fun getList(sql: String, cd1: String): MutableList<MainData> {
        val list: MutableList<MainData> = ArrayList()
        var c: Cursor? = null
        try {
            c = DBUtil.query(sql, cd1)
            if (c != null) {
                while (c.moveToNext()) {
                    val map = MainData(
                        cd1 = c.getInt(0),
                        cd2 = c.getInt(1),
                        nm = c.getString(2),
                        nm2 = c.getString(3),
                    )
                    list.add(map)
                }
            }
        } catch (e: Exception) {
            Log.e(tag, "Cursor.Exception.Cursor= $c")
            Log.e(tag, "Cursor.Exception= $e")
        } finally {
            c?.close()
        }
        return list
    }

}