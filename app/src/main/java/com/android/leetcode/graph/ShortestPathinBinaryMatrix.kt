package com.android.leetcode.graph

import java.util.LinkedList

object ShortestPathinBinaryMatrix {
    fun shortestPathBinaryMatrix(grid: Array<IntArray>): Int {
        val x = intArrayOf(0,1,1,1,0,-1,-1,-1)
        val y = intArrayOf(1,1,0,-1,-1,-1,0,1)
        var step = 1
        val queue = LinkedList<Pair<Int,Int>>()
        queue.offer(Pair(0,0))
        grid[0][0] = 1

        while (queue.isNotEmpty()) {
            repeat(queue.size) {
                val point = queue.poll()!!
                if(point.first == grid.size - 1 && point.second == grid[0].size - 1) return step
                for (i in 0..7) {
                    val row = point.first + x[i]
                    val col = point.second + y[i]
                    if( row in 0 until grid.size && col in 0 until grid[0].size && grid[row][col] == 0) {
                        queue.offer(Pair(row,col))
                        grid[row][col] = 1
                    }
                }
            }
            step++
        }

        return -1
    }

    fun shortestPathBinaryMatrixFaceBook(grid: Array<IntArray>): List<Pair<Int,Int>> {
        if (grid[0][0] == 1 || grid[grid.lastIndex][grid[0].lastIndex] == 1) return  listOf()
        val x = intArrayOf(0,1,1,1,0,-1,-1,-1)
        val y = intArrayOf(1,1,0,-1,-1,-1,0,1)
        var path = listOf<Pair<Int,Int>>()

        fun dfs(point: Pair<Int,Int>, grid: Array<IntArray>, temp: MutableList<Pair<Int,Int>>) {
            if(point.first == grid.size-1 && point.second == grid[0].size-1) {
                temp.add(point)
                if(path.isEmpty() || temp.size <= path.size) {
                    path = temp.toList()
                }
                return
            }

            temp.add(point)
            grid[point.first][point.second] = 1

            for(i in 0..7) {
                val newRow = point.first + x[i]
                val newCol = point.second + y[i]
                if (newRow in 0 until grid.size && newCol in 0 until grid[0].size && grid[newRow][newCol] == 0) {
                    grid[newRow][newCol] = 1
                    dfs(Pair(newRow,newCol), grid, temp)
                    grid[newRow][newCol] = 0
                    temp.removeLast()
                }
            }
        }

        dfs(Pair(0,0), grid, mutableListOf())

        return path
    }
}

fun main() {
    print(ShortestPathinBinaryMatrix.shortestPathBinaryMatrixFaceBook(
        arrayOf(intArrayOf(0,0,0),
            intArrayOf(1,1,0),
            intArrayOf(1,1,0))
    ))
}