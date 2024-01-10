package com.android.leetcode.graph

import java.util.LinkedList
import java.util.Queue

object NumberOfIsland {

    fun numIslands(grid: Array<CharArray>): Int {
        var island = 0
        var row = grid.size - 1
        var column = grid[0].size - 1

        for (r in 0..row) {
            for (c in 0..column) {
                if (grid[r][c] == '1') {
                    island++
                    bfs(grid, intArrayOf(r,c))
                }
            }
        }
        return island
    }

    //Breath first search
    fun bfs(grid: Array<CharArray>, start: IntArray) {
        val queue: Queue<IntArray> = LinkedList<IntArray>()
        queue.offer(start)
        grid[start[0]][start[1]] = '0'
        while (queue.isNotEmpty()) {
            val current = queue.poll()
            var x = current[0]
            var y = current[1]

            val top = intArrayOf(x-1,y)
            val right = intArrayOf(x, y+1)
            val left = intArrayOf(x, y-1)
            val down = intArrayOf(y+1, x)

            for (point in listOf<IntArray>(top, right, left, down)) {
                if (!isIsland(grid,point)) continue
                queue.offer(point)
                grid[point[0]][point[1]] = '0'
            }
        }
    }

    //Depth First Search
    private fun dfs(grid: Array<CharArray>, start: IntArray) {
        grid[start[0]][start[1]] = '0'

        var x = start[0]
        var y = start[1]
        var top = intArrayOf(x-1, y)
        var right = intArrayOf(x, y+1)
        var left = intArrayOf(x, y-1)
        var down = intArrayOf(x+1, y)

        for(point in listOf(top,right,left,down)) {
            if (!isIsland(grid, point)) continue
            grid[point[0]][point[1]] = '0'
            dfs(grid, point)
        }
    }

    private fun isIsland(grid: Array<CharArray>, point: IntArray) =
        point[0] >= 0 && point[1] >= 0 && point[0] < grid.size && point[1] < grid[0].size && grid[point[0]][point[1]] == '1'
 }

fun main() {
    NumberOfIsland.numIslands(
        arrayOf(
            charArrayOf('1', '1', '1', '1', '0'),
            charArrayOf('1', '1', '0', '1', '0'),
            charArrayOf('1', '1', '0', '0', '0'),
            charArrayOf('0', '0', '0', '0', '0')
        )
    )
}