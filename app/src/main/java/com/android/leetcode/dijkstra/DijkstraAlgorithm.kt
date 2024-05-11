package com.android.leetcode.dijkstra

import java.util.PriorityQueue

/*
*
* https://neetcode.io/problems/dijkstra
* Implement Dijkstra's shortest path algorithm.

Given a weighted, directed graph, and a starting vertex, return the shortest distance from the starting vertex to every vertex in the graph.

Input:

n - the number of vertices in the graph, where (2 <= n <= 100). Each vertex is labeled from 0 to n - 1.
edges - a list of tuples, each representing a directed edge in the form (u, v, w), where u is the source vertex, v is the destination vertex, and w is the weight of the edge, where (1 <= w <= 10).
src - the source vertex from which to start the algorithm, where (0 <= src < n).
Note: If a vertex is unreachable from the source vertex, the shortest path distance for the unreachable vertex should be -1.

Example 1:

Dijkstra's Algorithm

Input:
n = 5
edges = [[0,1,10], [0,2,3], [1,3,2], [2,1,4], [2,3,8], [2,4,2], [3,4,5]]
src = 0

Output:
{{0:0}, {1:7}, {2:3}, {3:9}, {4:5}}*/
object DijkstraAlgorithm {
    fun shortestPath(n: Int, edges: List<List<Int>>, src: Int): HashMap<Int,Int> {

        // create an adjacent list
        val adj = hashMapOf<Int,MutableList<List<Int>>>()

        for ((s,d,w) in edges) {
            adj[s] = adj.getOrDefault(s, mutableListOf()).apply {
                this.add(listOf(d,w))
            }
        }

        val shortest = hashMapOf<Int,Int>()
        // heap is a pair contain node,weight
        val minHeap = PriorityQueue {d1: Pair<Int,Int>, d2: Pair<Int,Int> ->
            d1.second.compareTo(d2.second)
        }

        minHeap.offer(Pair(src,0))

        while (minHeap.isNotEmpty()) {
            val (node,dist) = minHeap.poll()!!
            if (shortest.contains(node)) continue
            shortest[node] = dist

            for ((node2,dist2) in adj[node] ?: listOf()) {
                if (shortest.contains(node2)) continue
                minHeap.offer(Pair(node2,dist+dist2))
            }
        }

        for (i in 0 until n) {
            if (!shortest.contains(i)) {
                shortest[i] = -1
            }
        }


        return shortest
    }
}
//[0,1,10], [0,2,3], [1,3,2], [2,1,4], [2,3,8], [2,4,2], [3,4,5]
fun main() {
    DijkstraAlgorithm.shortestPath(5, listOf(listOf(0,1,10), listOf(0,2,3), listOf(1,3,2), listOf(2,1,4), listOf(2,3,8), listOf(2,4,2), listOf(3,4,5)), 0)
}