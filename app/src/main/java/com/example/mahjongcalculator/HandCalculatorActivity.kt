package com.example.mahjongcalculator

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.mahjongcalculator.databinding.ActivityHandCalculatorBinding
import org.mahjong4j.GeneralSituation
import org.mahjong4j.PersonalSituation

private lateinit var binding: ActivityHandCalculatorBinding


private const val NUM_PAGES = 2

class HandCalculatorActivity : FragmentActivity(), SituationFragment.SituationListener, HandFragment.HandListener {
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHandCalculatorBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewPager = binding.pager
        val pagerAdapter = HandCalculatorAdapter(this)
        viewPager.adapter = pagerAdapter
    }

    override fun getHand(hand: HandContainer): HandContainer {
        return hand
    }

    /*private fun evaluate() {
        val personalSituation = PersonalSituation(
            situationFragment.binding.tsumo.isChecked,
            situationFragment.binding.ippatsu.isChecked,
            situationFragment.binding.riichi.isChecked,
            situationFragment.binding.doubleRiichi.isChecked,
            situationFragment.binding.chankan.isChecked,
            situationFragment.binding.rinshankaiho.isChecked,
            seatWind.toTileEnum()
        )

        val generalSituation = GeneralSituation(
            situationFragment.binding.firstRound.isChecked,
            situationFragment.binding.houtei.isChecked,
            prevalentWind.toTileEnum(),
            dora.map { it.toTileEnum() }.toList(),
            uradora.map { it.toTileEnum() }.toList()
        )

        hand.calculate(personalSituation, generalSituation)

        Intent(this, HandResultActivity::class.java).also { intent ->
            intent.putExtra("score", hand.getPoints())
            intent.putExtra("han", hand.getHan())
            intent.putExtra("fu", hand.getFu())

            val yaku = hand.getYaku().map { it.toString() }.toTypedArray()
            intent.putExtra("yaku", yaku)

            val yakuman = hand.getYakuman().map { it.toString() }.toTypedArray()
            intent.putExtra("yakuman", yakuman)

            startActivity(intent)
        }
    }*/

    /*private fun setSeatWind(value: Int) {
        seatWind = MTile(Suit.Wind, value)
        situationFragment.redrawSeatWind(value - 1)
    }

    private fun setPrevalentWind(value: Int) {
        prevalentWind = MTile(Suit.Wind, value)
        situationFragment.redrawPrevalentWind(value - 1)
    }*/

    /*fun newTile(suit: Suit, value: Int) {
        if(placeDora() || placeUradora()) {
            if(placeDora()) {
                if(dora.size >= 5) {
                    return
                }
                else {
                    dora.add(MTile(suit, value))
                    redrawDora()
                }
            }
            else if(placeUradora()) {
                if(uradora.size >= 5) {
                    return
                }
                else {
                    uradora.add(MTile(suit, value))
                    redrawDora()
                }
            }
        }
        else if(placeHand()){
            if (placeTriplet()) {
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
                }
            } else if (placeOKan() || placeCKan()) {
                currentMeld.add(MTile(suit, value))

                if (currentMeld.size == 4) {
                    if (!hand.addMeld(currentMeld, placeCKan())) {
                        Toast.makeText(applicationContext, "Invalid Kan", Toast.LENGTH_SHORT).show()
                    }
                    currentMeld = mutableListOf()
                }
            } else {
                hand.addTile(suit, value)
            }

            redrawHand()
        }
        else if(lastTile()) {
            hand.last = MTile(suit, value)
            //situationBinding.ivLastTile.setImageResource(hand.last.toDrawable(baseContext))
        }
    }

    private fun deleteTile(index: Int) {
        if (hand.deleteTile(index)) {
            redrawHand()
        }
    }

    private fun deleteDora(index: Int) {
        if(placeDora()) {
            if(index < dora.size) {
                uradora.removeAt(index)
                redrawDora()
            }
        }
    }

    private fun deleteUradora(index: Int) {
        if(placeUradora()) {
            if(index < uradora.size) {
                uradora.removeAt(index)
                redrawDora()
            }
        }
    }

    private fun redrawDora() {
        for (i in 0 until 5) {
            if(placeDora()) {
                findViewById<ImageView>(handBinding.doraGroup.referencedIds[i]).setBackgroundResource(R.drawable.highlight)
                findViewById<ImageView>(handBinding.uradoraGroup.referencedIds[i]).setBackgroundResource(R.drawable.front)
            }
            else if(placeUradora()) {
                findViewById<ImageView>(handBinding.doraGroup.referencedIds[i]).setBackgroundResource(R.drawable.front)
                findViewById<ImageView>(handBinding.uradoraGroup.referencedIds[i]).setBackgroundResource(R.drawable.highlight)
            }

            if(i < dora.size) {
                dora[i].toDrawable(baseContext).let {
                    findViewById<ImageView>(handBinding.doraGroup.referencedIds[i]).setImageResource(it)

                }
            }
            else {
                findViewById<ImageView>(handBinding.doraGroup.referencedIds[i]).setImageResource(R.drawable.blank)
            }

            if(i < uradora.size) {
                uradora[i].toDrawable(baseContext).let {
                    findViewById<ImageView>(handBinding.uradoraGroup.referencedIds[i]).setImageResource(it)
                }
            }
            else {
                findViewById<ImageView>(handBinding.uradoraGroup.referencedIds[i]).setImageResource(R.drawable.blank)
            }
        }
    }

    private fun redrawHand() {
        for (i in 0 until hand.maxNumOfTiles) {
            if(i < hand.tiles.size) {
                hand.tiles[i].toDrawable(baseContext).let {
                    findViewById<ImageView>(handBinding.HandGroup.referencedIds[i]).setImageResource(it)
                }
            }
            else {
                findViewById<ImageView>(handBinding.HandGroup.referencedIds[i]).setImageResource(R.drawable.blank)
            }
        }

        var numOfThree = 0
        var numOfFour = 0
        hand.melds.forEach {
            val skip = numOfThree * 3 + numOfFour * 4

            if(it.tiles.size == 4) {
                for(k in it.tiles.indices) {
                    if(k == 0 || k == 4 && it.closed) {
                        findViewById<ImageView>(handBinding.HandGroup.referencedIds[hand.tiles.size + k + skip]).setImageResource(R.drawable.back)
                    }
                    else {
                        findViewById<ImageView>(handBinding.HandGroup.referencedIds[hand.tiles.size + k + skip]).setImageResource(it.tiles[k].toDrawable(baseContext))
                    }
                }

                numOfFour++
            }
            else if(it.tiles.size == 3) {
                for(k in it.tiles.indices) {
                    findViewById<ImageView>(handBinding.HandGroup.referencedIds[hand.tiles.size + k + skip]).setImageResource(it.tiles[k].toDrawable(baseContext))
                }

                numOfThree++
            }
        }

    }*/

    private inner class HandCalculatorAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment {
            return when(position) {
                0 -> HandFragment()
                1 -> SituationFragment()
                else -> Fragment()
            }

        }
    }
}