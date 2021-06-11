package com.example.countonoffbluetooth

import android.bluetooth.BluetoothAdapter
import android.content.*
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayCount()
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(bluetoothReceiver,  IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED))
    }

    fun updateCountTextView(t1: String?,t2: String?) {
        runOnUiThread {
            val textV1 : TextView  = findViewById<View>(R.id.onC) as TextView
            textV1.text = t1

            val textV2 : TextView  = findViewById<View>(R.id.offC) as TextView
            textV2.text = t2
        }
    }

    fun displayCount()
    {
        var helper=CountDBHelper2(applicationContext)
        var db=helper.readableDatabase
        var rs=db.rawQuery("select * from COUNTONOFF",null)
        if(rs.moveToNext())
        {
            updateCountTextView(rs.getString(0),rs.getString(1))
            Toast.makeText(applicationContext,"On Count:"+rs.getString(0)+" Off Count:"+rs.getString(1),Toast.LENGTH_LONG).show()
        }
    }

    fun updateOnCount()
    {
        var helper=CountDBHelper2(applicationContext)
        var db=helper.readableDatabase
        var rs=db.rawQuery("update COUNTONOFF set OnCount=1+(select OnCount from COUNTONOFF)",null)
        rs.moveToNext()
        displayCount()
    }

    fun updateOffCount()
    {
        var helper=CountDBHelper2(applicationContext)
        var db=helper.readableDatabase
        var rs=db.rawQuery("update COUNTONOFF set OffCount=1+(select OffCount from COUNTONOFF)",null)
        rs.moveToNext()
        displayCount()
    }

    private val bluetoothReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            setContentView(R.layout.activity_main)
            val action = intent.action
            if (BluetoothAdapter.ACTION_STATE_CHANGED == action) {
                when (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1)) {
                    BluetoothAdapter.STATE_ON -> {
                        updateOnCount()
                                                 }
                    BluetoothAdapter.STATE_OFF -> {
                        updateOffCount()
                    }
                }
            }
        }
    }
}