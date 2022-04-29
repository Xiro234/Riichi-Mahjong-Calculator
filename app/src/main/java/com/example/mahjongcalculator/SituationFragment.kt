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

        binding.ivSeatWind1.setOnClickListener { listener.seatWindPressed(1) }
        binding.ivSeatWind2.setOnClickListener { listener.seatWindPressed(2) }
        binding.ivSeatWind3.setOnClickListener { listener.seatWindPressed(3) }
        binding.ivSeatWind4.setOnClickListener { listener.seatWindPressed(4) }

        binding.ivPrevalentWind1.setOnClickListener { listener.seatWindPressed(1) }
        binding.ivPrevalentWind2.setOnClickListener { listener.seatWindPressed(2) }
        binding.ivPrevalentWind3.setOnClickListener { listener.seatWindPressed(3) }
        binding.ivPrevalentWind4.setOnClickListener { listener.seatWindPressed(4) }

        return binding.root
    }

    interface SituationListener {
        fun seatWindPressed(num: Int)
        fun prevelantWindPressed(num: Int)
    }

    fun redrawSeatWind(value: Int) {
        binding.SeatWinds.referencedIds.forEach { view?.findViewById<ImageView>(it)?.setBackgroundResource(R.drawable.front) }
        view?.findViewById<ImageView>(binding.SeatWinds.referencedIds[value])?.setBackgroundResource(R.drawable.highlight)
    }

    fun redrawPrevelantWind(value: Int) {
        binding.RoundWinds.referencedIds.forEach { view?.findViewById<ImageView>(it)?.setBackgroundResource(R.drawable.front) }
        view?.findViewById<ImageView>(binding.RoundWinds.referencedIds[value])?.setBackgroundResource(R.drawable.highlight)
    }
}