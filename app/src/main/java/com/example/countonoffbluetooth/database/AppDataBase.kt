package com.example.countonoffbluetooth.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AppDataBase(context:Context):SQLiteOpenHelper(context,"CountOnOffDB",null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE COUNTONOFF(OnCount INTEGER,OffCount INTEGER)")
        db?.execSQL("INSERT INTO COUNTONOFF VALUES(0,0)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}