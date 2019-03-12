package com.adt.game_of_life.view.activity

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.adt.game_of_life.R
import com.adt.game_of_life.util.startActivity
import pl.grajek.actions.model.dto.ActivityStartModel

class SplashActivity : AppCompatActivity() {

    private val visibilityTime = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val model = ActivityStartModel(MenuActivity::class.java, Bundle())
            startActivity(model)
        }, visibilityTime)
    }
}
