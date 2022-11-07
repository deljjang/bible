package com.siny.db

import android.database.sqlite.SQLiteDatabase

/**
 * sqlite에서 사용
 */
interface DBAdapterOnCreate {
    fun onCreate(db: SQLiteDatabase?)
    fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
}