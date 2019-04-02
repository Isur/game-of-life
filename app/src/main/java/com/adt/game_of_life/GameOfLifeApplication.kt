package com.adt.game_of_life

import android.app.Application
import com.adt.game_of_life.model.algorithm.ConwayAlgorithm
import com.adt.game_of_life.model.algorithm.IConwayAlgorithm
import com.adt.game_of_life.model.pref.IColorsPref
import com.adt.game_of_life.model.pref.IGameRulesPref
import com.adt.game_of_life.model.pref.SharedPrefAccess
import com.adt.game_of_life.model.pref.serializer.GameRulesSerializer
import com.adt.game_of_life.model.pref.serializer.IGameRulesSerializer
import com.adt.game_of_life.model.setting.GameColors
import com.adt.game_of_life.model.setting.GameRules
import com.adt.game_of_life.viewmodel.GameViewModel
import com.adt.game_of_life.viewmodel.LoadViewModel
import com.adt.game_of_life.viewmodel.MenuViewModel
import com.adt.game_of_life.viewmodel.SettingsViewModel
import org.koin.android.ext.android.startKoin
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import timber.log.Timber
import kotlin.random.Random

class GameOfLifeApplication : Application() {

    private val appModule = getApplicationModule()

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
        setupTimber()
    }

    private fun getApplicationModule(): Module {
        return module {
            single<IGameRulesSerializer> { GameRulesSerializer() }
            single {
                SharedPrefAccess(this@GameOfLifeApplication, get())
            } bind IGameRulesPref::class bind IColorsPref::class

            single { GameRules(get()) }
            single { GameColors(get()) }

            single { Array(50) { Array<Int?>(50) { Random.nextInt(0, 2) } } }
            single<IConwayAlgorithm> { ConwayAlgorithm(get(), get()) }

            viewModel { MenuViewModel() }
            viewModel { GameViewModel(get()) }
            viewModel { LoadViewModel() }
            viewModel { SettingsViewModel(get(), get()) }
        }
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}