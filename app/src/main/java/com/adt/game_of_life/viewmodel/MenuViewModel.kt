package com.adt.game_of_life.viewmodel

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import com.adt.game_of_life.view.activity.GameActivity
import pl.grajek.actions.model.SingleLiveEvent
import pl.grajek.actions.model.dto.ActivityStartModel

class MenuViewModel : ViewModel() {

    val activityToStart = SingleLiveEvent<ActivityStartModel>()

    fun startGameActivity() {
        activityToStart.value = ActivityStartModel(GameActivity::class.java, Bundle())
    }
}