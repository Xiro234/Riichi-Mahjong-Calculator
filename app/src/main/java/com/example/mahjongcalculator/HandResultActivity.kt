package com.example.mahjongcalculator

import android.annotation.SuppressLint
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import com.example.mahjongcalculator.databinding.ActivityHandResultBinding

private lateinit var binding: ActivityHandResultBinding

class HandResultActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHandResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.score.text = intent.getSerializableExtra("score").toString()
        binding.han.text = intent.getIntExtra("han", 0).toString()
        binding.fu.text = intent.getIntExtra("fu", 0).toString()
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

                binding.yakuLabel.updateLayoutParams<ConstraintLayout.LayoutParams> { topToBottom = binding.score.id }
                binding.han.visibility = View.GONE
                binding.fu.visibility = View.GONE
                binding.hanLabel.visibility = View.GONE
                binding.fuLabel.visibility = View.GONE
            }
        }

        binding.yaku.text = str
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Intent(
            applicationContext,
            HandCalculatorActivity::class.java
        ).also { intent ->
            startActivity(intent)
        }
    }
}