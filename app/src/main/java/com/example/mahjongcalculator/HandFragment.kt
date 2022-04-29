package com.example.mahjongcalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.mahjongcalculator.databinding.ActivityHandCalculatorBinding
import com.example.mahjongcalculator.databinding.FragmentHandBinding

class HandFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_hand, container, false)

        view.findViewById<ImageView>(R.id.ivMan1).setOnClickListener { HandCalculatorActivity().newTile(Suit.Man, 1) }

        return view
    }
}