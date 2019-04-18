package com.adt.game_of_life.model.dialog

import android.content.Context

interface IDialogManager {
    fun showInputDialog(
        context: Context,
        onConfirmButton: (String) -> Unit,
        onNegativeButton: () -> Unit
    )
}