package com.example.mahjongcalculator

import android.util.Log
import org.mahjong4j.GeneralSituation
import org.mahjong4j.PersonalSituation
import org.mahjong4j.Player
import org.mahjong4j.Score
import org.mahjong4j.hands.Hands
import org.mahjong4j.tile.Tile

class HandContainer {
    var tiles = mutableListOf<MTile>()
    val maxNumOfTiles = 13
    var last = Tile.M1

    fun addTile(suit: Suit, value: Int, dora: Boolean = false): Boolean {
        if (maxNumOfTiles == tiles.size) {
            return false
        }

        var found = false
        for (i in 0 until tiles.size) {
            if (tiles[i].suit == suit) {
                if (tiles[i].value >= value) {
                    found = true
                }
            } else if (i != 0) {
                if (tiles[i - 1].suit == suit) {
                    found = true
                }
            }

            if (found) {
                tiles.add(i, MTile(suit, value, dora))
                return true
            }
        }

        tiles.add(MTile(suit, value, dora))
        Log.d(null, tiles.size.toString())
        return true
    }

    fun deleteTile(index: Int): Boolean {
        if (index >= tiles.size || index >= maxNumOfTiles) {
            return false
        }

        tiles.removeAt(index)
        return true
    }

    fun getAsArray(): IntArray {
        val array = IntArray(34)

        for(i in 0 until (tiles.size)) {
            array[tiles[i].getIndex()]++
        }

        return array
    }

    fun getValid(): Boolean {
        val array = getAsArray()

        Log.d(null, array.joinToString())

        last = Tile.M6
        val hand = Hands(array, last)
        return hand.canWin
    }

    fun getPoints(): Score {
        return if(getValid()) {
            val array = getAsArray()

            last = Tile.M6
            val hand = Hands(array, last)

            val personalSituation = PersonalSituation(true, false, false, false, false, false, Tile.TON)
            val generalSituation = GeneralSituation(false, false, Tile.TON, listOf(Tile.M1), listOf(Tile.M2))
            val player = Player(hand, generalSituation, personalSituation)

            player.calculate()

            player.score
        } else {
            Score.SCORE0
        }
    }
}
