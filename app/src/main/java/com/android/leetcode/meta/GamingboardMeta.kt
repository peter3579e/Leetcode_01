package com.android.leetcode.meta

import android.graphics.Point
import java.util.LinkedList
import java.util.Queue

/*
0 0 0 0 0 0 0
0 0 1 0 0 1 0
0 0 1 0 1 1 0
0 0 1 0 1 0 1
1 1 1 0 0 0 0
 */
object GamingboardMeta {
    fun findPath(grid: Array<IntArray>): List<Pair<Int,Int>> {
        if (grid[0][0] == 1 || grid[grid.size-1][grid.size-1] == 1) return listOf()
        val x = intArrayOf(0,1,1,1,0,-1,-1,-1)
        val y = intArrayOf(1,1,0,-1,-1,-1,0,1)

        val queue = LinkedList<Pair<Int,Int>>()
        val adjNode = hashMapOf<Pair<Int,Int>,Pair<Int,Int>>()
        queue.offer(Pair(0,0))
        grid[0][0] = 1
        val ans = mutableListOf<Pair<Int,Int>>()

        while (queue.isNotEmpty()) {
            repeat(queue.size) {
                var cur = queue.poll()!!
                var row = cur.first
                var col = cur.second

                if (row == grid.size-1 && col == grid[0].size-1) {

                    var temp = Pair(row, col)
                    while (temp != Pair(0, 0)) {
                        ans.add(temp)
                        temp = adjNode[temp]!!
                    }
                    ans.add(Pair(0, 0))
                    return ans.reversed()
                }
                for (i in 0..7) {
                    val newRow = row + x[i]
                    val newCol = col + y[i]
                    if (newRow in 0 until grid.size && newCol in 0 until grid[0].size && grid[newRow][newCol] == 0) {
                        adjNode[Pair(newRow,newCol)] = Pair(row,col)
                        queue.offer(Pair(newRow,newCol))
                        grid[newRow][newCol] = 1
                    }
                }

            }
        }

        return listOf()
    }

    fun findPath2(maze: Array<IntArray>): List<Pair<Int, Int>> {
        val rows = maze.size
        val cols = maze[0].size

        // Define directions: up, down, left, right
        val directions = arrayOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1, -1 to 1, 1 to 1, 1 to -1, -1 to -1)

        // Queue for BFS
        val queue: Queue<Pair<Int, Int>> = LinkedList()
        // Map to store parent nodes
        val parentMap = mutableMapOf<Pair<Int, Int>, Pair<Int, Int>>()
        // Set to track visited nodes
        val visited = mutableSetOf<Pair<Int, Int>>()

        // Add entrance to the queue
        queue.offer(0 to 0)
        // Mark entrance as visited
        visited.add(0 to 0)

        while (queue.isNotEmpty()) {
            val current = queue.poll()

            if (current.first == rows - 1 && current.second == cols - 1) {
                // Reached the exit, reconstruct the path
                val path = mutableListOf<Pair<Int, Int>>()
                var node = current
                while (node != null) {
                    path.add(node)
                    node = parentMap[node]
                }
                return path.reversed()
            }

            // Explore neighbors
            for ((dx, dy) in directions) {
                val newX = current.first + dx
                val newY = current.second + dy

                // Check if the new position is within bounds and passable
                if (newX in 0 until rows && newY in 0 until cols && maze[newX][newY] == 0 && (newX to newY) !in visited) {
                    queue.offer(newX to newY)
                    visited.add(newX to newY)
                    parentMap[newX to newY] = current
                }
            }
        }

        // If no path found
        return emptyList()
    }

    /*
        0 0 0
        1 0 0
        0 0 0
     */

    fun findPathBFS(grid: Array<IntArray>): List<Pair<Int,Int>> {
        val x = intArrayOf(0,1,1,1,0,-1,-1,-1)
        val y = intArrayOf(1,1,0,-1,-1,-1,0,1)

        if (grid[0][0] == 1 || grid[grid.lastIndex][grid.lastIndex] == 1) return listOf()

        val adj = hashMapOf<Pair<Int,Int>,Pair<Int,Int>>()
        val queue = LinkedList<Pair<Int,Int>>()

        queue.offer(Pair(0,0))
        grid[0][0] = 1

        val ans = mutableListOf<Pair<Int,Int>>()

        while (queue.isNotEmpty()) {
            val cur = queue.poll()!!
            val row = cur.first
            val col = cur.second

            if (row == grid.size-1 && col == grid.size-1) {
                ans.add(Pair(row,col))
                var reRow = row
                var reCol = col
                while (reRow != 0 && reCol != 0) {
                    val point = adj[Pair(reRow,reCol)]!!
                    reRow = point.first
                    reCol = point.second
                    ans.add(Pair(reRow,reCol))
                }
                ans.reversed()
                break
            }

            for (i in 0..7) {
                val newRow = row + x[i]
                val newCol = col + y[i]
                if (newRow in 0 until grid.size && newCol in 0 until grid[0].size && grid[newRow][newCol] != 1) {
                    queue.offer(Pair(newRow,newCol))
                    grid[newRow][newCol] = 1
                    adj[Pair(newRow,newCol)] = Pair(row,col)
                }
            }
        }

        return ans
    }









    fun findPathDFS(grid: Array<IntArray>): List<Pair<Int, Int>> {
        val x = intArrayOf(0,1,1,1,0,-1,-1,-1)
        val y = intArrayOf(1,1,0,-1,-1,-1,0,1)

        if (grid[0][0] == 1 || grid[grid.size-1][grid[0].size-1] == 1) return listOf()

        var ans = listOf<Pair<Int,Int>>()

        fun dfs(row: Int, col: Int, temp: MutableList<Pair<Int,Int>>): Boolean {
            if(row == grid.size-1 && col == grid[0].size - 1) {
                ans = temp.toList()
                return true
            }
            for (i in 0..7) {
                val newRow = row + x[i]
                val newCol = col + y[i]
                if(newRow in 0 until grid.size && newCol in 0 until grid[0].size && grid[newRow][newCol] == 0) {
                    grid[newRow][newCol] = 1
                    temp.add(Pair(newRow,newCol))
                    if(dfs(newRow,newCol,temp)) return true
                    temp.removeLast()
                }
            }
            return false
        }

        grid[0][0] = 1
        dfs(0,0, mutableListOf(Pair(0,0)))
        return ans
    }
}

fun main() {
    print(GamingboardMeta.findPathBFS(arrayOf(intArrayOf(0,0,0), intArrayOf(1,0,0), intArrayOf(0,0,0))))
    /*
    * 000          0 1 2
    * 100            1 2
    * 000            2 2
    *  00 -> 01 -> 02 -> 12 -> 22
    *  00 -> 10 -> 20 -> 21 -> 22
    * 0,1,2,3,4,5,0
    * 1,2,*,4,5,*,0
    * 0,0,*,5,*,*,0
    * 0,0,*,6,*,0,*
    * *,*,*,7,0,0,0
    * */
    //intArrayOf(0,0,0,0,0,0,0), intArrayOf(0,0,1,0,0,1,0), intArrayOf(0,0,1,0,1,1,0), intArrayOf(0,0,1,0,1,0,1), intArrayOf(1,1,1,0,0,0,0)
}