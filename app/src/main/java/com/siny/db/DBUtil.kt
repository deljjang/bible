package com.siny.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import kotlin.collections.set

/**
 * DB처리용
 */
@SuppressLint("StaticFieldLeak")
object DBUtil {

    private const val tag = "DBUtil"
    private const val dbName = "siny_bible.db"

    var context: Context? = null
        set(value) {
            if (field == null) {
                mDBAdapter = DBAdapter(value, dbName, mDBAdapterOnCreate)
            }
            field = value
        }

    var mDBAdapter: DBAdapter? = null

    private val mDBAdapterOnCreate: DBAdapterOnCreate =
    object : DBAdapterOnCreate {
        override fun onCreate(db: SQLiteDatabase?) {
            DBCreate.onCreate(db!!)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            DBCreate.onUpgrade(db!!, oldVersion, newVersion)
        }
    }

    fun insert(tableName: String?, values: ContentValues?): Int {
        var i: Long = 0
        try {
            i = mDBAdapter!!.insertTable(tableName, values)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return i.toInt()
    }

    fun deleteAll(table: String): Int {
        val sql = "delete from "
        return exec(sql + table)
    }

    fun delete(tableName: String, where: String?, vararg args: String?): Int {
        return mDBAdapter!!.deleteTable(tableName, where, *args)
    }

    fun update(table: String, map: Map<String, String>, where: String, vararg args: String): Int {
        val values = ContentValues()
        for (key in map.keys) {
            values.put(key, map[key])
        }
        return update(table, values, where, *args)
    }

    fun update(table: String, values: ContentValues, where: String, vararg args: String): Int {
        val pkData: Array<String?> = arrayOfNulls(args.size)
        var i =0
        args.forEach {
            pkData[i++] = it
        }

        return mDBAdapter!!.updateTableString(table, values, where, pkData)
    }

    fun update(tableName: String, values: ContentValues, pkColumn: String, pkData: String?) {
        update(tableName, values, arrayOf(pkColumn), arrayOf(pkData))
    }

    fun update(tableName: String, values: ContentValues, pkMap: MutableMap<String, String>): Int {
        val sWhere = StringBuilder()
        val pkData = arrayOfNulls<String>(pkMap.size)
        for ((iWhere, key) in pkMap.keys.withIndex()) {
            if (iWhere > 0) {
                sWhere.append(" and ")
            }
            sWhere.append(key).append(" = ? ")
            pkData[iWhere] = pkMap[key]
        }
        return mDBAdapter!!.updateTableString(tableName, values, sWhere.toString(), pkData)
    }

    fun update(
        tableName: String,
        values: ContentValues,
        pkColumn: Array<String>,
        pkData: Array<String?>
    ): Int {
        val sWhere = StringBuilder()
        var iWhere = 0
        for (sTmp in pkColumn) {
            if (iWhere > 0) {
                sWhere.append(" and ")
            }
            sWhere.append(sTmp).append(" = ? ")
            iWhere++
        }
        return mDBAdapter!!.updateTableString(tableName, values, sWhere.toString(), pkData)
    }

    fun merge(tableName: String, values: ContentValues?, pkMap: Map<String?, String?>) {
        val sWhere = StringBuilder()
        var iWhere = 0
        val pkData = arrayOfNulls<String>(pkMap.size)
        for (key in pkMap.keys) {
            if (iWhere > 0) {
                sWhere.append(" and ")
            }
            sWhere.append(key).append(" = ? ")
            pkData[iWhere] = pkMap[key]
            iWhere++
        }
        val sql = "select count(1) cnt from $tableName where $sWhere"
        val i = getInt(sql, *pkData)
        if (i == 0) {
            insert(tableName, values)
        } else {
            mDBAdapter!!.updateTableString(tableName, values!!, sWhere.toString(), pkData)
        }
    }

    fun merge(
        tableName: String,
        values: ContentValues,
        pkColumn: Array<String>,
        pkData: Array<String?>
    ) {
        val sWhere = StringBuilder()
        var iWhere = 0
        for (sTmp in pkColumn) {
            if (iWhere > 0) {
                sWhere.append(" and ")
            }
            sWhere.append(sTmp).append(" = ? ")
            iWhere++
        }
        val sql = "select count(1) cnt from $tableName where $sWhere"
        val i = getInt(sql, *pkData)
        if (i == 0) {
            insert(tableName, values)
        } else {
            update(tableName, values, pkColumn, pkData)
        }
    }

    fun exec(sql: String): Int {
        var i = 0
        try {
            mDBAdapter!!.execSQL(sql)
        } catch (e: Exception) {
            i = -1
        }
        return i
    }

    fun execSQL(sql: String): Int {
        var i = 0
        try {
            mDBAdapter!!.execSQL(sql)
        } catch (e: Exception) {
            i = -1
        }
        return i
    }

    fun execSQL(sql: String, vararg args: Any?): Int {
        var i = 0
        try {
            mDBAdapter!!.execSQL(sql, *args)
        } catch (e: Exception) {
            i = -1
        }
        return i
    }

    fun execSQL(sql: String, vararg args: String?): Int {
        var i = 0
        try {
            mDBAdapter!!.execSQL(sql, *args)
        } catch (e: Exception) {
            i = -1
        }
        return i
    }

    fun query(sql: String, vararg args: String?): Cursor? {
        return try {
            mDBAdapter!!.selectTableRow(sql, *args)
        } catch (e: Exception) {
            Log.e(tag, "Cursor.Exception = $e")
            null
        }
    }

    fun getInt(c: Cursor, col: String?): Int {
        val i = c.getColumnIndex(col)
        return if (i >= 0) {
            c.getInt(i)
        } else 0
    }

    fun getString(c: Cursor, col: String?): String {
        val i = c.getColumnIndex(col)
        return if (i >= 0) {
            c.getString(i) ?: ""
        } else ""
    }

    fun getMap(sql: String, vararg args: String?): MutableMap<String, String> {
        var c: Cursor? = null
        val map: MutableMap<String, String> = HashMap()
        return try {
            c = query(sql, *args)
            if (c != null) {
                if (c.moveToFirst()) {
                    for (i in 0 until c.columnCount) {
                        map[c.getColumnName(i)] = c.getString(i) ?: ""
                    }
                }
            }
            map
        } catch (e: Exception) {
            Log.e(tag, "Cursor.Exception = $e")
            map
        } finally {
            close(c)
        }
    }

    fun getTableInt(table: String, col: String, where: String, vararg args: String?): Int {
        var i = 0
        var c: Cursor? = null
        try {
            c = query("SELECT $col FROM $table WHERE $where", *args)
            assert(c != null)
            if (c!!.moveToFirst()) {
                i = c.getInt(0)
            }
        } catch (e: Exception) {
            Log.e(tag, "Cursor.Exception = $e")
        } finally {
            close(c)
        }
        return i
    }

    fun getInt(sql: String, vararg args: String?): Int {
        var i = 0
        var c: Cursor? = null
        try {
            c = query(sql, *args)
            assert(c != null)
            if (c!!.moveToFirst()) {
                i = c.getInt(0)
            }
        } catch (e: Exception) {
            Log.e(tag, "Cursor.Exception = $e")
        } finally {
            close(c)
        }
        return i
    }

    fun getString(sql: String, vararg args: String?): String {
        var s = ""
        var c: Cursor? = null
        try {
            c = query(sql, *args)
            assert(c != null)
            if (c!!.moveToFirst()) {
                s = c.getString(0) ?: ""
            }
        } catch (e: Exception) {
            Log.e(tag, "Cursor.Exception = $e")
        } finally {
            close(c)
        }
        return s
    }

    fun getTableString(
        table: String,
        col: String,
        where: String,
        vararg args: String?
    ): String {
        var s = ""
        var c: Cursor? = null
        try {
            c = query("SELECT $col FROM $table WHERE $where", *args)
            assert(c != null)
            if (c!!.moveToFirst()) {
                s = c.getString(0) ?: ""
            }
        } catch (e: Exception) {
            Log.e(tag, "Cursor.Exception = $e")
            return s
        } finally {
            close(c)
        }
        return s
    }

    fun getList(sql: String, vararg args: String?): MutableList<MutableMap<String, String>> {
        val list: MutableList<MutableMap<String, String>> = ArrayList()
        var c: Cursor? = null
        try {
            c = query(sql, *args)
            if (c != null) {
                while (c.moveToNext()) {
                    val map = HashMap<String, String>()
                    for (i in 0 until c.columnCount) {
                        //Log.e(TAG, "Cursor.getColumnName($i)=${c.getColumnName(i)} = ${c.getString(i)}")
                        map[c.getColumnName(i)] = c.getString(i) ?: ""
                    }
                    list.add(map)
                }
            }
        } catch (e: Exception) {
            Log.e(tag, "Cursor.Exception.Cursor= $c")
            Log.e(tag, "Cursor.Exception= $e")
        } finally {
            close(c)
        }
        return list
    }

    fun getStrings(sql: String, vararg args: String?): Array<String?>? {
        var c: Cursor? = null
        return try {
            c = query(sql, *args)
            val ss = arrayOfNulls<String>(c!!.count)
            val i = 0
            while (c.moveToNext()) {
                ss[i] = c.getString(i)
            }
            ss
        } catch (e: Exception) {
            log("getStrings.Exception = $e")
            null
        } finally {
            close(c)
        }
    }

    private fun close(c: Cursor?) {
        c?.close()
    }

    private fun log(msg: String?) {
        Log.d(tag, msg!!)
    }

    class DBAdapter(context: Context?, dbName: String, mDBAdapterOnCreate: DBAdapterOnCreate?) {
        init {
            helper = DatabaseHelper(context, dbName, mDBAdapterOnCreate)
        }

        companion object {
            lateinit var helper: DatabaseHelper
        }

        fun insertTable(tableName: String?, values: ContentValues?): Long {
            val db = helper.writableDatabase
            return db.insert(tableName, null, values)
        }

        fun updateTableString(
            tableName: String,
            values: ContentValues,
            where: String,
            pkData: Array<String?>
        ): Int {
            val db = helper.writableDatabase
            var i = 0
            try {
                i = db.update(tableName, values, where, pkData)
            } catch (e: java.lang.Exception) {
                log("tableName=$tableName")
                log("Exception=$e")
            }
            return i
        }

        fun deleteTable(tableName: String?, where: String?, vararg args: String?): Int {
            val db = helper.writableDatabase
            return db.delete(tableName, where, args)
        }

        fun execSQL(sql: String) {
            val db = helper.writableDatabase
            try {
                db.execSQL(sql)
            } catch (e: java.lang.Exception) {
                log("getMessage=${e.message}")
                log("Exception=$e")
                log("sql=$sql")
            }
        }

        fun execSQL(sql: String, vararg args: Any?) {
            val db = helper.writableDatabase
            try {
                db.execSQL(sql, args)
            } catch (e: java.lang.Exception) {
                val log = java.lang.StringBuilder(sql)
                if (args != null) {
                    log.append("\n args.length=").append(args.size)
                    var i = 0
                    while (i < args.size) {
                        log.append("\n bind[").append(i).append("]=").append(args[i])
                        i++
                    }
                }
                log("getMessage=${e.message}")
                log("Exception=$e")
                log("execSQL.sql err= $log")
            }
        }

        fun selectTableRow(sql: String, vararg args: String?): Cursor? {
            val db = helper.writableDatabase
            // where조건절에 들어가는 내용들
            var c: Cursor? = null
            try {
                c = db.rawQuery(sql, args)
            } catch (e: java.lang.Exception) {
                Log.d(tag, "selectTableRow.sql err= $sql")
                var log = sql
                if (args != null) {
                    var i = 0
                    while (i < args.size) {
                        log += """bind[$i]=${args[i]}\n""".trimIndent()
                        i++
                    }
                }
                log("getMessage=${e.message}")
                log("Exception=$e")
                log("selectTableRow.log= $log")
            }
            return c
        }

    }

}


