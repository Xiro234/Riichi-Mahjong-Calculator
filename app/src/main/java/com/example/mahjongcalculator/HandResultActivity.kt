package com.example.mahjongcalculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mahjongcalculator.databinding.ActivityHandResultBinding

private lateinit var binding: ActivityHandResultBinding

class HandResultActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHandResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.score.text = "Score: " + intent.getSerializableExtra("score").toString()
        binding.han.text = "Han: " + intent.getIntExtra("han", 0).toString()
        binding.fu.text = "Fu: " + intent.getIntExtra("fu", 0).toString()
        val array = intent.getStringArrayExtra("yaku")
        var str = "No Yaku"
        if(array != null) {
            str = array.joinToString(", ") { it }
        }

        binding.yaku.text = "Yaku: $str"
    }
}