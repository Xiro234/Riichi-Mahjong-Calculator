package com.example.mahjongcalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.mahjongcalculator.databinding.ActivityHandCalculatorBinding
import org.mahjong4j.tile.Tile

private lateinit var binding: ActivityHandCalculatorBinding


private const val NUM_PAGES = 2

class HandCalculatorActivity : FragmentActivity(), SituationFragment.SituationListener {
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

    override fun calculate(
        seatWind: Tile,
        prevalentWind: Tile,
        tsumo: Boolean,
        ippatsu: Boolean,
        riichi: Boolean,
        doubleRiichi: Boolean,
        chankan: Boolean,
        rinshankaiho: Boolean,
        firstRound: Boolean,
        houtei: Boolean
    ) {
        (supportFragmentManager.fragments[0] as HandFragment).calculate(seatWind, prevalentWind, tsumo, ippatsu, riichi, doubleRiichi, chankan, rinshankaiho, firstRound, houtei)
    }
}