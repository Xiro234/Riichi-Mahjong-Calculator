package com.example.mahjongcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.mahjongcalculator.databinding.ActivityHandCalculatorBinding

private lateinit var binding: ActivityHandCalculatorBinding


class HandCalculatorActivity : AppCompatActivity() {
    var hand: Hand = Hand()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHandCalculatorBinding.inflate(layoutInflater)
        val view = binding.root
        hand = Hand()
        setContentView(view)

        binding.ivMan1.setOnClickListener { newTile(Suit.Man, 1) }
    }

    private fun newTile(suit: Suit, value: Int) {
        hand.addTile(suit, value)

        for(i in 0..hand.numOfTiles) {
            hand.tiles[i]?.let { findViewById<ImageView>(binding.HandGroup.referencedIds[i]).setImageResource(it.toDrawable(baseContext)) }
        }
    }
}