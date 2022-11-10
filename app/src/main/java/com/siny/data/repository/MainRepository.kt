package com.siny.data.repository

import android.database.Cursor
import android.util.Log
import com.siny.data.model.DetailData
import com.siny.data.model.MainData
import com.siny.db.DBUtil

class MainRepository {

    private val tag: String = javaClass.simpleName

    fun getList(cd1: String): List<MainData> {
        val sql = "select cd1, cd2, nm, nm2, pos from tb_list where cd1 = ? order by 1, 2"
        return getList(sql, cd1)
    }

    fun setMainPos(cd1: Int, cd2: Int, pos: Int) {
        val sql = "update tb_list set pos = ? where cd1 = ? and cd2 = ?"
        DBUtil.execSQL(sql, pos.toString(), cd1.toString(), cd2.toString())
    }

    fun getDetailList(mainData: MainData): List<DetailData> {
        val sql = "select cd1, cd2, cd3, cd4, txt, pos, favorite from tb_list2 where cd1 = ? and cd2 = ? order by 1, 2, 3, 4"
        return getDetailList(sql, mainData)
    }

    fun getCd3Count(main: MainData): Int {
        val sql = "select max(cd3) cd3 from tb_list2 where cd1 = ? and cd2 = ?"
        return DBUtil.getInt(sql, main.cd1.toString(), main.cd2.toString())
    }

    fun getCd4Count(cd1: Int, cd2: Int, cd3: Int): Int {
        val sql = "select max(cd4) cd3 from tb_list2 where cd1 = ? and cd2 = ? and cd3 = ?"
        return DBUtil.getInt(sql, cd1.toString(), cd2.toString(), cd3.toString())
    }

    fun getPos(detail: DetailData): Int {
        val sql = "select pos from tb_list2 where cd1 = ? and cd2 = ? and cd3 = ? and cd4 = ?"
        return DBUtil.getInt(sql, detail.cd1.toString(), detail.cd2.toString(), detail.cd3.toString(), detail.cd4.toString())
    }

    fun getDetailList(sql: String, mainData: MainData): MutableList<DetailData> {
        val list: MutableList<DetailData> = ArrayList()
        var c: Cursor? = null
        try {
            c = DBUtil.query(sql, mainData.cd1.toString(), mainData.cd2.toString())
            if (c != null) {
                while (c.moveToNext()) {
                    val map = DetailData(
                        cd1 = c.getInt(0),
                        cd2 = c.getInt(1),
                        cd3 = c.getInt(2),
                        cd4 = c.getInt(3),
                        txt = c.getString(4),
                        pos = c.getInt(5),
                        favorite = c.getInt(6),
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
                        pos = c.getInt(4),
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