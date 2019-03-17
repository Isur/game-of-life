package com.adt.game_of_life.algorithm

//TODO: Klasa do usuniecia po dodaniu klasy algorytmu w poprawne miejsce aplikacji
//Wystaczy wywolac makeGameStep() - otrzymujemy numer kroku i wyglad planszy w consoli

class TemporaryAlgorithmInitializer {
    private val conwayGameSize: Int = 20
    private var conwayStep: Int = 0
    private var conwayGameBoard = Array(conwayGameSize) { arrayOfNulls<Int>(conwayGameSize) }
    private val gameAlgorithm: ConwayAlgorithm

    init {
        initializeConwayGameBoard()
        gameAlgorithm = ConwayAlgorithm(this.conwayGameBoard, this.conwayGameSize)
    }

    fun makeGameStep() {
        this.conwayGameBoard = gameAlgorithm.gameStep()
        printConwayBoard()
    }

    private fun printConwayBoard() {
        println("Step number: $conwayStep")
        for (x in 0..(conwayGameSize - 1)) {
            for (y in 0..(conwayGameSize - 1)) {
                print(conwayGameBoard[x][y].toString() + " ")
            }
            print("\n")
        }
        conwayStep += 1
    }

    private fun initializeConwayGameBoard() {
        for (x in 0..(conwayGameSize - 1)) {
            for (y in 0..(conwayGameSize - 1)) {
                conwayGameBoard[x][y] = 0
            }
        }

        //Pentadecathlon for 20x20
        conwayGameBoard[9][5] = 1
        conwayGameBoard[9][6] = 1
        conwayGameBoard[8][7] = 1
        conwayGameBoard[10][7] = 1
        conwayGameBoard[9][8] = 1
        conwayGameBoard[9][9] = 1
        conwayGameBoard[9][10] = 1
        conwayGameBoard[9][11] = 1
        conwayGameBoard[8][12] = 1
        conwayGameBoard[10][12] = 1
        conwayGameBoard[9][13] = 1
        conwayGameBoard[9][14] = 1
    }
}