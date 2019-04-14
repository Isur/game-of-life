package com.adt.game_of_life.view.activity

import abak.tr.com.boxedverticalseekbar.BoxedVertical
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.adt.game_of_life.R
import com.adt.game_of_life.databinding.ActivityGameBinding
import com.adt.game_of_life.enums.InputMode
import com.adt.game_of_life.model.bitmap.BitmapGenerator
import com.adt.game_of_life.model.bitmap.IBitmapGenerator
import com.adt.game_of_life.model.dto.BoardProperties
import com.adt.game_of_life.model.dto.CellProperties
import com.adt.game_of_life.model.dto.ViewProperties
import com.adt.game_of_life.model.input.IScreenToBoardConverter
import com.adt.game_of_life.model.input.ScreenToBoardConverter
import com.adt.game_of_life.util.containsPoint
import com.adt.game_of_life.util.getBinding
import com.adt.game_of_life.view.activity.contract.BackActivity
import com.adt.game_of_life.viewmodel.GameViewModel
import kotlinx.android.synthetic.main.activity_game.*
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.viewModel
import uk.co.senab.photoview.PhotoViewAttacher

class GameActivity : BackActivity() {

    private val viewModel: GameViewModel by viewModel()
    private lateinit var bitmapGenerator: IBitmapGenerator
    private lateinit var photoView: PhotoViewAttacher
    private lateinit var viewProperties: ViewProperties
    private lateinit var boardProperties: BoardProperties
    private lateinit var cellProperties: CellProperties
    private lateinit var coordsConverter: IScreenToBoardConverter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = getBinding<ActivityGameBinding>(this, R.layout.activity_game)
        binding.vm = viewModel

        setupView()
        setListeners()
    }

    private fun setupView() {
        setTitle(R.string.game_activity_title)
        photoView = PhotoViewAttacher(gameImageView)
        speedSeekBar.value = viewModel.speed
    }

    private fun setListeners() {
        gameImageView.post {
            // Invoked when view is rendered so width and height are ready
            initializeModels()
            setObservers()
        }

        speedSeekBar.setOnBoxedPointsChangeListener(object : BoxedVertical.OnValuesChangeListener {
            override fun onStartTrackingTouch(p0: BoxedVertical?) {}
            override fun onStopTrackingTouch(p0: BoxedVertical?) {}
            override fun onPointsChanged(p0: BoxedVertical?, progress: Int) {
                viewModel.changeSpeed(progress)
            }
        })

        speedButton.setOnClickListener {
            speedSeekBar.visibility = if (speedSeekBar.visibility == View.INVISIBLE) View.VISIBLE else View.INVISIBLE
        }

        swapImageView.setOnClickListener {
            viewModel.switchInputMode()
        }
    }

    private fun setObservers() {
        viewModel.board.observe(this, Observer { board ->
            board?.let { updateVisualization(it) }
        })

        viewModel.inputMode.observe(this, Observer { mode ->
            if (mode != null) {
                when (mode) {
                    InputMode.REVIVE -> setReviveMode()
                    InputMode.ZOOM -> setZoomMode()
                }
            }
        })
    }

    private fun setReviveMode() {
        gameImageView.setOnTouchListener { _, event ->
            event?.let {
                if (gameImageView.containsPoint(it.x, it.y)) {
                    val x = it.x.toInt()
                    val y = it.y.toInt()
                    val scale = photoView.scale
                    val rect = photoView.displayRect
                    val toBoard = coordsConverter.convert(x, y, scale, rect)
                    viewModel.reviveCell(toBoard.x, toBoard.y)
                }
            }
            true
        }
        val drawable = ContextCompat.getDrawable(this, R.drawable.ic_create_white_24dp)
        swapImageView.setImageDrawable(drawable)
    }

    private fun setZoomMode() {
        val matrix = photoView.displayMatrix
        photoView = PhotoViewAttacher(gameImageView)
        photoView.displayMatrix = matrix
        val drawable = ContextCompat.getDrawable(this, R.drawable.ic_zoom_out_map_white_24dp)
        swapImageView.setImageDrawable(drawable)
    }

    private fun initializeModels() {
        viewProperties = ViewProperties(gameImageView.width, gameImageView.height)
        boardProperties = viewModel.boardProperties
        cellProperties = CellProperties(viewProperties, boardProperties)
        coordsConverter = ScreenToBoardConverter(cellProperties)
        bitmapGenerator = BitmapGenerator(get(), cellProperties, viewProperties)
    }

    private fun updateVisualization(board: Array<Array<Int?>>) {
        val matrix = photoView.displayMatrix

        val bitmap = bitmapGenerator.generate(board)
        gameImageView.setImageBitmap(bitmap)

        photoView.update()
        photoView.displayMatrix = matrix
    }

    override fun onBackPressed() {
        viewModel.destroy()
        super.onBackPressed()
    }
}
