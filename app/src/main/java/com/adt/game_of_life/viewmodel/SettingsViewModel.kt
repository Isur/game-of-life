package com.adt.game_of_life.viewmodel

import android.arch.lifecycle.ViewModel
import com.adt.game_of_life.model.setting.GameColors
import com.adt.game_of_life.model.setting.GameRules

class SettingsViewModel(
    val gameRules: GameRules,
    val gameColors: GameColors
) : ViewModel()