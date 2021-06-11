package com.example.countonoffbluetooth

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CountDBHelper2(context:Context):SQLiteOpenHelper(context,"CountOnOffDB",null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE COUNTONOFF(OnCount INTEGER,OffCount INTEGER)")
        db?.execSQL("INSERT INTO COUNTONOFF VALUES(0,0)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//        TODO("Not yet implemented")
    }
}