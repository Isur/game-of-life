package com.adt.game_of_life

import android.app.Application
import com.adt.game_of_life.viewmodel.GameViewModel
import com.adt.game_of_life.viewmodel.LoadViewModel
import com.adt.game_of_life.viewmodel.MenuViewModel
import com.adt.game_of_life.viewmodel.SettingsViewModel
import org.koin.android.ext.android.startKoin
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import timber.log.Timber

class GameOfLifeApplication : Application() {

    private val appModule = getApplicationModule()

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
        setupTimber()
    }

    private fun getApplicationModule(): Module {
        return module {
            viewModel { MenuViewModel() }
            viewModel { GameViewModel() }
            viewModel { LoadViewModel() }
            viewModel { SettingsViewModel() }
        }
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}