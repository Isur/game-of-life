package com.adt.game_of_life.model.algorithm

interface IConwayAlgorithm {
    val gameBoard: Array<Array<Int?>>
    fun gameStep(): Array<Array<Int?>>
}