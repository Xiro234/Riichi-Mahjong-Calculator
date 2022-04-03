package com.example.mahjongcalculator

import android.util.Log

class Hand {
    var tiles = arrayOfNulls<Tile>(13)
    var numOfTiles: Int = 0

    fun addTile(suit: Suit, value: Int): Boolean {
        if(numOfTiles >= 13) {
            return false
        }

        tiles[numOfTiles] = Tile(suit, value)
        numOfTiles++
        Log.d(null, numOfTiles.toString())
        return true
    }

    fun deleteTile(index: Int) : Boolean {
        if(tiles[index] == null) {
            return false
        }

        tiles[index] = null
        for(i in index until numOfTiles) {
            if(i == 12) {
                tiles[i] = null
            }
            else {
                tiles[i] = tiles[i + 1]
            }
        }

        tiles[numOfTiles - 1] = null
        numOfTiles--

        return true
    }
}