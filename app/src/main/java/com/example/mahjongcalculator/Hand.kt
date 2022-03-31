package com.example.mahjongcalculator

class Hand {
    var tiles = arrayOfNulls<Tile>(13)
    var numOfTiles: Int = 0

    fun addTile(suit: Suit, value: Int) {
        if(numOfTiles == 13) {
            return
        }

        tiles[numOfTiles] = Tile(suit, value)
        numOfTiles++
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