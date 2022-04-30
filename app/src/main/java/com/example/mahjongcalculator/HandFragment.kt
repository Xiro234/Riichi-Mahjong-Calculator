package com.example.mahjongcalculator

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.mahjongcalculator.databinding.FragmentHandBinding
import com.google.android.material.tabs.TabLayout

class HandFragment : Fragment() {
    private var hand: HandContainer = HandContainer()
    private var currentMeld: MutableList<MTile> = mutableListOf()
    private var dora: MutableList<MTile> = mutableListOf()
    private var uradora: MutableList<MTile> = mutableListOf()

    private lateinit var binding: FragmentHandBinding
    private lateinit var listener: HandListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is HandListener) {
            listener = context
        }
        else {
            throw ClassCastException("$context must implement SituationListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHandBinding.inflate(inflater, container, false)
        hand = HandContainer()

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
        //endregion

        //region HONOR BUTTONS
        binding.ivTon.setOnClickListener { newTile(Suit.Wind, 1) }
        binding.ivNan.setOnClickListener { newTile(Suit.Wind, 2) }
        binding.ivShaa.setOnClickListener { newTile(Suit.Wind, 3) }
        binding.ivPei.setOnClickListener { newTile(Suit.Wind, 4) }

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
        //endregion

        //region DORA BUTTONS
        binding.ivDora1.setOnClickListener { deleteDora(0) }
        binding.ivDora2.setOnClickListener { deleteDora(1) }
        binding.ivDora3.setOnClickListener { deleteDora(2) }
        binding.ivDora4.setOnClickListener { deleteDora(3) }
        binding.ivDora5.setOnClickListener { deleteDora(4) }

        binding.ivUradora1.setOnClickListener { deleteUradora(0) }
        binding.ivUradora2.setOnClickListener { deleteUradora(1) }
        binding.ivUradora3.setOnClickListener { deleteUradora(2) }
        binding.ivUradora4.setOnClickListener { deleteUradora(3) }
        binding.ivUradora5.setOnClickListener { deleteUradora(4) }
        //

        binding.tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    setVisibility(tab)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    unsetVisibility(tab)
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) { }
        })

        binding.btnPonChii.setOnClickListener {
            if(binding.btnPonChii.isChecked) {
                binding.btnOKan.isChecked = false
                binding.btnCKan.isChecked = false
            }
            currentMeld = mutableListOf()
        }
        binding.btnOKan.setOnClickListener {
            if(binding.btnOKan.isChecked) {
                binding.btnPonChii.isChecked = false
                binding.btnCKan.isChecked = false
            }
            currentMeld = mutableListOf()
        }
        binding.btnCKan.setOnClickListener {
            if(binding.btnCKan.isChecked) {
                binding.btnOKan.isChecked = false
                binding.btnPonChii.isChecked = false
            }
            currentMeld = mutableListOf()
        }


        return binding.root
    }

    interface HandListener {
        fun getHand(hand: HandContainer): HandContainer
    }

    private fun setVisibility(tab: TabLayout.Tab?) {
        if(tab == binding.tabLayout.getTabAt(0)) {
            binding.HandGroup.visibility = View.VISIBLE
        }
        else if(tab == binding.tabLayout.getTabAt(1)) {
            binding.doraGroup.visibility = View.VISIBLE
        }
        else if(tab == binding.tabLayout.getTabAt(2)) {
            binding.uradoraGroup.visibility = View.VISIBLE
        }
    }

    private fun unsetVisibility(tab: TabLayout.Tab?){
        if(tab == binding.tabLayout.getTabAt(0)) {
            binding.HandGroup.visibility = View.GONE
        }
        else if(tab == binding.tabLayout.getTabAt(1)) {
            binding.doraGroup.visibility = View.GONE
        }
        else if(tab == binding.tabLayout.getTabAt(2)) {
            binding.uradoraGroup.visibility = View.GONE
        }
    }

    private fun deleteTile(index: Int) {
        if (hand.deleteTile(index)) {
            redrawHand()
        }
    }

    private fun deleteDora(index: Int) {
        if(binding.tabLayout.getTabAt(1)?.isSelected == true) {
            if(index < dora.size) {
                dora.removeAt(index)
                redrawDora()
            }
        }
    }

    private fun deleteUradora(index: Int) {
        if(binding.tabLayout.getTabAt(2)?.isSelected == true) {
            if(index < uradora.size) {
                uradora.removeAt(index)
                redrawDora()
            }
        }
    }

    private fun redrawDora() {
        for (i in 0 until 5) {
            if(i < dora.size) {
                dora[i].toDrawable(requireActivity().baseContext).let {
                    view?.findViewById<ImageView>(binding.doraGroup.referencedIds[i])?.setImageResource(it)
                }
            }
            else {
                view?.findViewById<ImageView>(binding.doraGroup.referencedIds[i])?.setImageResource(R.drawable.blank)
            }

            if(i < uradora.size) {
                uradora[i].toDrawable(requireActivity().baseContext).let {
                    view?.findViewById<ImageView>(binding.uradoraGroup.referencedIds[i])?.setImageResource(it)
                }
            }
            else {
                view?.findViewById<ImageView>(binding.uradoraGroup.referencedIds[i])?.setImageResource(R.drawable.blank)
            }
        }
    }

    private fun newTile(suit: Suit, value: Int) {
        if(binding.btnLastTile.isChecked) {
            hand.last = MTile(suit, value)
            binding.ivLastTile.setImageResource(hand.last.toDrawable(requireActivity().baseContext))
        }
        else if(binding.tabLayout.getTabAt(1)?.isSelected == true || binding.tabLayout.getTabAt(2)?.isSelected == true) {
            if(binding.tabLayout.getTabAt(1)?.isSelected == true) {
                if(dora.size >= 5) {
                    return
                }
                else {
                    dora.add(MTile(suit, value))
                    redrawDora()
                }
            }
            else if(binding.tabLayout.getTabAt(2)?.isSelected == true) {
                if(uradora.size >= 5) {
                    return
                }
                else {
                    uradora.add(MTile(suit, value))
                    redrawDora()
                }
            }
        }
        else if(binding.tabLayout.getTabAt(0)?.isSelected == true){
            if (binding.btnPonChii.isChecked) {
                currentMeld.add(MTile(suit, value))
                println(currentMeld.size)
                if (currentMeld.size == 3) {
                    if (!hand.addMeld(currentMeld)) {
                        Toast.makeText(
                            activity,
                            "Invalid Pon or Chii",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    currentMeld = mutableListOf()
                }
            } else if (binding.btnOKan.isChecked || binding.btnCKan.isChecked) {
                currentMeld.add(MTile(suit, value))

                if (currentMeld.size == 4) {
                    if (!hand.addMeld(currentMeld, binding.btnCKan.isChecked)) {
                        Toast.makeText(activity, "Invalid Kan", Toast.LENGTH_SHORT).show()
                    }
                    currentMeld = mutableListOf()
                }
            }
            else {
                hand.addTile(suit, value)
            }

            redrawHand()
        }
    }

    private fun redrawHand() {
        for (i in 0 until hand.maxNumOfTiles) {
            if(i < hand.tiles.size) {
                hand.tiles[i].toDrawable(requireActivity().baseContext).let {
                    view?.findViewById<ImageView>(binding.HandGroup.referencedIds[i])?.setImageResource(it)
                }
            }
            else {
                view?.findViewById<ImageView>(binding.HandGroup.referencedIds[i])?.setImageResource(R.drawable.blank)
            }
        }

        var numOfThree = 0
        var numOfFour = 0
        hand.melds.forEach {
            val skip = numOfThree * 3 + numOfFour * 4

            if(it.tiles.size == 4) {
                for(k in it.tiles.indices) {
                    if((k == 0 || k == 3) && it.closed) {
                        view?.findViewById<ImageView>(binding.HandGroup.referencedIds[hand.tiles.size + k + skip])?.setImageResource(R.drawable.back)
                    }
                    else {
                        view?.findViewById<ImageView>(binding.HandGroup.referencedIds[hand.tiles.size + k + skip])?.setImageResource(it.tiles[k].toDrawable(requireActivity().baseContext))
                    }
                }

                numOfFour++
            }
            else if(it.tiles.size == 3) {
                for(k in it.tiles.indices) {
                    view?.findViewById<ImageView>(binding.HandGroup.referencedIds[hand.tiles.size + k + skip])?.setImageResource(it.tiles[k].toDrawable(requireActivity().baseContext))
                }

                numOfThree++
            }
        }

    }
}