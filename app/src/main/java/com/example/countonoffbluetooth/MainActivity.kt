package com.example.countonoffbluetooth


import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var  onCount=0
    var  offCount=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(bluetoothReceiver,  IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED))
    }

    private val bluetoothReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        lateinit var onCC:TextView
        lateinit var offCC:TextView
        override fun onReceive(context: Context, intent: Intent) {
            setContentView(R.layout.activity_main)
            val action = intent.action
            if (BluetoothAdapter.ACTION_STATE_CHANGED == action) {
                when (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1)) {
                    BluetoothAdapter.STATE_OFF -> {
                        offCount+=1
                        offCC =findViewById <TextView>(R.id.offC)
                        offCC.text= offCount.toString()
                        Toast.makeText(this@MainActivity, "Bluetooth is off,Off Count:" + offCount, Toast.LENGTH_SHORT).show()
                    }
                    BluetoothAdapter.STATE_ON -> {
                        onCount+=1
                          onCC =findViewById <TextView>(R.id.onC)
                        onCC.text= onCount.toString()
                        Toast.makeText(this@MainActivity, "Bluetooth is on, On Count:" + onCount, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}