package com.android.leetcode.dijkstra

import java.util.Arrays
import java.util.PriorityQueue
import kotlin.math.min


object MinimumCostToReachCityWithDiscounts {

    fun minimumCost(n: Int, highways: Array<IntArray>, discounts: Int): Int {

        // create graph
        val map = hashMapOf<Int, MutableList<Pair<Int,Int>>>()

        for((origin,destination,price) in highways) {
            map[origin] = map.getOrDefault(origin,mutableListOf()).apply {
                this.add(Pair(destination,price))
            }
            map[destination] = map.getOrDefault(destination,mutableListOf()).apply {
                this.add(Pair(origin,price))
            }
        }

        val visited = mutableSetOf<Int>()
        val queue = PriorityQueue {pair1: Triple<Int,Int,Int>, pair2: Triple<Int,Int,Int> ->
            pair1.third.compareTo(pair2.third)
        }

        queue.offer(Triple(0,0,0))
        //[0,6,13,18]
        val costList = Array<Int>(n) {0}

        while(queue.isNotEmpty()) {
            val (origin,dest,price) = queue.poll()!!
            visited.add(dest)
            costList[dest] = costList[origin]  + price

            if(dest == n-1) {
                return costList[dest]
            }

            for((destination,cost) in map[dest] ?: listOf()) {
                if(!visited.contains(destination)) {
                    queue.offer(Triple(origin,destination,cost + costList[dest]))
                }
            }
        }

        return -1
    }

    class Solution {
        fun minimumCost(n: Int, highways: Array<IntArray>, discounts: Int): Int {
            // Construct the graph from the given highways array
            val graph: MutableList<MutableList<IntArray>> = ArrayList()
            for (i in 0 until n) {
                graph.add(ArrayList())
            }
            for (highway in highways) {
                val u = highway[0]
                val v = highway[1]
                val toll = highway[2]
                graph[u].add(intArrayOf(v, toll))
                graph[v].add(intArrayOf(u, toll))
            }

            // Min-heap priority queue to store tuples of (cost, city, discounts used)
            val pq = PriorityQueue(
                Comparator.comparingInt { a: IntArray ->
                    a[0]
                }
            )
            pq.offer(intArrayOf(0, 0, 0)) // Start from city 0 with cost 0 and 0 discounts used

            // 2D array to track minimum distance to each city with a given number of discounts used
            val dist = Array(n) {
                IntArray(
                    discounts + 1
                )
            }
            for (row in dist) {
                Arrays.fill(row, Int.MAX_VALUE)
            }
            dist[0][0] = 0

            val visited = Array(n) {
                BooleanArray(
                    discounts + 1
                )
            }

            while (!pq.isEmpty()) {
                val current = pq.poll()
                val currentCost = current[0]
                val city = current[1]
                val discountsUsed =
                    current[2]

                // Skip processing if already visited with the same number of discounts used
                if (visited[city][discountsUsed]) {
                    continue
                }
                visited[city][discountsUsed] = true

                // Explore all neighbors of the current city
                for (neighbor in graph[city]) {
                    val nextCity = neighbor[0]
                    val toll = neighbor[1]

                    // Case 1: Move to the neighbor without using a discount
                    if (currentCost + toll < dist[nextCity][discountsUsed]) {
                        dist[nextCity][discountsUsed] = currentCost + toll
                        pq.offer(
                            intArrayOf(
                                dist[nextCity][discountsUsed],
                                nextCity,
                                discountsUsed,
                            )
                        )
                    }

                    // Case 2: Move to the neighbor using a discount if available
                    if (discountsUsed < discounts) {
                        val newCostWithDiscount = currentCost + toll / 2
                        if (newCostWithDiscount < dist[nextCity][discountsUsed + 1]
                        ) {
                            dist[nextCity][discountsUsed + 1] = newCostWithDiscount
                            pq.offer(
                                intArrayOf(
                                    newCostWithDiscount,
                                    nextCity,
                                    discountsUsed + 1,
                                )
                            )
                        }
                    }
                }
            }

            // Find the minimum cost to reach city n-1 with any number of discounts used
            var minCost = Int.MAX_VALUE
            for (d in 0..discounts) {
                minCost = min(minCost.toDouble(), dist[n - 1][d].toDouble()).toInt()
            }
            return if (minCost == Int.MAX_VALUE) -1 else minCost
        }
    }

}

fun main() {
    print(MinimumCostToReachCityWithDiscounts.minimumCost(4,arrayOf(intArrayOf(1,3,17), intArrayOf(1,2,7),
        intArrayOf(3,2,5), intArrayOf(0,1,6), intArrayOf(3,0,20)),20))
}