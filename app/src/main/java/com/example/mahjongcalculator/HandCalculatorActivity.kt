package com.example.mahjongcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        binding.ivMan5Dora.setOnClickListener { newTile(Suit.Man, 5, true) }
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
        binding.ivPin5Dora.setOnClickListener { newTile(Suit.Pin, 5, true) }
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
        binding.ivSou5Dora.setOnClickListener { newTile(Suit.Sou, 5, true) }
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

        //region HAND BUTTONS
        binding.ivHand1.setOnClickListener { deleteTile(0) }
        binding.ivHand2.setOnClickListener { deleteTile(1) }
        binding.ivHand3.setOnClickListener { deleteTile(2) }
        binding.ivHand4.setOnClickListener { deleteTile(3) }
        binding.ivHand5.setOnClickListener { deleteTile(4) }
        binding.ivHand6.setOnClickListener { deleteTile(5) }
        binding.ivHand7.setOnClickListener { deleteTile(6) }
        binding.ivHand8.setOnClickListener { deleteTile(7) }
        binding.ivHand9.setOnClickListener { deleteTile(8) }
        binding.ivHand10.setOnClickListener { deleteTile(9) }
        binding.ivHand11.setOnClickListener { deleteTile(10) }
        binding.ivHand12.setOnClickListener { deleteTile(11) }
        binding.ivHand13.setOnClickListener { deleteTile(12) }
        binding.ivHand14.setOnClickListener { deleteTile(13) }
        //endregion

        binding.btnCalc.setOnClickListener {
            Log.d(null, hand.checkIfValid().toString())
        }
    }

    private fun newTile(suit: Suit, value: Int, dora: Boolean = false) {
        if(hand.addTile(suit, value, dora)) {
            redrawHand()
        }
    }

    private fun deleteTile(index: Int) {
        if(hand.deleteTile(index)) {
            redrawHand()
        }
    }

    private fun redrawHand() {
        for (i in 0 until hand.tiles.size) {
            if(hand.tiles[i] != null) {
                hand.tiles[i]?.toDrawable(baseContext)?.let {
                    findViewById<ImageView>(binding.HandGroup.referencedIds[i]).setImageResource(it)
                }
            }
            else {
                findViewById<ImageView>(binding.HandGroup.referencedIds[i]).setImageResource(R.drawable.blank)
            }
        }
    }
}