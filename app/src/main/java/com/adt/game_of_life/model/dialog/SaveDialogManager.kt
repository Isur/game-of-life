package com.adt.game_of_life.model.dialog

import android.content.Context
import com.adt.game_of_life.R
import com.adt.game_of_life.model.file.IFileManager
import com.yarolegovich.lovelydialog.LovelyTextInputDialog

class SaveDialogManager(private val fileManager: IFileManager) : IDialogManager {

    override fun showInputDialog(
        context: Context,
        onConfirmButton: (String) -> Unit,
        onNegativeButton: () -> Unit
    ) {
        LovelyTextInputDialog(context)
            .setTopColorRes(R.color.colorAccent)
            .setTitle(R.string.dialog_save_title)
            .setInputFilter(
                R.string.filename_taken
            ) { text -> !fileManager.getFilenames().contains(text) }
            .setConfirmButton(
                R.string.dialog_ok_button
            ) { text ->
                onConfirmButton(text)
            }
            .setNegativeButton(
                R.string.dialog_cancel_button
            ) { onNegativeButton() }
            .show()
    }
}