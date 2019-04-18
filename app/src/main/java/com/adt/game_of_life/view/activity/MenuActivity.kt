package com.adt.game_of_life.view.activity

import android.Manifest
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.adt.game_of_life.R
import com.adt.game_of_life.databinding.ActivityMenuBinding
import com.adt.game_of_life.util.getBinding
import com.adt.game_of_life.util.startActivity
import com.adt.game_of_life.view.activity.contract.IPermissionActivity
import com.adt.game_of_life.viewmodel.MenuViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MenuActivity : AppCompatActivity(), IPermissionActivity {

    private val viewModel: MenuViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = getBinding<ActivityMenuBinding>(R.layout.activity_menu)
        binding.vm = viewModel

        setTitle(R.string.menu_activity_title)
        setObservables()
        checkPermission(this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            onDenied = { onBackPressed() })
    }

    private fun setObservables() {
        viewModel.activityToStart.observe(this, Observer { activity ->
            activity?.let { startActivity(it) }
        })
    }

    override fun onBackPressed() {
        finishAffinity()
        super.onBackPressed()
    }
}
