package com.example.mahjongcalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
        val yaku = intent.getStringArrayExtra("yaku")
        val yakuman = intent.getStringArrayExtra("yakuman")
        var str = "No Yaku"
        if(yaku != null) {
            if(yaku.isNotEmpty()) {
                str = yaku.joinToString(", ") { it }
            }
        }

        if(yakuman != null) {
            if(yakuman.isNotEmpty()) {
                str = yakuman.joinToString(", ") { it }
                binding.han.text = ""
                binding.fu.text = ""
            }
        }

        binding.yaku.text = "Yaku/Yakuman: $str"
    }
}