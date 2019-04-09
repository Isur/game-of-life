package com.adt.game_of_life.model.bitmap

import android.graphics.Bitmap
import com.adt.game_of_life.model.dto.CellProperties

/**
 * Created by dgrajewski on 27.03.2019.
 */
interface IBitmapGenerator {
    fun generate(board: Array<Array<Int?>>, cell: CellProperties): Bitmap
}