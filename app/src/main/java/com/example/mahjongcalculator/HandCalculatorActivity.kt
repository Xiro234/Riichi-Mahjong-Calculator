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

        //region MAN BUTTONS
        binding.ivMan1.setOnClickListener { newTile(Suit.Man, 1) }
        binding.ivMan2.setOnClickListener { newTile(Suit.Man, 2) }
        binding.ivMan3.setOnClickListener { newTile(Suit.Man, 3) }
        binding.ivMan4.setOnClickListener { newTile(Suit.Man, 4) }
        binding.ivMan5.setOnClickListener { newTile(Suit.Man, 5) }
        binding.ivMan6.setOnClickListener { newTile(Suit.Man, 6) }
        binding.ivMan7.setOnClickListener { newTile(Suit.Man, 7) }
        binding.ivMan8.setOnClickListener { newTile(Suit.Man, 8) }
        binding.ivMan9.setOnClickListener { newTile(Suit.Man, 9) }
        binding.ivMan5Dora.setOnClickListener { newTile(Suit.Man, 10) }
        //endregion

        //region PIN BUTTONS
        binding.ivPin1.setOnClickListener { newTile(Suit.Pin, 1) }
        binding.ivPin2.setOnClickListener { newTile(Suit.Pin, 2) }
        binding.ivPin3.setOnClickListener { newTile(Suit.Pin, 3) }
        binding.ivPin4.setOnClickListener { newTile(Suit.Pin, 4) }
        binding.ivPin5.setOnClickListener { newTile(Suit.Pin, 5) }
        binding.ivPin6.setOnClickListener { newTile(Suit.Pin, 6) }
        binding.ivPin7.setOnClickListener { newTile(Suit.Pin, 7) }
        binding.ivPin8.setOnClickListener { newTile(Suit.Pin, 8) }
        binding.ivPin9.setOnClickListener { newTile(Suit.Pin, 9) }
        binding.ivPin5Dora.setOnClickListener { newTile(Suit.Pin, 10) }
        //endregion

        //region SOU BUTTONS
        binding.ivSou1.setOnClickListener { newTile(Suit.Sou, 1) }
        binding.ivSou2.setOnClickListener { newTile(Suit.Sou, 2) }
        binding.ivSou3.setOnClickListener { newTile(Suit.Sou, 3) }
        binding.ivSou4.setOnClickListener { newTile(Suit.Sou, 4) }
        binding.ivSou5.setOnClickListener { newTile(Suit.Sou, 5) }
        binding.ivSou6.setOnClickListener { newTile(Suit.Sou, 6) }
        binding.ivSou7.setOnClickListener { newTile(Suit.Sou, 7) }
        binding.ivSou8.setOnClickListener { newTile(Suit.Sou, 8) }
        binding.ivSou9.setOnClickListener { newTile(Suit.Sou, 9) }
        binding.ivSou5Dora.setOnClickListener { newTile(Suit.Sou, 10) }
        //endregion

        //region HONOR BUTTONS
        binding.ivPei.setOnClickListener { newTile(Suit.Wind, 1) }
        binding.ivTon.setOnClickListener { newTile(Suit.Wind, 2) }
        binding.ivNan.setOnClickListener { newTile(Suit.Wind, 3) }
        binding.ivShaa.setOnClickListener { newTile(Suit.Wind, 4) }

        binding.ivHaku.setOnClickListener { newTile(Suit.Dragon, 1) }
        binding.ivHatsu.setOnClickListener { newTile(Suit.Dragon, 2) }
        binding.ivChun.setOnClickListener { newTile(Suit.Dragon, 3) }
        //endregion
    }

    private fun newTile(suit: Suit, value: Int) {
        if(hand.addTile(suit, value)) {
            for (i in 0 until hand.numOfTiles) {
                hand.tiles[i]?.let {
                    findViewById<ImageView>(binding.HandGroup.referencedIds[i]).setImageResource(
                        it.toDrawable(baseContext)
                    )
                }
            }
        }
    }
}