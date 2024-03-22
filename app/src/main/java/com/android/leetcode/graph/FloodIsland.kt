package com.android.leetcode.graph

object FloodIsland {

    /*
    *    int matrix, all elements are positive, value mean sea level
   top-left -> bottom-right
   what i highest flood I can still reach to bottom-right
                    5.  2.  1. 1
                    3.  6   4. 9
                    2.  5.  3  5
        return 2

    *
    *
    * */
    // return the maximum height
    fun floodIsland (grid: Array<IntArray>): Int {
        val max = maxOf(grid[0][0],grid[grid.size-1][grid[0].size-1])

        var left = 0
        var right = max

        while (left < right) { // Changed the termination condition
            val mid = (left + right) / 2
            if (ableToReach(0, 0, grid, mutableSetOf(), mid)) {
                left = mid // Since mid is a potential solution
            } else {
                right = mid - 1 // If unable to reach, decrease right
            }
        }

        return left
    }

    private val x = intArrayOf(0,1,0,-1)
    private val y = intArrayOf(1,0,-1,0)

    private fun ableToReach(row: Int, col: Int, grid: Array<IntArray>, visited: MutableSet<Pair<Int,Int>>, level: Int): Boolean {
        if (row == grid.size-1 && col == grid[0].size-1) {
            return true
        }

        visited.add(Pair(row,col))

        for (i in 0..3) {
            val newRow = row + x[i]
            val newCol = col + y[i]
            if (newRow in 0 until grid.size && newCol in 0 until grid[0].size && grid[newRow][newCol] > level && !visited.contains(Pair(newRow,newCol))) {
                if(ableToReach(newRow,newCol,grid,visited,level)) return true
            }
        }
        return false
    }
}

fun main() {
    print(FloodIsland.floodIsland(arrayOf(intArrayOf(5,2,1,1), intArrayOf(3,6,4,9), intArrayOf(2,5,3,5))))
}