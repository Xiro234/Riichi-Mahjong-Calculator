package com.example.mahjongcalculator

class Hand {
    var tiles = arrayOfNulls<Tile>(13)
    var numOfTiles: Int = 0

    fun addTile(tile: Tile) {
        tiles[numOfTiles] = tile
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