package com.example.mahjongcalculator

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.mahjongcalculator.databinding.ActivityHandCalculatorBinding
import com.example.mahjongcalculator.databinding.FragmentHandBinding
import com.example.mahjongcalculator.databinding.FragmentSituationBinding

class SituationFragment : Fragment() {

    private lateinit var binding: FragmentSituationBinding
    private lateinit var listener: SituationListener
    private var seatWind: MTile = MTile(Suit.Wind, 0)
    private var prevalentWind: MTile = MTile(Suit.Wind, 0)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is SituationListener) {
            listener = context
        }
        else {
            throw ClassCastException("$context must implement SituationListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSituationBinding.inflate(inflater, container, false)

        binding.ivSeatWind1.setOnClickListener { setSeatWind(1) }
        binding.ivSeatWind2.setOnClickListener { setSeatWind(2) }
        binding.ivSeatWind3.setOnClickListener { setSeatWind(3) }
        binding.ivSeatWind4.setOnClickListener { setSeatWind(4) }

        binding.ivPrevalentWind1.setOnClickListener { setPrevalentWind(1) }
        binding.ivPrevalentWind2.setOnClickListener { setPrevalentWind(2) }
        binding.ivPrevalentWind3.setOnClickListener { setPrevalentWind(3) }
        binding.ivPrevalentWind4.setOnClickListener { setPrevalentWind(4) }

        return binding.root
    }

    interface SituationListener {
    }

    private fun setSeatWind(value: Int) {
        seatWind = MTile(Suit.Wind, value)
        redrawSeatWind(value - 1)
    }

    private fun setPrevalentWind(value: Int) {
        prevalentWind = MTile(Suit.Wind, value)
        redrawPrevalentWind(value - 1)
    }

    fun redrawSeatWind(value: Int) {
        binding.SeatWinds.referencedIds.forEach { view?.findViewById<ImageView>(it)?.setBackgroundResource(R.drawable.front) }
        view?.findViewById<ImageView>(binding.SeatWinds.referencedIds[value])?.setBackgroundResource(R.drawable.highlight)
    }

    fun redrawPrevalentWind(value: Int) {
        binding.RoundWinds.referencedIds.forEach { view?.findViewById<ImageView>(it)?.setBackgroundResource(R.drawable.front) }
        view?.findViewById<ImageView>(binding.RoundWinds.referencedIds[value])?.setBackgroundResource(R.drawable.highlight)
    }
}