package com.example.mahjongcalculator

import android.util.Log

class Hand {
    var tiles = mutableListOf<Tile>()
    val maxNumOfTiles = 14

    fun addTile(suit: Suit, value: Int, dora: Boolean = false): Boolean {
        if(maxNumOfTiles == tiles.size) {
            return false
        }

        var found = false
        for(i in 0 until tiles.size) {
            if(tiles[i].suit == suit) {
                if(tiles[i].value >= value) {
                    found = true
                }
            }
            else if(i != 0) {
                if (tiles[i - 1].suit == suit) {
                    found = true
                }
            }

            if(found) {
                tiles.add(i, Tile(suit, value, dora))
                return true
            }
        }

        tiles.add(Tile(suit, value, dora))
        Log.d(null, tiles.size.toString())
        return true
    }

    fun deleteTile(index: Int) : Boolean {
        if(index >= tiles.size || index >= maxNumOfTiles) {
            return false
        }

        tiles.removeAt(index)
        return true
    }

    fun checkIfValid(): Boolean { //Using https://stackoverflow.com/questions/4937771/mahjong-winning-hand-algorithm as a reference
        if(tiles.size != maxNumOfTiles) {
            return false
        }

        tiles.forEach {
            it.winning = false
            it.visited = false
        }

        return recursiveCheck()
    }

    private fun recursiveCheck(foundPair: Boolean = false): Boolean {
        if(tiles.all { it.winning } ) {
            return true
        }

        var index = 0
        var found = false
        var pair = foundPair
        do {
            for (i in 0 until tiles.size) {
                if (!tiles[i].visited) {
                    index = i
                    break
                }
            }

            if(index >= 13) {
                return false
            }

            if(!pair && tiles[index] == tiles[index + 1]) {
                tiles[index].visited = true
                tiles[index + 1].visited = true

                tiles[index].winning = true
                tiles[index + 1].winning = true

                pair = true
                found = true
            }
            else {
                if(index >= 12) {
                    return false
                }
                else if (Tile.isMeld(tiles[index], tiles[index + 1], tiles[index + 2])) {
                    tiles[index].visited = true
                    tiles[index + 1].visited = true
                    tiles[index + 2].visited = true

                    tiles[index].winning = true
                    tiles[index + 1].winning = true
                    tiles[index + 2].winning = true
                    found = true
                }
            }

            tiles[index].visited = true
        } while(!found)

        var str = ""

        tiles.forEach {
            str += it.winning.toString() + " "
        }

        Log.d(null, "$index $str")

        if(recursiveCheck(pair)) {
            return true
        }
        else {
            if(pair) {
                tiles[index].visited = false
                tiles[index + 1].visited = false

                tiles[index].winning = false
                tiles[index + 1].winning = false
            }
            else {
                tiles[index].visited = true
                tiles[index + 1].visited = true
                tiles[index + 2].visited = true

                tiles[index].winning = false
                tiles[index + 1].winning = false
                tiles[index + 2].winning = false
            }
        }


        /*for(j in 0 until numOfTiles) {
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
        }*/

        return false
    }
}