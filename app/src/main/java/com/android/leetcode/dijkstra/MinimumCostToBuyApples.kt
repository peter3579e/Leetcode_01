package com.android.leetcode.dijkstra

import java.util.PriorityQueue

object MinimumCostToBuyApples {
    /*
    * */
    fun minCost(n: Int, roads: Array<IntArray>, appleCost: IntArray, k: Int): LongArray {

        val adj = hashMapOf<Int, MutableList<Pair<Int,Long>>>()
        for((origin,dest,price) in roads) {
            adj[origin] = adj.getOrDefault(origin, mutableListOf()).apply {
                this.add(Pair(dest,  (k+1).toLong() * price.toLong()))
            }
            adj[dest] = adj.getOrDefault(dest, mutableListOf()).apply {
                this.add(Pair(origin, (k+1).toLong() * price.toLong()))
            }
        }


        fun dijkstra(src: Int): Long {

            // node price
            val minHeap = PriorityQueue {n1: Pair<Int,Long>, n2: Pair<Int,Long> ->
                n1.second.compareTo(n2.second)
            }

            var cheapest = Long.MAX_VALUE
            val visited = mutableSetOf<Int>()

            minHeap.offer(Pair(src,0))
            while(minHeap.isNotEmpty()) {
                val (node, cost) = minHeap.poll()!!
                if(visited.contains(node)) continue
                cheapest = minOf(cheapest,cost + appleCost[node-1])
                visited.add(node)
                for((node2,price) in adj[node] ?: listOf()) {
                    if(visited.contains(node2)) continue
                    minHeap.offer(Pair(node2,price+cost))
                }
            }
            return cheapest
        }

        val ans = LongArray(n){ 0 }

        for(i in 1..n) {
            ans[i-1] = dijkstra(i)
        }

        return ans
    }
}

/*
[[1,2,4],[2,3,2],[2,4,5],[3,4,1],[1,3,4]]
[56,42,102,301]
1.  2.  3.  4

56
42 + 4 + 4*2
102

adj nextnode cost
[1] [1,       56][2,       54] [3,       4]
[2] [1,       4] [3,       2] [4,       5]
[3] [2,       2] [4,       1] [1,       4]
[4] [2,       5] [3,       1]
 */

fun main() {
    /*
    n = 4, roads = [[1,2,4],[2,3,2],[2,4,5],[3,4,1],[1,3,4]], appleCost = [56,42,102,301], k = 2
     */
    MinimumCostToBuyApples.minCost(4, arrayOf(intArrayOf(1,2,4), intArrayOf(2,3,2), intArrayOf(2,4,5), intArrayOf(3,4,1), intArrayOf(1,3,4)), intArrayOf(56,42,102,301),2)
}