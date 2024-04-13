package com.android.leetcode.graph

object ValidSudoku {
    fun isValidSudoku(board: Array<CharArray>): Boolean {

        val colMap = hashMapOf<Int, MutableSet<Char>>()
        val square = hashMapOf<Int, MutableSet<Char>>()

        for (row in 0 until board.size) {
            val rowSet = mutableSetOf<Char>()
            for (col in 0 until board[0].size) {
                val point = board[row][col]
                if (point.isDigit()) {
                    if (rowSet.contains(point)) return false
                    rowSet.add(point)
                    //check col
                    if (colMap.contains(col)) {
                        if (colMap[col]!!.contains(point)) return false
                        colMap[col]!!.add(point)
                    } else {
                        colMap[col] = mutableSetOf(point)
                    }
                    //check boxes
                    val box = row / 3 * 3 + col / 3
                    if (square.contains(box)) {
                        if (square[box]!!.contains(point)) return false
                        square[box]!!.add(point)
                    } else {
                        square[box] = mutableSetOf(point)
                    }
                }
            }
        }

        return true
    }
}

fun main() {
    val strs = listOf<String>()
    val str = strs.joinToString(" ")
    str.split(" ")
    val sudokuInput = listOf(
        listOf("5", "3", ".", ".", "7", ".", ".", ".", "."),
        listOf("6", ".", ".", "1", "9", "5", ".", ".", "."),
        listOf(".", "9", "8", ".", ".", ".", ".", "6", "."),
        listOf("8", ".", ".", ".", "6", ".", ".", ".", "3"),
        listOf("4", ".", ".", "8", ".", "3", ".", ".", "1"),
        listOf("7", ".", ".", ".", "2", ".", ".", ".", "6"),
        listOf(".", "6", ".", ".", ".", ".", "2", "8", "."),
        listOf(".", ".", ".", "4", "1", "9", ".", ".", "5"),
        listOf(".", ".", ".", ".", "8", ".", ".", "7", "9")
    )

    val sudokuArray = Array(9) { row ->
        CharArray(9) { col ->
            sudokuInput[row][col].firstOrNull() ?: '.'
        }
    }
    ValidSudoku.isValidSudoku(sudokuArray)
}