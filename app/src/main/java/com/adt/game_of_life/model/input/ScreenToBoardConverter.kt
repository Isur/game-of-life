package com.adt.game_of_life.model.input

import com.adt.game_of_life.model.dto.CellProperties
import com.adt.game_of_life.model.dto.Coords

class ScreenToBoardConverter(private val cell: CellProperties) : IScreenToBoardConverter {

    override fun convert(coords: Coords): Coords {
        return convert(coords.x, coords.y)
    }

    override fun convert(x: Int, y: Int): Coords {
        val boardX = Math.round(x / cell.width)
        val boardY = Math.round(y / cell.height)
        return Coords(boardX, boardY)
    }
}