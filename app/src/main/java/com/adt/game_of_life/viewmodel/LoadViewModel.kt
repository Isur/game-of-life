package com.adt.game_of_life.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.adt.game_of_life.R
import com.adt.game_of_life.model.algorithm.IBoardManipulator
import com.adt.game_of_life.model.file.IFileManager
import com.adt.game_of_life.model.snackbar.SnackBarModel

class LoadViewModel(
    private val boardManipulator: IBoardManipulator,
    private val fileManager: IFileManager
) : ViewModel() {

    val files = MutableLiveData<List<String>>()
    val snackBar = MutableLiveData<SnackBarModel>()

    init {
        files.value = fileManager.getFilenames()
    }

    fun load(filename: String) {
        val loaded = fileManager.getContent(filename)
        boardManipulator.setBoard(loaded)
        snackBar.value = SnackBarModel(R.string.save_loaded, R.color.success)
    }

    fun delete(filename: String) {
        fileManager.delete(filename)
        files.value = fileManager.getFilenames()
        snackBar.value = SnackBarModel(R.string.save_deleted, R.color.success)
    }
}