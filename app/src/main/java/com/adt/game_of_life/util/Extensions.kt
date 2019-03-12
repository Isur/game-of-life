package com.adt.game_of_life.util

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.app.AppCompatActivity
import pl.grajek.actions.model.dto.ActivityStartModel

fun AppCompatActivity.startActivity(model: ActivityStartModel) {
    val intent = Intent(this, model.type)
    intent.putExtras(model.bundle)
    startActivity(intent)
}

fun <T : ViewDataBinding> AppCompatActivity.getBinding(activity: Activity, layout: Int): T {
    return DataBindingUtil.setContentView(activity, layout)
}