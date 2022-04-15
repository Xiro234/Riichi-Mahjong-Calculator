package com.example.mahjongcalculator

import android.content.Context

enum class Suit {
    Sou,
    Man,
    Pin,
    Wind, //1: East, 2: South, 3: West, 4: North
    Dragon //1: White, 2: Green, 3: Red
}

class MTile(inputSuit: Suit, inputValue: Int, isDora: Boolean = false) {
    var suit: Suit = inputSuit
    var value: Int = inputValue
    var winning: Boolean = false
    var visited: Boolean = false
    var dora: Boolean = isDora

    override operator fun equals(other: Any?) = (other is MTile) && (other.suit == this.suit) && (other.value == this.value)

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

        if(value == 5 && dora) {
            return c.resources.getIdentifier(suitString + 5 + "_dora", "drawable", c.getPackageName())
        }

        return c.resources.getIdentifier(suitString + value, "drawable", c.getPackageName())
    }


    fun isBelow(MTile: MTile): Boolean {
        if(suit != MTile.suit) {
            return false
        }

        return (value + 1) == MTile.value
    }

    fun getIndex(): Int {
        return when (suit) {
            Suit.Pin -> {
                value - 1
            }
            Suit.Sou -> {
                (9 + value) - 1
            }
            Suit.Man -> {
                (18 + value) - 1
            }
            Suit.Wind -> {
                (27 + value) - 1
            }
            else -> {
                (31 + value) - 1
            }
        }
    }
}