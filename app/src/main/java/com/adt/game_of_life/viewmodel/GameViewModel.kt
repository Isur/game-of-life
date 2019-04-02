package com.adt.game_of_life.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.adt.game_of_life.model.algorithm.IConwayAlgorithm

class GameViewModel(private val conwayAlgorithm: IConwayAlgorithm) : ViewModel() {

    val board = MutableLiveData<Array<Array<Int?>>>()

    init {
        board.value = conwayAlgorithm.gameBoard
    }

    fun step() {
        board.value = conwayAlgorithm.gameStep()
    }
}