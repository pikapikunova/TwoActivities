package com.example.twoactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_2.*

class Activity2 : AppCompatActivity() {
    companion object {
        val EXTRA_FIELD = "com.example.twoactivities.foruser"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        val extra = intent.getStringExtra(EXTRA_FIELD)
        textView3.text = "Ответ: " + extra
        button3.setOnClickListener {
            this.finish()
        }
    }
}