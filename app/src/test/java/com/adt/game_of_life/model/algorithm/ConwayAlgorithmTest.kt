package com.adt.game_of_life.model.algorithm

import com.adt.game_of_life.MockitoTest
import com.adt.game_of_life.model.pref.IGameRulesPref
import com.adt.game_of_life.model.setting.GameRules
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import kotlin.test.assertEquals

class ConwayAlgorithmTest : MockitoTest() {

    private lateinit var conwayAlgorithm: ConwayAlgorithm
    @Mock
    private lateinit var mockGameRulesPref: IGameRulesPref
    private val board = arrayOf<Array<Int?>>(
        arrayOf(1, 1, 1),
        arrayOf(1, 1, 1),
        arrayOf(1, 1, 1)
    )

    private fun setupConwayAlgorithm(
        neighboursToBorn: MutableList<Int>,
        neighboursToDie: MutableList<Int>
    ) {
        `when`(mockGameRulesPref.getNeighboursToBorn())
            .thenReturn(neighboursToBorn)
        `when`(mockGameRulesPref.getNeighboursToDie())
            .thenReturn(neighboursToDie)
        val gameRules = GameRules(mockGameRulesPref)
        conwayAlgorithm = ConwayAlgorithm(gameRules, board)
    }

    @Test
    fun `Gives correct board properties`() {
        setupConwayAlgorithm(mutableListOf(), mutableListOf())

        val properties = conwayAlgorithm.boardProperties
        val expectedHeight = board.size
        val expectedWidth = board[0].size

        assertEquals(properties.height, expectedHeight)
        assertEquals(properties.width, expectedWidth)
    }

    @Test
    fun `Gives correct result after step with rules 3 | 0,1,4,5,6,7,8`() {
        val neighboursToBorn = mutableListOf(3)
        val neighboursToDie = mutableListOf(0, 1, 4, 5, 6, 7, 8)
        setupConwayAlgorithm(neighboursToBorn, neighboursToDie)

        val actual = conwayAlgorithm.gameStep().flatten()
        val expected = arrayOf<Array<Int?>>(
            arrayOf(1, 0, 1),
            arrayOf(0, 0, 0),
            arrayOf(1, 0, 1)
        ).flatten()

        assertEquals(expected, actual)
    }

    @Test
    fun `Gives correct result after step with rules 2,4,5 | 3,6,8`() {
        val neighboursToBorn = mutableListOf(2, 4, 5)
        val neighboursToDie = mutableListOf(3, 6, 8)
        setupConwayAlgorithm(neighboursToBorn, neighboursToDie)

        val actual = conwayAlgorithm.gameStep().flatten()
        val expected = arrayOf<Array<Int?>>(
            arrayOf(0, 1, 0),
            arrayOf(1, 0, 1),
            arrayOf(0, 1, 0)
        ).flatten()

        assertEquals(expected, actual)
    }
}