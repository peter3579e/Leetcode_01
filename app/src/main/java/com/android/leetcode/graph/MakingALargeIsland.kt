package com.android.leetcode.graph

import java.util.Stack


object MakingALargeIsland {

    //this is the best solution I can write, but it exceed the time limit
    var area = 0

    fun largestIsland(grid: Array<IntArray>): Int {
        val N = grid.size

        var ans = 0
        var hasZero = false
        for (r in 0 until N)
            for (c in 0 until N)
                if (grid[r][c] == 0) {
                    hasZero = true
                    val newGrid = deepCopy(grid) // Create a deep copy of the grid
                    newGrid[r][c] = 1
                    dfs(newGrid, intArrayOf(r, c))
                    ans = Math.max(ans, area)
                    area = 0
                }

        return if (hasZero) ans else N * N
    }

    private fun dfs(grid: Array<IntArray>, start: IntArray) {
        var x = start[0]
        var y = start[1]
        grid[x][y] = 0
        area++
        var top = intArrayOf(x - 1, y)
        var left = intArrayOf(x, y - 1)
        var right = intArrayOf(x, y + 1)
        var down = intArrayOf(x + 1, y)

        for (point in listOf(top, left, right, down)) {
            if (!isIsland(grid, point[0], point[1])) continue
            dfs(grid, point)
        }
    }

    private fun isIsland(grid: Array<IntArray>, x: Int, y: Int) =
        x >= 0 && y >= 0 && x < grid.size && y < grid.size && grid[x][y] == 1

    private fun deepCopy(grid: Array<IntArray>): Array<IntArray> {
        return Array(grid.size) { row ->
            IntArray(grid[row].size) { col ->
                grid[row][col]
            }
        }
    }
}

object MakingALargeIsland2 {

    var map = hashMapOf<Int, Int>()
    var area = 0
    /*
    1, 0
    0, 1
     */

    fun largestIsland(grid: Array<IntArray>): Int {
        val N = grid.size
        var count = 1

        var hasZero = false
        var max = 0

        for (r in 0 until N) {
            for (c in 0 until N)
                if (grid[r][c] == 1) {
                    dfs(grid, intArrayOf(r, c), ++count)
                    map[count] = area
                    area = 0
                }
        }

        for (r in 0 until N) {
            for (c in 0 until N)
                if (grid[r][c] == 0) {
                    hasZero = true
                    var box = 1
                    var top = intArrayOf(r - 1, c)
                    var left = intArrayOf(r, c - 1)
                    var right = intArrayOf(r, c + 1)
                    var down = intArrayOf(r + 1, c)
                    var list = mutableListOf<Int>()

                    for (point in listOf(top, left, right, down)) {
                        if (!isVaild(grid, point[0], point[1])) continue
                        var x = point[0]
                        var y = point[1]
                        if (map.contains(grid[x][y]) && !list.contains(grid[x][y])) {
                            list.add(grid[x][y])
                            var a = map[grid[x][y]]!!
                            box = box + a
                            max = maxOf(max, box)
                        }
                    }
                }
        }

        return if (!hasZero) N * N else if (map.isEmpty()) 1 else max
    }

    private fun dfs(grid: Array<IntArray>, start: IntArray, num: Int) {
        var x = start[0]
        var y = start[1]
        grid[x][y] = num
        area++
        var top = intArrayOf(x - 1, y)
        var left = intArrayOf(x, y - 1)
        var right = intArrayOf(x, y + 1)
        var down = intArrayOf(x + 1, y)

        for (point in listOf(top, left, right, down)) {
            if (!isIsland(grid, point[0], point[1])) continue
            dfs(grid, point, num)
        }
    }

    private fun isVaild(grid: Array<IntArray>, x: Int, y: Int) =
        x >= 0 && y >= 0 && x < grid.size && y < grid.size


    private fun isIsland(grid: Array<IntArray>, x: Int, y: Int) =
        x >= 0 && y >= 0 && x < grid.size && y < grid.size && grid[x][y] == 1

}

object Solution4 {

    var map = hashMapOf<Int, Int>()
    var area = 0
    /*
    1, 0
    0, 1
     */

    fun largestIsland(grid: Array<IntArray>): Int {
        val N = grid.size
        var count = 1

        var hasZero = false
        var max = 0

        for (r in 0 until N) {
            for (c in 0 until N)
                if (grid[r][c] == 0) {
                    hasZero = true
                    var box = 1
                    var top = intArrayOf(r - 1, c)
                    var left = intArrayOf(r, c - 1)
                    var right = intArrayOf(r, c + 1)
                    var down = intArrayOf(r + 1, c)
                    var list = mutableListOf<Int>()
                    for (point in listOf(top, left, right, down)) {
                        if (!isIsland(grid, point[0], point[1])) continue
                        var x = point[0]
                        var y = point[1]
                        if(!map.contains(grid[x][y])) {
                            dfs(grid, intArrayOf(x, y), ++count)
                            map[count] = area
                            area = 0
                        }
                        if (map.contains(grid[x][y]) && !list.contains(grid[x][y])) {
                            list.add(grid[x][y])
                            var a = map[grid[x][y]]!!
                            box = box + a
                            max = maxOf(max, box)
                        }
                    }
                }
        }

        return if (!hasZero) N * N else if (map.isEmpty()) 1 else max
    }

    private fun dfs(grid: Array<IntArray>, start: IntArray, num: Int) {
        var x = start[0]
        var y = start[1]
        grid[x][y] = num
        area++
        var top = intArrayOf(x - 1, y)
        var left = intArrayOf(x, y - 1)
        var right = intArrayOf(x, y + 1)
        var down = intArrayOf(x + 1, y)

        for (point in listOf(top, left, right, down)) {
            if (!isIsland(grid, point[0], point[1])) continue
            dfs(grid, point, num)
        }
    }

    private fun isVaild(grid: Array<IntArray>, x: Int, y: Int) =
        x >= 0 && y >= 0 && x < grid.size && y < grid.size


    private fun isIsland(grid: Array<IntArray>, x: Int, y: Int) =
        x >= 0 && y >= 0 && x < grid.size && y < grid.size && grid[x][y] == 1

}

fun main() {
    //[[0,0,0,0,0,0,0],[0,1,1,1,1,0,0],[0,1,0,0,1,0,0],[1,0,1,0,1,0,0],[0,1,0,0,1,0,0],[0,1,0,0,1,0,0],[0,1,1,1,1,0,0]]
    /*
    intArrayOf(0,0,0,0,0,0,0),
            intArrayOf(0,1,1,1,1,0,0),
            intArrayOf(0,1,0,0,1,0,0),
            intArrayOf(1,0,1,0,1,0,0),
            intArrayOf(0,1,0,0,1,0,0),
            intArrayOf(0,1,0,0,1,0,0),
            intArrayOf(0,1,1,1,1,0,0)
     */
    println(Solution4.largestIsland(
        arrayOf(
            intArrayOf(1,0),
            intArrayOf(0,1)
        )
    )
    )
}