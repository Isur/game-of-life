package com.adt.game_of_life.model.bitmap

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import com.adt.game_of_life.model.setting.GameColors
import com.adt.game_of_life.model.setting.ViewProperties

/**
 * Created by dgrajewski on 27.03.2019.
 */
class BitmapGenerator(
    private val gameColors: GameColors,
    private val viewProperties: ViewProperties
) : IBitmapGenerator {

    private var cellWidth = 0f
    private var cellHeight = 0f

    private val bitmap = Bitmap.createBitmap(viewProperties.width, viewProperties.height, Bitmap.Config.RGB_565)
    private val canvas = Canvas(bitmap)
    private val rect = RectF()
    private val paint = Paint()

    init {
        paint.color = gameColors.aliveColor
    }

    override fun generate(board: Array<Array<Int?>>): Bitmap {
        cellHeight = viewProperties.height / board.size.toFloat()
        cellWidth = viewProperties.width / board[0].size.toFloat()

        canvas.drawColor(gameColors.deadColor)

        for (y in board.indices) {
            for (x in board[y].indices) {
                if (board[y][x] == 1) {
                    rect.set(
                        (x * cellWidth), (y * cellHeight),
                        (x * cellWidth + cellWidth) - 1, (y * cellHeight + cellHeight) - 1
                    )
                    canvas.drawRect(rect, paint)
                }
            }
        }

        return bitmap
    }
}