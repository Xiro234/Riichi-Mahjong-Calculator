package com.example.mahjongcalculator

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.mahjongcalculator.databinding.ActivityHandCalculatorBinding
import com.example.mahjongcalculator.databinding.FragmentHandBinding
import org.mahjong4j.GeneralSituation
import org.mahjong4j.PersonalSituation

private lateinit var binding: ActivityHandCalculatorBinding
private lateinit var handFragment: HandFragment
private lateinit var situationFragment: SituationFragment


private const val NUM_PAGES = 2

class HandCalculatorActivity : FragmentActivity(), SituationFragment.SituationListener {
    private var hand: HandContainer = HandContainer()
    private var oKan: Boolean = false
    private var cKan: Boolean = false
    private var ponChii: Boolean = false
    private var currentMeld: MutableList<MTile> = mutableListOf()
    private var dora: MutableList<MTile> = mutableListOf()
    private var uradora: MutableList<MTile> = mutableListOf()
    private var seatWind: MTile = MTile(Suit.Wind, 0)
    private var prevalentWind: MTile = MTile(Suit.Wind, 0)
    private var placeDora: Boolean = false
    private var placeUradora: Boolean = false

    private lateinit var viewPager: ViewPager2

    override fun seatWindPressed(num: Int) {
        setSeatWind(num)
    }

    override fun prevelantWindPressed(num: Int) {
        setPrevalentWind(num)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHandCalculatorBinding.inflate(layoutInflater)
        handFragment = supportFragmentManager.fragments[1] as HandFragment
        situationFragment = supportFragmentManager.fragments[1] as SituationFragment
        val view = binding.root
        hand = HandContainer()
        setContentView(view)

        viewPager = binding.pager
        val pagerAdapter = HandCalculatorAdapter(this)
        viewPager.adapter = pagerAdapter

        //region MAN BUTTONS
        handBinding.ivMan1.setOnClickListener { newTile(Suit.Man, 1) }
        handBinding.ivMan2.setOnClickListener { newTile(Suit.Man, 2) }
        handBinding.ivMan3.setOnClickListener { newTile(Suit.Man, 3) }
        handBinding.ivMan4.setOnClickListener { newTile(Suit.Man, 4) }
        handBinding.ivMan5.setOnClickListener { newTile(Suit.Man, 5) }
        handBinding.ivMan6.setOnClickListener { newTile(Suit.Man, 6) }
        handBinding.ivMan7.setOnClickListener { newTile(Suit.Man, 7) }
        handBinding.ivMan8.setOnClickListener { newTile(Suit.Man, 8) }
        handBinding.ivMan9.setOnClickListener { newTile(Suit.Man, 9) }
        //endregion

        //region PIN BUTTONS
        handBinding.ivPin1.setOnClickListener { newTile(Suit.Pin, 1) }
        handBinding.ivPin2.setOnClickListener { newTile(Suit.Pin, 2) }
        handBinding.ivPin3.setOnClickListener { newTile(Suit.Pin, 3) }
        handBinding.ivPin4.setOnClickListener { newTile(Suit.Pin, 4) }
        handBinding.ivPin5.setOnClickListener { newTile(Suit.Pin, 5) }
        handBinding.ivPin6.setOnClickListener { newTile(Suit.Pin, 6) }
        handBinding.ivPin7.setOnClickListener { newTile(Suit.Pin, 7) }
        handBinding.ivPin8.setOnClickListener { newTile(Suit.Pin, 8) }
        handBinding.ivPin9.setOnClickListener { newTile(Suit.Pin, 9) }
        //endregion

        //region SOU BUTTONS
        handBinding.ivSou1.setOnClickListener { newTile(Suit.Sou, 1) }
        handBinding.ivSou2.setOnClickListener { newTile(Suit.Sou, 2) }
        handBinding.ivSou3.setOnClickListener { newTile(Suit.Sou, 3) }
        handBinding.ivSou4.setOnClickListener { newTile(Suit.Sou, 4) }
        handBinding.ivSou5.setOnClickListener { newTile(Suit.Sou, 5) }
        handBinding.ivSou6.setOnClickListener { newTile(Suit.Sou, 6) }
        handBinding.ivSou7.setOnClickListener { newTile(Suit.Sou, 7) }
        handBinding.ivSou8.setOnClickListener { newTile(Suit.Sou, 8) }
        handBinding.ivSou9.setOnClickListener { newTile(Suit.Sou, 9) }
        //endregion

        //region HONOR BUTTONS
        handBinding.ivTon.setOnClickListener { newTile(Suit.Wind, 1) }
        handBinding.ivNan.setOnClickListener { newTile(Suit.Wind, 2) }
        handBinding.ivShaa.setOnClickListener { newTile(Suit.Wind, 3) }
        handBinding.ivPei.setOnClickListener { newTile(Suit.Wind, 4) }

        handBinding.ivHaku.setOnClickListener { newTile(Suit.Dragon, 1) }
        handBinding.ivHatsu.setOnClickListener { newTile(Suit.Dragon, 2) }
        handBinding.ivChun.setOnClickListener { newTile(Suit.Dragon, 3) }
        //endregion

        //region HAND BUTTONS
        handBinding.ivHand1.setOnClickListener { deleteTile(0) }
        handBinding.ivHand2.setOnClickListener { deleteTile(1) }
        handBinding.ivHand3.setOnClickListener { deleteTile(2) }
        handBinding.ivHand4.setOnClickListener { deleteTile(3) }
        handBinding.ivHand5.setOnClickListener { deleteTile(4) }
        handBinding.ivHand6.setOnClickListener { deleteTile(5) }
        handBinding.ivHand7.setOnClickListener { deleteTile(6) }
        handBinding.ivHand8.setOnClickListener { deleteTile(7) }
        handBinding.ivHand9.setOnClickListener { deleteTile(8) }
        handBinding.ivHand10.setOnClickListener { deleteTile(9) }
        handBinding.ivHand11.setOnClickListener { deleteTile(10) }
        handBinding.ivHand12.setOnClickListener { deleteTile(11) }
        handBinding.ivHand13.setOnClickListener { deleteTile(12) }
        //endregion

        //region DORA BUTTONS
        handBinding.ivDora1.setOnClickListener { deleteDora(0) }
        handBinding.ivDora2.setOnClickListener { deleteDora(1) }
        handBinding.ivDora3.setOnClickListener { deleteDora(2) }
        handBinding.ivDora4.setOnClickListener { deleteDora(3) }
        handBinding.ivDora5.setOnClickListener { deleteDora(4) }

        handBinding.ivUradora1.setOnClickListener { deleteUradora(0) }
        handBinding.ivUradora2.setOnClickListener { deleteUradora(1) }
        handBinding.ivUradora3.setOnClickListener { deleteUradora(2) }
        handBinding.ivUradora4.setOnClickListener { deleteUradora(3) }
        handBinding.ivUradora5.setOnClickListener { deleteUradora(4) }
        //

        /*//region WIND BUTTONS
        situationBinding.ivSeatWind1.setOnClickListener { setSeatWind(0) }
        situationBinding.ivSeatWind2.setOnClickListener { setSeatWind(1) }
        situationBinding.ivSeatWind3.setOnClickListener { setSeatWind(2) }
        situationBinding.ivSeatWind4.setOnClickListener { setSeatWind(3) }

        situationBinding.ivPrevalentWind1.setOnClickListener { setPrevalentWind(0) }
        situationBinding.ivPrevalentWind2.setOnClickListener { setPrevalentWind(1) }
        situationBinding.ivPrevalentWind3.setOnClickListener { setPrevalentWind(2) }
        situationBinding.ivPrevalentWind4.setOnClickListener { setPrevalentWind(3) }
        */

        //binding.btnNext.setOnClickListener { next() }

        handBinding.btnOKan.setOnClickListener {
            oKan = !oKan
            if(!oKan) {
                currentMeld = mutableListOf()
            }
        }

        handBinding.btnCKan.setOnClickListener {
            cKan = !cKan
            if(!cKan) {
                currentMeld = mutableListOf()
            }
        }

        handBinding.btnPonChii.setOnClickListener {
            ponChii = !ponChii
            if(!ponChii) {
                currentMeld = mutableListOf()
            }
        }
    }

    private fun next() {
        if(handBinding.root.visibility == View.VISIBLE) {
            handBinding.root.visibility = View.GONE
            handBinding.root.visibility = View.VISIBLE
            placeDora = true
        }
        else if(handBinding.root.visibility == View.VISIBLE) {
            if(placeDora) {
                placeDora = false
                placeUradora = true
                redrawDora()
            }
            else if(placeUradora) {
                placeDora = false
                placeUradora = false

                handBinding.root.visibility = View.GONE
                situationBinding.root.visibility = View.VISIBLE
                //situationBinding.ivLastTile.setImageResource(hand.last.toDrawable(baseContext))
            }
        }
        else if(situationBinding.root.visibility == View.VISIBLE) {
            val personalSituation = PersonalSituation(
                situationBinding.tsumo.isChecked,
                situationBinding.ippatsu.isChecked,
                situationBinding.riichi.isChecked,
                situationBinding.doubleRiichi.isChecked,
                situationBinding.chankan.isChecked,
                situationBinding.rinshankaiho.isChecked,
                seatWind.toTileEnum()
            )

            val generalSituation = GeneralSituation(
                situationBinding.firstRound.isChecked,
                situationBinding.houtei.isChecked,
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
        }
    }

    private fun setSeatWind(value: Int) {
        seatWind = MTile(Suit.Wind, value)
        situationFragment.redrawSeatWind(value - 1)
    }

    private fun setPrevalentWind(value: Int) {
        prevalentWind = MTile(Suit.Wind, value)
        situationFragment.redrawPrevalentWind(value - 1)
    }

    fun newTile(suit: Suit, value: Int) {
        if(handBinding.root.visibility == View.VISIBLE) {
            if(placeDora) {
                if(dora.size >= 5) {
                    return
                }
                else {
                    dora.add(MTile(suit, value))
                    redrawDora()
                }
            }
            else if(placeUradora) {
                if(uradora.size >= 5) {
                    return
                }
                else {
                    uradora.add(MTile(suit, value))
                    redrawDora()
                }
            }
        }
        else if(handBinding.root.visibility == View.VISIBLE){
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
        else if(situationBinding.root.visibility == View.VISIBLE) {
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
        if(situationBinding.root.visibility == View.VISIBLE && placeDora) {
            if(index < dora.size) {
                uradora.removeAt(index)
                redrawDora()
            }
        }
    }

    private fun deleteUradora(index: Int) {
        if(situationBinding.root.visibility == View.VISIBLE && placeUradora) {
            if(index < uradora.size) {
                uradora.removeAt(index)
                redrawDora()
            }
        }
    }

    private fun redrawDora() {
        for (i in 0 until 5) {
            if(placeDora) {
                findViewById<ImageView>(handBinding.doraGroup.referencedIds[i]).setBackgroundResource(R.drawable.highlight)
                findViewById<ImageView>(handBinding.uradoraGroup.referencedIds[i]).setBackgroundResource(R.drawable.front)
            }
            else if(placeUradora) {
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

    }

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