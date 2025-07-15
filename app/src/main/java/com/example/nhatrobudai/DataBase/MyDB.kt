package com.example.nhatrobudai.DataBase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDB(context: Context) : SQLiteOpenHelper(context, "UserInfo.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE Account (\n" +
                "    ID       INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                     NOT NULL,\n" +
                "    UserName TEXT,\n" +
                "    Pass     TEXT\n" +
                ")")
        db?.execSQL("INSERT INTO Account(UserName,Pass) VALUES ('admin','123')")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Account")
        onCreate(db)
    }
}