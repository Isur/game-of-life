package com.adt.game_of_life.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.adt.game_of_life.model.algorithm.IConwayAlgorithm
import com.adt.game_of_life.model.simulation.ILooper
import com.adt.game_of_life.model.simulation.SpeedModel

class GameViewModel(
    private val conwayAlgorithm: IConwayAlgorithm,
    private val looper: ILooper,
    private val speedModel: SpeedModel
) : ViewModel() {

    val board = MutableLiveData<Array<Array<Int?>>>()
    val speed: Int
        get() = speedModel.percentageSpeed

    init {
        board.value = conwayAlgorithm.gameBoard
    }

    fun step() {
        board.value = conwayAlgorithm.gameStep()
    }

    fun changeSpeed(percentage: Int) {
        looper.stop()
        speedModel.percentageSpeed = percentage
        if (speedModel.canRun)
            looper.start({ step() }, speedModel.interval)
    }

    fun destroy() {
        looper.stop()
    }
}