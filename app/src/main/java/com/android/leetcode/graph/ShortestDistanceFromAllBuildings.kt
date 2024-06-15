package com.android.leetcode.graph

import java.util.LinkedList

object ShortestDistanceFromAllBuildings
{
    fun shortestDistance(grid: Array<IntArray>): Int {

        // find buliding point
        val buildings = mutableListOf<Pair<Int,Int>>()
        for(row in 0 until grid.size) {
            for(col in 0 until grid[0].size) {
                val cur = grid[row][col]
                if(cur == 1) buildings.add(Pair(row,col))
            }
        }

        if((grid.size == 1 && buildings.size > 2) || (grid[0].size == 1 && buildings.size > 2)) return -1

        val pointToSteps = hashMapOf<Pair<Int,Int>, Pair<Int,Int>>()


        fun bfs(point: Pair<Int,Int>) {

            val dir: List<Pair<Int,Int>> = listOf(Pair(0,1),Pair(1,0), Pair(0,-1), Pair(-1,0))

            val visited = mutableSetOf<Pair<Int,Int>>()

            val queue = LinkedList<Pair<Int,Int>>()

            queue.offer(point)
            visited.add(point)

            fun isVaild(x: Int, y: Int): Boolean = x in 0 until grid.size && y in 0 until grid[0].size && grid[x][y] == 0
            var steps = 1

            while(queue.isNotEmpty()) {
                repeat(queue.size) {
                    val cur = queue.poll()!!
                    val x = cur.first
                    val y = cur.second

                    for(d in dir) {
                        val newX = x + d.first
                        val newY = y + d.second
                        if(isVaild(newX, newY) && !visited.contains(Pair(newX,newY))) {
                            visited.add(Pair(newX,newY))
                            queue.offer(Pair(newX,newY))
                            pointToSteps[Pair(newX, newY)] = pointToSteps.getOrDefault(Pair(newX, newY), Pair(0, 0)).let {
                                Pair(it.first + 1, it.second + steps)
                            }
                        }
                    }
                }
                steps++
            }
        }

        for(building in buildings) {
            bfs(building)
        }

        var ans = Int.MAX_VALUE

        for((key,value) in pointToSteps) {
            if(value.first == buildings.size) {
                ans = minOf(ans,value.second)
            }
        }

        return if(ans == Int.MAX_VALUE) -1 else ans
    }
}

fun main() {
    //[[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
    ShortestDistanceFromAllBuildings.shortestDistance(arrayOf(intArrayOf(1,0,2,0,1), intArrayOf(0,0,0,0,0), intArrayOf(0,0,1,0,0)))
}