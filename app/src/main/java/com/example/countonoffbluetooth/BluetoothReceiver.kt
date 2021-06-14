package com.example.countonoffbluetooth

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BluetoothReceiver(private val iCallBack: iCallBack) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        if (BluetoothAdapter.ACTION_STATE_CHANGED == action) {
            when (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1)) {
                BluetoothAdapter.STATE_ON -> {
                    iCallBack.updateOnCount()
                }
                BluetoothAdapter.STATE_OFF -> {
                    iCallBack.updateOffCount()
                }
            }
        }
    }
}