package com.example.mahjongcalculator

import org.mahjong4j.hands.Kantsu
import org.mahjong4j.hands.Kotsu
import org.mahjong4j.hands.Mentsu
import org.mahjong4j.hands.Shuntsu

class Meld(list: List<MTile>, close: Boolean = false) {
    var tiles: List<MTile> = list
    val closed: Boolean = close

    fun toMentsu(): Mentsu {
        return if(tiles.size == 4) {
            Kantsu(!closed, tiles[0].toTileEnum())
        } else {
            if(tiles[0] == tiles[1] && tiles[1] == tiles[2]) {
                Kotsu(!closed, tiles[0].toTileEnum())
            } else {
                Shuntsu(!closed, tiles[0].toTileEnum(), tiles[1].toTileEnum(), tiles[0].toTileEnum())
            }
        }
    }
}