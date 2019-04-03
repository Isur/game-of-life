package com.adt.game_of_life.model.simulation

class SpeedModel(private val baseInterval: Long) {

    var percentageSpeed = 0
    val interval: Long
        get() = if (percentageSpeed != 0) baseInterval / percentageSpeed else 0
    val canRun: Boolean
        get() = percentageSpeed > 0
}