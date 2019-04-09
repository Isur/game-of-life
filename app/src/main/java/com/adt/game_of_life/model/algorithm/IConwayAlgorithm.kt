package com.adt.game_of_life.model.algorithm

import com.adt.game_of_life.model.dto.BoardProperties

interface IConwayAlgorithm {
    val gameBoard: Array<Array<Int?>>
    val boardProperties: BoardProperties
    fun gameStep(): Array<Array<Int?>>
    fun reviveCell(x: Int, y: Int): Boolean
}