package com.example.mahjongcalculator

import android.content.Context

enum class Suit {
    Sou,
    Man,
    Pin,
    Wind, //1: North, 2: East, 3: South, 4: West
    Dragon //1: White, 2: Green, 3: Red
}

class Tile(inputSuit: Suit, inputValue: Int) {
    var suit = inputSuit
    var value = inputValue //Value of 10 equals red 5

    fun toDrawable(c: Context): Int {
        var suitString : String = suit.toString().lowercase()

        if(suitString == "wind") {
            return when(value) {
                1 -> R.drawable.ton
                2 -> R.drawable.nan
                3 -> R.drawable.shaa
                4 -> R.drawable.pei
                else -> R.drawable.blank
            }
        }

        if(suitString == "dragon") {
            return when(value) {
                1 -> R.drawable.haku
                2 -> R.drawable.hatsu
                3 -> R.drawable.chun
                else -> R.drawable.blank
            }
        }

        if(value == 10) {
            return c.resources.getIdentifier(suitString + 5 + "_dora", "drawable", c.getPackageName())
        }

        return c.resources.getIdentifier(suitString + value, "drawable", c.getPackageName())
    }
}