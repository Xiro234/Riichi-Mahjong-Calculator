package com.example.mahjongcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.example.mahjongcalculator.databinding.ActivityHandCalculatorBinding
import org.mahjong4j.GeneralSituation
import org.mahjong4j.PersonalSituation

private lateinit var binding: ActivityHandCalculatorBinding


class HandCalculatorActivity : AppCompatActivity() {
    private var hand: HandContainer = HandContainer()
    private var oKan: Boolean = false
    private var cKan: Boolean = false
    private var ponChii: Boolean = false
    private var currentMeld: MutableList<MTile> = mutableListOf()
    private var dora: MutableList<MTile> = mutableListOf()
    private var seatWind: MTile = MTile(Suit.Wind, 0)
    private var prevalentWind: MTile = MTile(Suit.Wind, 0)

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

        //region DORA BUTTONS
        binding.situation.ivDora1.setOnClickListener { deleteTile(0) }
        binding.situation.ivDora2.setOnClickListener { deleteTile(1) }
        binding.situation.ivDora3.setOnClickListener { deleteTile(2) }
        binding.situation.ivDora4.setOnClickListener { deleteTile(3) }
        binding.situation.ivDora5.setOnClickListener { deleteTile(4) }
        //

        //region WIND BUTTONS
        binding.situation.ivSeatWind1.setOnClickListener { setSeatWind(0) }
        binding.situation.ivSeatWind2.setOnClickListener { setSeatWind(1) }
        binding.situation.ivSeatWind3.setOnClickListener { setSeatWind(2) }
        binding.situation.ivSeatWind4.setOnClickListener { setSeatWind(3) }

        binding.situation.ivPrevalentWind1.setOnClickListener { setPrevalentWind(0) }
        binding.situation.ivPrevalentWind2.setOnClickListener { setPrevalentWind(1) }
        binding.situation.ivPrevalentWind3.setOnClickListener { setPrevalentWind(2) }
        binding.situation.ivPrevalentWind4.setOnClickListener { setPrevalentWind(3) }
        //

        binding.btnNext.setOnClickListener {
            if(binding.hand.root.visibility == View.VISIBLE) {
                binding.hand.root.visibility = View.GONE
                binding.situation.root.visibility = View.VISIBLE
            }
            else if(binding.situation.root.visibility == View.VISIBLE) {
                val personalSituation = PersonalSituation(
                    binding.situation.tsumo.isChecked,
                    binding.situation.ippatsu.isChecked,
                    binding.situation.riichi.isChecked,
                    binding.situation.doubleRiichi.isChecked,
                    binding.situation.chankan.isChecked,
                    binding.situation.rinshankaiho.isChecked,
                    seatWind.toTileEnum()
                )

                val generalSituation = GeneralSituation(
                    binding.situation.firstRound.isChecked,
                    binding.situation.houtei.isChecked,
                    prevalentWind.toTileEnum(),
                    dora.map { it.toTileEnum() }.toList(),
                    listOf()
                )

                hand.getPoints(personalSituation, generalSituation)
            }
        }

        binding.hand.btnOKan.setOnClickListener {
            oKan = !oKan
            if(!oKan) {
                currentMeld = mutableListOf()
            }
        }

        binding.hand.btnCKan.setOnClickListener {
            cKan = !cKan
            if(!cKan) {
                currentMeld = mutableListOf()
            }
        }

        binding.hand.btnPonChii.setOnClickListener {
            ponChii = !ponChii
            if(!ponChii) {
                currentMeld = mutableListOf()
            }
        }
    }

    private fun setSeatWind(value: Int) {
        seatWind = MTile(Suit.Wind, value)
        redrawSeatWind()
    }

    private fun redrawSeatWind() {
        binding.situation.SeatWinds.referencedIds.forEach { findViewById<ImageView>(it).setBackgroundResource(R.drawable.front) }
        findViewById<ImageView>(binding.situation.SeatWinds.referencedIds[seatWind.value]).setBackgroundResource(R.drawable.highlight)
    }

    private fun setPrevalentWind(value: Int) {
        prevalentWind = MTile(Suit.Wind, value)
        redrawPrevalentWind()
    }

    private fun redrawPrevalentWind() {
        binding.situation.RoundWinds.referencedIds.forEach { findViewById<ImageView>(it).setBackgroundResource(R.drawable.front) }
        findViewById<ImageView>(binding.situation.RoundWinds.referencedIds[prevalentWind.value]).setBackgroundResource(R.drawable.highlight)
    }

    private fun newTile(suit: Suit, value: Int) {
        if(binding.situation.root.visibility == View.VISIBLE) {
            if(dora.size >= 5) {
                return
            }
            else {
                dora.add(MTile(suit, value))
                redrawDora()
            }
        }
        else {
            if (ponChii) {
                currentMeld.add(MTile(suit, value))

                if (currentMeld.size == 3) {
                    if (!hand.addMeld(currentMeld)) {
                        Toast.makeText(
                            applicationContext,
                            "Invalid Pon or Chii",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    currentMeld = mutableListOf()
                    ponChii = false
                }
            } else if (oKan || cKan) {
                currentMeld.add(MTile(suit, value))

                if (currentMeld.size == 4) {
                    if (!hand.addMeld(currentMeld, cKan)) {
                        Toast.makeText(applicationContext, "Invalid Kan", Toast.LENGTH_SHORT).show()
                    }
                    currentMeld = mutableListOf()
                    oKan = false
                    cKan = false
                }
            } else {
                hand.addTile(suit, value)
            }

            redrawHand()
        }
    }

    private fun deleteTile(index: Int) {
        if(binding.situation.root.visibility == View.VISIBLE) {
            if(index < dora.size) {
                dora.removeAt(index)
                redrawDora()
            }
        }
        else {
            if (hand.deleteTile(index)) {
                redrawHand()
            }
        }
    }

    private fun redrawDora() {
        for (i in 0 until 5) {
            if(i < dora.size) {
                dora[i].toDrawable(baseContext).let {
                    findViewById<ImageView>(binding.situation.DoraGroup.referencedIds[i]).setImageResource(it)
                }
            }
            else {
                findViewById<ImageView>(binding.situation.DoraGroup.referencedIds[i]).setImageResource(R.drawable.blank)
            }
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

        var numOfThree = 0
        var numOfFour = 0
        hand.melds.forEach {
            val skip = numOfThree * 3 + numOfFour * 4

            if(it.tiles.size == 4) {
                for(k in it.tiles.indices) {
                    if(k == 0 || k == 4 && it.closed) {
                        findViewById<ImageView>(binding.hand.HandGroup.referencedIds[hand.tiles.size + k + skip]).setImageResource(R.drawable.back)
                    }
                    else {
                        findViewById<ImageView>(binding.hand.HandGroup.referencedIds[hand.tiles.size + k + skip]).setImageResource(it.tiles[k].toDrawable(baseContext))
                    }
                }

                numOfFour++
            }
            else if(it.tiles.size == 3) {
                for(k in it.tiles.indices) {
                    findViewById<ImageView>(binding.hand.HandGroup.referencedIds[hand.tiles.size + k + skip]).setImageResource(it.tiles[k].toDrawable(baseContext))
                }

                numOfThree++
            }
        }

    }
}