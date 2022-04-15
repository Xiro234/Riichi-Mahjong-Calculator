package com.example.mahjongcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.example.mahjongcalculator.databinding.ActivityHandCalculatorBinding

private lateinit var binding: ActivityHandCalculatorBinding


class HandCalculatorActivity : AppCompatActivity() {
    var hand: HandContainer = HandContainer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHandCalculatorBinding.inflate(layoutInflater)
        val view = binding.root
        hand = HandContainer()
        setContentView(view)

        //region MAN BUTTONS

        binding.calc.ivMan1.setOnClickListener { newTile(Suit.Man, 1) }
        binding.calc.ivMan2.setOnClickListener { newTile(Suit.Man, 2) }
        binding.calc.ivMan3.setOnClickListener { newTile(Suit.Man, 3) }
        binding.calc.ivMan4.setOnClickListener { newTile(Suit.Man, 4) }
        binding.calc.ivMan5.setOnClickListener { newTile(Suit.Man, 5) }
        binding.calc.ivMan6.setOnClickListener { newTile(Suit.Man, 6) }
        binding.calc.ivMan7.setOnClickListener { newTile(Suit.Man, 7) }
        binding.calc.ivMan8.setOnClickListener { newTile(Suit.Man, 8) }
        binding.calc.ivMan9.setOnClickListener { newTile(Suit.Man, 9) }
        //endregion

        //region PIN BUTTONS
        binding.calc.ivPin1.setOnClickListener { newTile(Suit.Pin, 1) }
        binding.calc.ivPin2.setOnClickListener { newTile(Suit.Pin, 2) }
        binding.calc.ivPin3.setOnClickListener { newTile(Suit.Pin, 3) }
        binding.calc.ivPin4.setOnClickListener { newTile(Suit.Pin, 4) }
        binding.calc.ivPin5.setOnClickListener { newTile(Suit.Pin, 5) }
        binding.calc.ivPin6.setOnClickListener { newTile(Suit.Pin, 6) }
        binding.calc.ivPin7.setOnClickListener { newTile(Suit.Pin, 7) }
        binding.calc.ivPin8.setOnClickListener { newTile(Suit.Pin, 8) }
        binding.calc.ivPin9.setOnClickListener { newTile(Suit.Pin, 9) }
        //endregion

        //region SOU BUTTONS
        binding.calc.ivSou1.setOnClickListener { newTile(Suit.Sou, 1) }
        binding.calc.ivSou2.setOnClickListener { newTile(Suit.Sou, 2) }
        binding.calc.ivSou3.setOnClickListener { newTile(Suit.Sou, 3) }
        binding.calc.ivSou4.setOnClickListener { newTile(Suit.Sou, 4) }
        binding.calc.ivSou5.setOnClickListener { newTile(Suit.Sou, 5) }
        binding.calc.ivSou6.setOnClickListener { newTile(Suit.Sou, 6) }
        binding.calc.ivSou7.setOnClickListener { newTile(Suit.Sou, 7) }
        binding.calc.ivSou8.setOnClickListener { newTile(Suit.Sou, 8) }
        binding.calc.ivSou9.setOnClickListener { newTile(Suit.Sou, 9) }
        //endregion

        //region HONOR BUTTONS
        binding.calc.ivTon.setOnClickListener { newTile(Suit.Wind, 1) }
        binding.calc.ivNan.setOnClickListener { newTile(Suit.Wind, 2) }
        binding.calc.ivShaa.setOnClickListener { newTile(Suit.Wind, 3) }
        binding.calc.ivPei.setOnClickListener { newTile(Suit.Wind, 4) }

        binding.calc.ivHaku.setOnClickListener { newTile(Suit.Dragon, 1) }
        binding.calc.ivHatsu.setOnClickListener { newTile(Suit.Dragon, 2) }
        binding.calc.ivChun.setOnClickListener { newTile(Suit.Dragon, 3) }
        //endregion

        //region HAND BUTTONS
        binding.hand.ivHand1.setOnClickListener { deleteTile(0) }
        binding.hand.ivHand2.setOnClickListener { deleteTile(1) }
        binding.hand.ivHand3.setOnClickListener { deleteTile(2) }
        binding.hand.ivHand4.setOnClickListener { deleteTile(3) }
        binding.hand.ivHand5.setOnClickListener { deleteTile(4) }
        binding.hand.ivHand6.setOnClickListener { deleteTile(5) }
        binding.hand.ivHand7.setOnClickListener { deleteTile(6) }
        binding.hand.ivHand8.setOnClickListener { deleteTile(7) }
        binding.hand.ivHand9.setOnClickListener { deleteTile(8) }
        binding.hand.ivHand10.setOnClickListener { deleteTile(9) }
        binding.hand.ivHand11.setOnClickListener { deleteTile(10) }
        binding.hand.ivHand12.setOnClickListener { deleteTile(11) }
        binding.hand.ivHand13.setOnClickListener { deleteTile(12) }
        //endregion

        /*binding.btnCalc.setOnClickListener {
            Log.d(null, hand.getPoints().toString())
        }*/
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
        for (i in 0 until hand.maxNumOfTiles) {
            if(i < hand.tiles.size) {
                hand.tiles[i].toDrawable(baseContext).let {
                    findViewById<ImageView>(binding.hand.HandGroup.referencedIds[i]).setImageResource(it)
                }
            }
            else {
                findViewById<ImageView>(binding.hand.HandGroup.referencedIds[i]).setImageResource(R.drawable.blank)
            }
        }
    }
}