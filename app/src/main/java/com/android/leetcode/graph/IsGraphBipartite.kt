package com.android.leetcode.graph

object IsGraphBipartite {
    fun isBipartite(graph: Array<IntArray>): Boolean {

        var set = mutableSetOf<IntArray>()

        for(i in graph) {
            set.add(i)
        }

        return if (set.size > 2) false else true
    }
}

fun main() {
    IsGraphBipartite.isBipartite(arrayOf(intArrayOf(1,3), intArrayOf(0,2), intArrayOf(1,3), intArrayOf(0,2)))
}