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

        setupView()
        setListeners()
    }

    private fun setListeners() {
        aliveColorButton.setOnClickListener {
            aliveColorButton.showColorPicker(this, viewModel.gameColors.aliveColor) {
                viewModel.gameColors.aliveColor = it
            }
        }

        deadColorButton.setOnClickListener {
            deadColorButton.showColorPicker(this, viewModel.gameColors.deadColor) {
                viewModel.gameColors.deadColor = it
            }
        }

        setListeners(aliveNumbers as ConstraintLayout) { isChecked, i ->
            Timber.e("born : $isChecked : $i")
            if (isChecked)
                viewModel.gameRules.addNeighbourToBorn(i)
            else
                viewModel.gameRules.removeNeighbourToBorn(i)
        }

        setListeners(deadNumbers as ConstraintLayout) { isChecked, i ->
            Timber.e("dead : $isChecked : $i")
            if (isChecked)
                viewModel.gameRules.addNeighbourToDie(i)
            else
                viewModel.gameRules.removeNeighbourToDie(i)
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

    private fun setupView() {
        aliveColorButton.setBackgroundColor(viewModel.gameColors.aliveColor)
        deadColorButton.setBackgroundColor(viewModel.gameColors.deadColor)
        check(aliveNumbers as ConstraintLayout, viewModel.gameRules.neighboursToBorn)
        check(deadNumbers as ConstraintLayout, viewModel.gameRules.neighboursToDie)
    }

    private fun check(numberPicker: ConstraintLayout, checked: List<Int>) {
        for (i in 0..8) {
            val child = numberPicker.getChildAt(i)
            if (child is CheckBox && checked.contains(i)) {
                child.isChecked = true
            }
        }
    }
}
