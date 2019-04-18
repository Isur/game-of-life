package com.adt.game_of_life.viewmodel

import android.arch.lifecycle.ViewModel
import com.adt.game_of_life.model.file.IFileManager

class LoadViewModel(private val fileManager: IFileManager) : ViewModel() {

    val files: List<String>
        get() = fileManager.getFilenames()
}