package com.android.leetcode.graph

object NumberOfSpaceCleaningRobotCleaned {
    fun numberOfCleanRooms(room: Array<IntArray>): Int {
        /*
        0 0 0 1
        0 1 0 1
        1 0 0 0
        */

        val x = intArrayOf(0, 1, 0, -1)
        val y = intArrayOf(1, 0, -1, 0)
        var cleanSpace = 0
        val visited = hashMapOf<Pair<Int,Int>,MutableSet<Int>>()

        fun isVaild(row: Int, col: Int): Boolean = row in 0 until room.size && col in 0 until room[0].size && room[row][col] != 1

        fun dfs(point: Pair<Int,Int>, dir: Int): Boolean {
            val row = point.first
            val col = point.second

            var newDir = dir

            for(i in 0..3) {
                if(newDir > 3) newDir = (newDir % 3) - 1
                val newRow = row + x[newDir]
                val newCol = col + y[newDir]
                if(isVaild(newRow,newCol)) {
                    if (!visited.contains(Pair(row,col))) {
                        cleanSpace++
                        visited[Pair(row,col)] = visited.getOrDefault(Pair(row,col), mutableSetOf()).apply {
                            this.add(newDir)
                        }
                        if (dfs(Pair(newRow, newCol), newDir)) return true
                    } else if (visited.contains(Pair(row,col)) && !visited[Pair(row,col)]!!.contains(newDir)) {
                        if (dfs(Pair(newRow, newCol), newDir)) return true
                    } else {
                        return true
                    }
                }
                newDir++
            }
            return true
        }

        dfs(Pair(0,0),0)
        return cleanSpace
    }
}

fun main() {
    /*
    0 0 0 1
    0 1 0 1
    1 0 0 0
     */
    NumberOfSpaceCleaningRobotCleaned.numberOfCleanRooms(arrayOf(intArrayOf(0,0,0,1), intArrayOf(0,1,0,1),
        intArrayOf(1,0,0,0)))
}