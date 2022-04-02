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

    fun deleteTile(index: Int) {
        tiles[index] = null
        for(i in index until numOfTiles) {
            if(i == 13) {
                tiles[i] = null
            }
            else {
                tiles[i] = tiles[i + 1]
            }
        }
    }
}