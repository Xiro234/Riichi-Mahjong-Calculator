package com.example.mahjongcalculator

import android.content.Context
import android.util.Log

enum class Suit {
    Sou,
    Man,
    Pin,
    Wind, //1: North, 2: East, 3: South, 4: West
    Dragon //1: White, 2: Green, 3: Red
}

class Tile(inputSuit: Suit, inputValue: Int, isDora: Boolean = false) {
    var suit: Suit = inputSuit
    var value: Int = inputValue
    var winning: Boolean = false
    var visited: Boolean = false
    var dora: Boolean = isDora

    override operator fun equals(other: Any?) = (other is Tile) && (other.suit == this.suit) && (other.value == this.value)

    fun toDrawable(c: Context): Int {
        var suitString : String = suit.toString().lowercase()

        if(suitString == "wind") {
            return when(value) {
                1 -> R.drawable.pei
                2 -> R.drawable.ton
                3 -> R.drawable.nan
                4 -> R.drawable.shaa
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


    fun isBelow(tile: Tile): Boolean {
        if(suit != tile.suit) {
            return false
        }

        return (value + 1) == tile.value
    }

    companion object {
        /**
         * tile1, tile2, tile3 must be consecutive, otherwise weird behavior happens.
         */
        fun isMeld(tile1: Tile, tile2: Tile, tile3: Tile): Boolean {
            return (tile1.isBelow(tile2) && tile2.isBelow(tile3)) || (tile1 == tile2 && tile2 == tile3)
        }
    }
}