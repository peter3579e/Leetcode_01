package com.android.leetcode.graph

import java.util.Stack

class MakingALargeIsland {

    private val dr = intArrayOf(-1, 0, 1, 0)
    private val dc = intArrayOf(0, -1, 0, 1)

    fun largestIsland(grid: Array<IntArray>): Int {
        val N = grid.size

        var ans = 0
        var hasZero = false
        for (r in 0 until N)
            for (c in 0 until N)
                if (grid[r][c] == 0) {
                    hasZero = true
                    grid[r][c] = 1
                    ans = Math.max(ans, check(grid, r, c))
                    grid[r][c] = 0
                }

        return if (hasZero) ans else N * N
    }

    private fun check(grid: Array<IntArray>, r0: Int, c0: Int): Int {
        val N = grid.size
        val stack = Stack<Int>()
        val seen = HashSet<Int>()
        stack.push(r0 * N + c0)
        seen.add(r0 * N + c0)

        while (stack.isNotEmpty()) {
            val code = stack.pop()
            val r = code / N
            val c = code % N
            for (k in 0 until 4) {
                val nr = r + dr[k]
                val nc = c + dc[k]
                if (!seen.contains(nr * N + nc) && 0 <= nr && nr < N &&
                    0 <= nc && nc < N && grid[nr][nc] == 1) {
                    stack.push(nr * N + nc)
                    seen.add(nr * N + nc)
                }
            }
        }

        return seen.size
    }

    fun main () {

    }
}