package com.adt.game_of_life.algorithm

class ConwayAlgorithm constructor(var gameBoard: Array<Array<Int?>>, private val gameBoardSize: Int) {

    var conwayTransitionGameBoard: Array<Array<Int?>>

    init {
        conwayTransitionGameBoard = gameBoard.copy()
    }

    fun gameStep(): Array<Array<Int?>> {
        for (x in 0..(gameBoardSize - 1)) {
            for (y in 0..(gameBoardSize - 1)) {
                updateCellLife(x, y)
            }
        }

        gameBoard = this.conwayTransitionGameBoard.copy()
        return gameBoard
    }

    private fun updateCellLife(x: Int, y: Int) {
        val cellNeighbours = countCellNeighbours(x, y)
        if (gameBoard[x][y] == 1) {
            if (cellNeighbours != 2 && cellNeighbours != 3) {
                conwayTransitionGameBoard[x][y] = 0
            }
        } else {
            if (cellNeighbours == 3) {
                conwayTransitionGameBoard[x][y] = 1
            }
        }
    }

    private fun countCellNeighbours(x: Int, y: Int): Int {
        var liveNeighbouringCellsCounter: Int = 0

        if (x > 0 && gameBoard[x - 1][y] == 1) {
            liveNeighbouringCellsCounter += 1
        }
        if (x < (gameBoardSize - 1) && gameBoard[x + 1][y] == 1) {
            liveNeighbouringCellsCounter += 1
        }
        if (y > 0 && gameBoard[x][y - 1] == 1) {
            liveNeighbouringCellsCounter += 1
        }
        if (y < (gameBoardSize - 1) && gameBoard[x][y + 1] == 1) {
            liveNeighbouringCellsCounter += 1
        }
        if (x > 0 && y > 0 && gameBoard[x - 1][y - 1] == 1) {
            liveNeighbouringCellsCounter += 1
        }
        if (x > 0 && y < (gameBoardSize - 1) && gameBoard[x - 1][y + 1] == 1) {
            liveNeighbouringCellsCounter += 1
        }
        if (x < (gameBoardSize - 1) && y > 0 && gameBoard[x + 1][y - 1] == 1) {
            liveNeighbouringCellsCounter += 1
        }
        if (x < (gameBoardSize - 1) && y < (gameBoardSize - 1) && gameBoard[x + 1][y + 1] == 1) {
            liveNeighbouringCellsCounter += 1
        }

        return liveNeighbouringCellsCounter
    }

    private fun Array<Array<Int?>>.copy() = Array(gameBoardSize) { get(it).clone() }
}