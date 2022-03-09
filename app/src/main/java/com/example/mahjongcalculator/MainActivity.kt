package com.example.mahjongcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newHand = findViewById<Button>(R.id.new_hand_button)

        newHand.setOnClickListener {
            Toast.makeText(this, "Click!", Toast.LENGTH_SHORT).show()
        }
    }
}
