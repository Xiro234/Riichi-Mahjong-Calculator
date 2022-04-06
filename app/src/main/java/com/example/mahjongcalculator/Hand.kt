package com.example.mahjongcalculator

import android.util.Log

class Hand {
    var tiles = arrayOfNulls<Tile>(14)
    var numOfTiles: Int = 0

    fun addTile(suit: Suit, value: Int, dora: Boolean = false): Boolean {
        if(numOfTiles >= tiles.size) {
            return false
        }

        tiles[numOfTiles] = Tile(suit, value, dora)
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
            if(i == tiles.size - 1) {
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

    fun checkIfValid(): Boolean { //Using https://stackoverflow.com/questions/4937771/mahjong-winning-hand-algorithm as a reference
        if(!tiles.all { it != null }) {
            return false
        }

        tiles.forEach {
            it?.winning = false
            it?.visited = false
        }

        return recursiveCheck()
    }

    private fun recursiveCheck(): Boolean {
        if(tiles.all { it?.winning == true } ) {
            return true
        }

        for(i in 0 until numOfTiles) {
            tiles[i]?.let { tile ->
                if(!tile.visited) {
                    tile.visited = true
                    var set : MutableList<Tile> = mutableListOf()
                    set.add(tile)

                    var gotSet = false
                    var firstPair = true
                    for(j in 0 until numOfTiles) {
                        if(i != j) {
                            val checkTile = tiles[j]!!
                                if(set.size == 1) {
                                if(checkTile == set[0]) {
                                    set.add(checkTile)
                                }
                                else if(checkTile.isBelow(tile)) {
                                    set.add(0, checkTile)
                                }
                                else if(checkTile.isAbove(tile)) {
                                    set.add(checkTile)
                                }
                            }

                            if(set.size == 2) {
                                if(set[0] == set[1]) {
                                    if(checkTile == set[0]) {
                                        set.add(checkTile)
                                        gotSet = true
                                    }
                                }
                                else if(checkTile.isBelow(set[0])) {
                                    set.add(0, checkTile)
                                    gotSet = true
                                    break
                                }
                                else if(checkTile.isAbove(set[1])) {
                                    set.add(checkTile)
                                    gotSet = true
                                    break
                                }
                            }

                            /*if(set.size == 3) {
                                if(checkTile == set[0]) {
                                    set.add(checkTile)
                                    gotSet = true
                                } TODO: Make sure Kan is calculated */
                        }
                    }

                    if(gotSet) {
                        set.forEach {
                            it.visited = true
                            it.winning = true
                        }

                        if(recursiveCheck()) {
                            return true
                        }
                        else {
                            set.forEach {
                                it.winning = false
                            }
                        }
                    }
                }
            }
        }

        return false
    }
}