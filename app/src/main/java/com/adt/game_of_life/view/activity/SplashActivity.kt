package com.adt.game_of_life.view.activity

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.adt.game_of_life.R
import com.adt.game_of_life.model.dto.ActivityStartModel
import com.adt.game_of_life.util.startActivity
import kotlinx.android.synthetic.main.activity_splash.*
import pl.droidsonroids.gif.GifDrawable

class SplashActivity : AppCompatActivity() {

    private val visibilityTime = 2000L
    private val splashGifUrl = "gif/splash.gif"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //setupSplashGif()
        setupTransition()
    }

    private fun setupSplashGif() {
        val gifDrawable = GifDrawable(assets, splashGifUrl)
        splashImageView.background = gifDrawable
    }

    private fun setupTransition() {
        Handler().postDelayed({
            val model = ActivityStartModel(MenuActivity::class.java, Bundle())
            startActivity(model)
        }, visibilityTime)
    }
}
