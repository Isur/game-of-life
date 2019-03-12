package com.adt.game_of_life.viewmodel

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import com.adt.game_of_life.view.activity.GameActivity
import com.adt.game_of_life.view.activity.LoadActivity
import com.adt.game_of_life.view.activity.SettingsActivity
import pl.grajek.actions.model.SingleLiveEvent
import pl.grajek.actions.model.dto.ActivityStartModel

class MenuViewModel : ViewModel() {

    val activityToStart = SingleLiveEvent<ActivityStartModel>()

    private fun startActivity(type: Class<*>) {
        activityToStart.value = ActivityStartModel(type, Bundle())
    }

    fun startGameActivity() {
        startActivity(GameActivity::class.java)
    }

    fun startLoadActivity() {
        startActivity(LoadActivity::class.java)
    }

    fun startSettingsActivity() {
        startActivity(SettingsActivity::class.java)
    }
}