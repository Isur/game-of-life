package com.adt.game_of_life.model.dialog

import android.content.Context
import com.adt.game_of_life.R
import com.adt.game_of_life.model.file.IFileManager
import com.yarolegovich.lovelydialog.LovelyStandardDialog
import com.yarolegovich.lovelydialog.LovelyTextInputDialog

class DialogManager(private val fileManager: IFileManager) : IDialogManager {

    private val style = R.style.defaultDialogStyle

    override fun showSaveBoardDialog(
        context: Context,
        onConfirmButton: (String) -> Unit,
        onNegativeButton: () -> Unit
    ) {
        LovelyTextInputDialog(context, style)
            .setTopColorRes(R.color.colorAccent)
            .setTitle(R.string.dialog_save_message)
            .setInputFilter(R.string.filename_taken_or_empty) { text ->
                text.isNotBlank() && !fileManager.getFilenames().contains(text)
            }
            .setConfirmButton(R.string.dialog_ok_button) { text ->
                onConfirmButton(text)
            }
            .setNegativeButton(R.string.dialog_cancel_button) {
                onNegativeButton()
            }
            .setCancelable(false)
            .show()
    }

    override fun showDeleteSaveDialog(
        context: Context,
        onConfirmButton: () -> Unit,
        onNegativeButton: () -> Unit
    ) {
        LovelyStandardDialog(context, style)
            .setTopColorRes(R.color.colorAccent)
            .setTitle(R.string.dialog_delete_message)
            .setPositiveButton(R.string.dialog_ok_button) {
                onConfirmButton()
            }
            .setNegativeButton(R.string.dialog_cancel_button) {
                onNegativeButton()
            }
            .setCancelable(false)
            .show()
    }

    override fun showLoadDialog(
        context: Context,
        onConfirmButton: () -> Unit,
        onNegativeButton: () -> Unit
    ) {
        LovelyStandardDialog(context, style)
            .setTopColorRes(R.color.colorAccent)
            .setTitle(R.string.dialog_load_message)
            .setPositiveButton(R.string.dialog_ok_button) {
                onConfirmButton()
            }
            .setNegativeButton(R.string.dialog_cancel_button) {
                onNegativeButton()
            }
            .setCancelable(false)
            .show()
    }
}