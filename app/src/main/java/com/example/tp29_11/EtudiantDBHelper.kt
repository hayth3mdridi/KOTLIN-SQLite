package com.example.tp29_11

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.SimpleCursorAdapter

class EtudiantDBHelper(context: Context?) :

    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(EtudiantBC.SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(EtudiantBC.SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "PFE2.db"
    }

    private var dbHelper: EtudiantDBHelper? = null
    fun getDbHelper(context:Context): EtudiantDBHelper? {
        if (dbHelper == null) {
            dbHelper = EtudiantDBHelper(context)
        }
        return dbHelper
    }

}