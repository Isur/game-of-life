package com.adt.game_of_life.view.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.adt.game_of_life.R
import com.adt.game_of_life.databinding.ActivityLoadBinding
import com.adt.game_of_life.util.getBinding
import com.adt.game_of_life.view.activity.contract.BackActivity
import com.adt.game_of_life.view.adapter.LoadAdapter
import com.adt.game_of_life.viewmodel.LoadViewModel
import kotlinx.android.synthetic.main.activity_load.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class LoadActivity : BackActivity() {

    private val viewModel: LoadViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = getBinding<ActivityLoadBinding>(this, R.layout.activity_load)
        binding.vm = viewModel

        setTitle(R.string.load_activity_title)

        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        val adapter = LoadAdapter(viewModel.files) { Timber.e(it) }
        loadRecyclerView.layoutManager = LinearLayoutManager(this)
        loadRecyclerView.adapter = adapter
    }
}
