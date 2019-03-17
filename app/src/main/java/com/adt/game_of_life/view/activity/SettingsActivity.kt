package com.adt.game_of_life.view.activity

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.widget.CheckBox
import com.adt.game_of_life.R
import com.adt.game_of_life.databinding.ActivitySettingsBinding
import com.adt.game_of_life.util.getBinding
import com.adt.game_of_life.util.showColorPicker
import com.adt.game_of_life.view.activity.contract.BackActivity
import com.adt.game_of_life.viewmodel.SettingsViewModel
import kotlinx.android.synthetic.main.activity_settings.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber


class SettingsActivity : BackActivity() {

    private val viewModel: SettingsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = getBinding<ActivitySettingsBinding>(this, R.layout.activity_settings)
        binding.vm = viewModel

        setTitle(R.string.settings_activity_title)

        setListeners()
    }

    private fun setListeners() {
        aliveColorButton.setOnClickListener {
            aliveColorButton.showColorPicker(this, -3830785) {

            }
        }

        deadColorButton.setOnClickListener {
            deadColorButton.showColorPicker(this, -3830785) {

            }
        }

        setListeners(aliveNumbers as ConstraintLayout) { isChecked, i ->
            Timber.e("$i : $isChecked")
        }

        setListeners(deadNumbers as ConstraintLayout) { isChecked, i ->
            Timber.e("$i : $isChecked")
        }
    }

    private fun setListeners(numberPicker: ConstraintLayout, listener: (Boolean, Int) -> Unit) {
        for (i in 0..8) {
            val child = numberPicker.getChildAt(i)
            if (child is CheckBox) {
                child.setOnCheckedChangeListener { _, isChecked ->
                    listener(isChecked, i)
                }
            }
        }
    }
}
