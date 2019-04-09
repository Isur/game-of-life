package com.adt.game_of_life.model.input

import com.adt.game_of_life.model.dto.Coords

interface IScreenToBoardConverter {
    fun convert(coords: Coords): Coords
    fun convert(x: Int, y: Int): Coords
}