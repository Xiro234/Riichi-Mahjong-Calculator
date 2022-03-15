package com.example.mahjongcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.example.mahjongcalculator.databinding.ActivityHandCalculatorBinding

private lateinit var binding: ActivityHandCalculatorBinding


class HandCalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHandCalculatorBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnChangeImage.setOnClickListener {
            binding.ivHand1.setImageResource(R.drawable.man1)
        }
    }
}