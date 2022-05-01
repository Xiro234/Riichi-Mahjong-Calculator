package com.example.mahjongcalculator

import org.mahjong4j.GeneralSituation
import org.mahjong4j.PersonalSituation
import org.mahjong4j.Player
import org.mahjong4j.Score
import org.mahjong4j.hands.Hands
import org.mahjong4j.yaku.normals.NormalYaku
import org.mahjong4j.yaku.yakuman.Yakuman

class HandContainer {
    var tiles = mutableListOf<MTile>()
    var melds: MutableList<Meld> = mutableListOf()
    val maxNumOfTiles = 18
    private lateinit var player: Player
    var last = MTile(Suit.Man, 1)

    fun addTile(suit: Suit, value: Int): Boolean {
        if (14 == getSize()) {
            return false
        }

        tiles.add(MTile(suit, value))
        return true
    }

    fun addMeld(currentMeld: List<MTile>, closed: Boolean = false): Boolean {
        println(checkIfMeldFits(currentMeld))

        return if(checkIfMeldFits(currentMeld)) {
            melds.add(Meld(currentMeld, closed))
            true
        } else {
            false
        }
    }

    fun deleteTile(index: Int): Boolean {
        if (index >= getSize()) {
            return false
        }

        if(index < tiles.size) {
            tiles.removeAt(index)
        }
        else {
            val newIndex = index - tiles.size
            var count = 0
            for(i in 0 until melds.size) {
                for(j in 0 until melds[i].tiles.size) {
                    count++
                }

                if(count >= newIndex) {
                    melds.removeAt(i)
                    break
                }
            }
        }
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
        val mentsuList = melds.map { it.toMentsu() }

        val hand = if(melds.isEmpty()) {
            Hands(array, last.toTileEnum())
        }
        else {
            Hands(array, last.toTileEnum(), mentsuList)
        }

        return hand.canWin
    }

    fun getPoints(): Score {
        return if(getValid()) {
            player.score
        } else {
            Score.SCORE0
        }
    }

    fun getYakuman(): List<Yakuman> {
        return player.yakumanList
    }

    fun getHan(): Int {
        return player.han
    }

    fun getFu(): Int {
        return player.fu
    }

    fun getYaku(): List<NormalYaku> {
        return player.normalYakuList
    }

    fun getSize(): Int {
        return tiles.size + melds.size * 3
    }

    private fun checkIfMeldFits(currentMeld: List<MTile>): Boolean {
        return if(currentMeld.size == 4) {
            currentMeld[0] == currentMeld[1] && currentMeld[1] == currentMeld[2] && currentMeld[2] == currentMeld[3] && getSize() <= 11
        } else if(currentMeld.size == 3) {
            if(currentMeld.all { it.suit != Suit.Dragon && it.suit != Suit.Wind }) {
                (currentMeld[0].isBelow(currentMeld[1]) && currentMeld[1].isBelow(currentMeld[2]) && getSize() <= 11) || (currentMeld[0] == currentMeld[1] && currentMeld[1] == currentMeld[2] && getSize() <= 11)
            } else {
                currentMeld[0] == currentMeld[1] && currentMeld[1] == currentMeld[2] && getSize() <= 11
            }
        }
        else {
            false
        }
    }

    fun calculate(personalSituation: PersonalSituation, generalSituation: GeneralSituation): Boolean {
        if(getValid()) {
            val array = getAsArray()
            val mentsuList = melds.map { it.toMentsu() }

            val hand = if(melds.isEmpty()) {
                Hands(array, last.toTileEnum())
            }
            else {
                Hands(array, last.toTileEnum(), mentsuList)
            }

            player = Player(hand, generalSituation, personalSituation)

            player.calculate()
            return true
        }
        return false
    }
}
