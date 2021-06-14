package com.example.countonoffbluetooth.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.countonoffbluetooth.R
import com.example.countonoffbluetooth.controller.MainController
import com.example.countonoffbluetooth.interfaces.iMainView

class MainActivity : AppCompatActivity(), iMainView {
    lateinit var textV1: TextView
    lateinit var textV2: TextView
    lateinit var controller: MainController
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
        controller.getData()
    }

    fun initUi() {
        textV1 = findViewById<View>(R.id.onC) as TextView
        textV2 = findViewById<View>(R.id.offC) as TextView
        controller = MainController(this, this)
    }

    override fun updateOnCount(count : Int) {
        textV1.text = count.toString()
    }

    override fun updateOffCount(count : Int) {
        textV2.text = count.toString()
    }
}