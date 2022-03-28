package com.example.mahjongcalculator

enum class Suit {
    Sou,
    Man,
    Pin,
    Wind, //1: North, 2: East, 3: South, 4: West
    Dragon //1: White, 2: Green, 3: Red
}

class Tile(inputSuit: Suit, inputValue: Int) {
    var suit = inputSuit
    var value = inputValue
}