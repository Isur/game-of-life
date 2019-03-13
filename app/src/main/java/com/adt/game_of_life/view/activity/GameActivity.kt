package com.adt.game_of_life.view.activity

import android.os.Bundle
import com.adt.game_of_life.R
import com.adt.game_of_life.databinding.ActivityGameBinding
import com.adt.game_of_life.util.getBinding
import com.adt.game_of_life.view.activity.contract.BackActivity
import com.adt.game_of_life.viewmodel.GameViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class GameActivity : BackActivity() {

    private val viewModel: GameViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = getBinding<ActivityGameBinding>(this, R.layout.activity_game)
        binding.vm = viewModel

        setTitle(R.string.game_activity_title)
    }
}
