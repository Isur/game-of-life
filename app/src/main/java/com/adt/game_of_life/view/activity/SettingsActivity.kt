package com.adt.game_of_life.view.activity

import android.os.Bundle
import com.adt.game_of_life.R
import com.adt.game_of_life.databinding.ActivitySettingsBinding
import com.adt.game_of_life.util.getBinding
import com.adt.game_of_life.view.activity.contract.BackActivity
import com.adt.game_of_life.viewmodel.SettingsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SettingsActivity : BackActivity() {

    private val viewModel: SettingsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = getBinding<ActivitySettingsBinding>(this, R.layout.activity_settings)
        binding.vm = viewModel

        setTitle(R.string.settings_activity_title)
    }
}
