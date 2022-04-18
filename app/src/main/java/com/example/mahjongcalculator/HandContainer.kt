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
    var melds: MutableList<List<MTile>> = mutableListOf()
    val maxNumOfTiles = 13
    private var last = Tile.M1

    fun addTile(suit: Suit, value: Int, dora: Boolean = false): Boolean {
        if (maxNumOfTiles == getSize()) {
            return false
        }

        tiles.add(MTile(suit, value, dora))
        Log.d(null, tiles.size.toString())
        return true
    }

    fun addMeld(currentMeld: List<MTile>): Boolean {
        return if(checkIfMeldFits(currentMeld)) {
            melds.add(currentMeld)
            true
        } else {
            false
        }
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

    fun getSize(): Int {
        return tiles.size + melds.sumOf { it.size }
    }

    private fun checkIfMeldFits(currentMeld: List<MTile>): Boolean {
        return if(currentMeld.size == 4) {
            currentMeld[0] == currentMeld[1] && currentMeld[1] == currentMeld[2] && currentMeld[2] == currentMeld[3] && getSize() <= 9
        } else if(currentMeld.size == 3) {
            if(currentMeld.all { it.suit != Suit.Dragon && it.suit != Suit.Wind }) {
                currentMeld[0].isBelow(currentMeld[1]) && currentMeld[1].isBelow(currentMeld[2]) && getSize() <= 10
            } else {
                currentMeld[0] == currentMeld[1] && currentMeld[1] == currentMeld[2] && getSize() <= 10
            }
        }
        else {
            false
        }
    }
}
