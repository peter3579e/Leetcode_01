package com.android.leetcode.graph

object MinimumHeightTrees {
    fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
        if(n == 1) return listOf(0)
        var adj = hashMapOf<Int, MutableList<Int>>().apply {
            for((u,v) in edges) {
                this[u] = this.getOrDefault(u, mutableListOf()).apply { this.add(v) }
                this[v] = this.getOrDefault(v, mutableListOf()).apply { this.add(u) }
            }
        }

        val q = ArrayDeque<Int>().apply {
            for ((k,v) in adj) {
                if (v.size == 1) {
                    this.addFirst(k)
                }
            }
        }

        var left = n
        while (left > 2) {
            left = left - q.size
            repeat(q.size) {
                val node = q.removeLast()
                adj[node]?.forEach {
                    adj[it]?.remove(node)
                    if (adj[it]?.size == 1)
                        q.addFirst(it)
                }
            }
        }

        return q
    }
}

fun main() {
    MinimumHeightTrees.findMinHeightTrees(4, arrayOf(intArrayOf(1,0), intArrayOf(1,2), intArrayOf(1,3)))
}