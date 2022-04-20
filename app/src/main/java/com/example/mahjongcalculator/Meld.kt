package com.example.mahjongcalculator

import java.lang.IllegalArgumentException

class Meld(list: List<MTile>, close: Boolean = false) {
    var tiles: List<MTile> = list
    val closed: Boolean = close

    init {
        if(tiles.size != 3 || tiles.size != 4) {
            throw IllegalArgumentException()
        }
    }
}