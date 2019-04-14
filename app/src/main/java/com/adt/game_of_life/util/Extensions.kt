package com.adt.game_of_life.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.adt.game_of_life.R
import com.adt.game_of_life.model.dto.ActivityStartModel
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import timber.log.Timber

fun AppCompatActivity.startActivity(model: ActivityStartModel) {
    val intent = Intent(this, model.type)
    intent.putExtras(model.bundle)
    startActivity(intent)
}

fun <T : ViewDataBinding> AppCompatActivity.getBinding(activity: Activity, layout: Int): T {
    return DataBindingUtil.setContentView(activity, layout)
}

fun Button.showColorPicker(context: Context, initialColor: Int, callback: (Int) -> Unit) {
    val header = context.getString(R.string.dialog_choose_color_header)
    val ok = context.getString(R.string.dialog_ok_button)
    val cancel = context.getString(R.string.dialog_cancel_button)

    ColorPickerDialogBuilder
        .with(context, R.style.defaultDialogStyle)
        .setTitle(header)
        .initialColor(initialColor)
        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
        .density(12)
        .setOnColorSelectedListener { selectedColor ->
            Timber.e(selectedColor.toString())
        }
        .setPositiveButton(
            ok
        ) { _, selectedColor, _ ->
            this.setBackgroundColor(selectedColor)
            callback(selectedColor)
        }
        .setNegativeButton(cancel) { _, _ -> }
        .build()
        .show()
}

fun SharedPreferences.edit(callback: (SharedPreferences.Editor) -> Unit) {
    val editor = this.edit()
    callback(editor)
    editor.commit()
}

fun View.containsPoint(x: Float, y: Float): Boolean {
    return x > 0 && x < width && y > 0 && y < height
}