package com.adt.game_of_life.view.activity

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.adt.game_of_life.R
import com.adt.game_of_life.databinding.ActivityGameBinding
import com.adt.game_of_life.model.bitmap.BitmapGenerator
import com.adt.game_of_life.model.bitmap.IBitmapGenerator
import com.adt.game_of_life.model.setting.ViewProperties
import com.adt.game_of_life.util.getBinding
import com.adt.game_of_life.view.activity.contract.BackActivity
import com.adt.game_of_life.viewmodel.GameViewModel
import kotlinx.android.synthetic.main.activity_game.*
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.viewModel

class GameActivity : BackActivity() {

    private val viewModel: GameViewModel by viewModel()
    private lateinit var bitmapGenerator: IBitmapGenerator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = getBinding<ActivityGameBinding>(this, R.layout.activity_game)
        binding.vm = viewModel

        setTitle(R.string.game_activity_title)

        setListeners()
    }

    private fun setListeners() {
        gameImageView.post {
            // Invoked when view is rendered so width and height are ready
            val viewProperties = ViewProperties(gameImageView.width, gameImageView.height)
            bitmapGenerator = BitmapGenerator(get(), viewProperties)
            setObservers()
        }
    }

    private fun setObservers() {
        viewModel.board.observe(this, Observer {
            if (it != null) {
                val bitmap = bitmapGenerator.generate(it)
                gameImageView.setImageBitmap(bitmap)
            }
        })
    }
}
