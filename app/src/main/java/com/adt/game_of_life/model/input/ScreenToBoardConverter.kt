package com.adt.game_of_life.model.input

import com.adt.game_of_life.model.dto.CellProperties
import com.adt.game_of_life.model.dto.Coords

class ScreenToBoardConverter(private val cell: CellProperties) : IScreenToBoardConverter {

    override fun convert(coords: Coords): Coords {
        return convert(coords.x, coords.y)
    }

    override fun convert(x: Int, y: Int): Coords {
        val boardX = (x / cell.width).toInt()
        val boardY = (y / cell.height).toInt()
        return Coords(boardX, boardY)
    }
}