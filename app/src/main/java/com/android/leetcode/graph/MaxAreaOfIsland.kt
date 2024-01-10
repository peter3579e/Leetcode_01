package com.android.leetcode.graph

import java.util.LinkedList

object MaxAreaOfIsland {

    var area = 0
    fun maxAreaOfIsland(grid: Array<IntArray>): Int {

        var row = grid.size - 1
        var column = grid[0].size - 1
        var maxArea = 0

        for (r in 0..row) {
            for (c in 0..column) {
                if(grid[r][c] == 1) {
                    grid[r][c] = 0
                    bfs(grid, intArrayOf(r,c))
                    if(area > maxArea) maxArea = area
                    area = 0
                }
            }
        }

        return maxArea
    }

    private fun dfs(grid: Array<IntArray>, start: IntArray) {
        var x = start[0]
        var y = start[1]
        var top = intArrayOf(x-1,y)
        var right = intArrayOf(x,y+1)
        var left = intArrayOf(x, y-1)
        var down = intArrayOf(x+1, y)

        for(point in listOf(top, right, left, down)) {
            if(!isIsland(grid, point[0], point[1])) continue
            area ++
            grid[point[0]][point[1]] = 0
            dfs(grid, point)
        }
    }

    private fun bfs(grid: Array<IntArray>, start: IntArray) {
        var queue = LinkedList<IntArray>()
        queue.offer(start)
        area ++

        while (queue.isNotEmpty()) {
            var current = queue.poll()
            var x = current[0]
            var y = current[1]
            var top = intArrayOf(x-1,y)
            var right = intArrayOf(x, y+1)
            var left = intArrayOf(x, y-1)
            var down = intArrayOf(x+1, y)

            for (point in listOf<IntArray>(top, right, left, down)) {
                if (!isIsland(grid, point[0], point[1])) continue
                grid[point[0]][point[1]] = 0
                queue.offer(point)
                area++
            }
        }
    }

    private fun isIsland(grid: Array<IntArray>, x: Int, y: Int) = x >= 0 && y >= 0 && x < grid.size && y < grid[0].size && grid[x][y] == 1
}

fun main() {
   println(MaxAreaOfIsland.maxAreaOfIsland(arrayOf(
       intArrayOf(0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0),
       intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0),
       intArrayOf(0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
       intArrayOf(0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0),
       intArrayOf(0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0),
       intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0),
       intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0),
       intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0)
   )))
}