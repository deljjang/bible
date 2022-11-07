package com.siny.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// db create, update, open, close 를 관리해주는 객체
class DatabaseHelper(
    cxt: Context?,
    dbName: String,
    private val mDBAdapterOnCreate: DBAdapterOnCreate?
) : SQLiteOpenHelper(cxt, dbName, null, version) {

    //한번만 실행
    override fun onCreate(db: SQLiteDatabase) {
        mDBAdapterOnCreate?.onCreate(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        mDBAdapterOnCreate?.onUpgrade(db, oldVersion, newVersion)
    }

    override fun onOpen(db: SQLiteDatabase) {
        super.onOpen(db)
    }

    companion object {
        // 이부분이 바뀌었다면, version정보 upgrade 필수!!
        private const val version = 1
    }
}