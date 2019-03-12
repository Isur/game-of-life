package com.adt.game_of_life

import android.app.Application
import com.adt.game_of_life.viewmodel.MenuViewModel
import org.koin.android.ext.android.startKoin
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

class GameOfLifeApplication : Application() {

    private val appModule = getApplicationModule()

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }

    private fun getApplicationModule(): Module {
        return module {
            viewModel { MenuViewModel() }
        }
    }
}