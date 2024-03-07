package com.example.leonapplication.tdddemo

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class UtilsTest {

  private lateinit var board: Array<Array<Player?>>

  @Before
  fun setup() {
    board = emptyBoard()
  }

  @Test
  fun testInitializeEmptyBoard() {

    assertEquals(3, board.size)
    assertEquals(3, board[0].size)

    board.forEach { lines ->
      val allNull = lines.all { it == null }
      assertTrue(allNull)
    }
  }

  @Test
  fun testBoardHasSameLine() {
    board.forEachIndexed { index, line ->
      if (index == 1) {
        (0 until 3).forEach {
          line[it] = Player.A
        }
      }
    }
    assertEquals(Player.A, board.checkIfAllLineIsSamePlayer())
  }

  @Test
  fun testBoardHasSameColumn() {
    board.forEachIndexed { _, line ->
      line[0] = Player.A
    }
    assertEquals(Player.None, board.checkIfAllLineIsSamePlayer())
    val converted = board.switchColumnToLine()
    assertEquals(Player.A, converted.checkIfAllLineIsSamePlayer())
  }

  @Test
  fun testCheckHasSameXLine() {
    board.forEachIndexed { index, line ->
      (0..2).forEach {
        if (index == it) line[it] = Player.B
      }
    }

  }
}