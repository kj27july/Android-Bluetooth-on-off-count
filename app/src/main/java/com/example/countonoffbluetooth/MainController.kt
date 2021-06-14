package com.example.countonoffbluetooth

import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.IntentFilter

class MainController(private val iMainView: iMainView, private val context: Context) : iCallBack {

    init {
        val bluetoothReceiver = BluetoothReceiver(this)
        context.registerReceiver(
            bluetoothReceiver,
            IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
        )
    }

    override fun updateOnCount() {
        val helper = CountDBHelper2(context = context)
        val db = helper.readableDatabase
        val count = db.rawQuery("select OnCount from COUNTONOFF", null).getInt(1)
        val rs = db.rawQuery("update COUNTONOFF set OnCount=count+1", null)
        iMainView.updateOnCount(count + 1)
    }

    override fun updateOffCount() {
        val helper = CountDBHelper2(context)
        val db = helper.readableDatabase
        val count = db.rawQuery("select OffCount from COUNTONOFF", null).getInt(1)
        val rs = db.rawQuery("update COUNTONOFF set OffCount=1+count", null)
        iMainView.updateOffCount(count + 1)
    }

    fun getData() {
        val helper = CountDBHelper2(context)
        val db = helper.readableDatabase
        val rs = db.rawQuery("select * from COUNTONOFF", null)
        if (rs.moveToNext()) {
            iMainView.updateOnCount(rs.getInt(0))
            iMainView.updateOffCount(rs.getInt(1))
        }
    }
}