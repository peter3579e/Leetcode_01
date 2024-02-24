package com.android.leetcode.treeNode.dfs

import kotlin.math.max


object TreeDiameter {

    var diameter = 0
    val map: HashMap<Int, MutableList<Int>> = hashMapOf()

    fun treeDiameter(edges: Array<IntArray>): Int {
        if (edges.size == 1 || edges.isEmpty()) return edges.size
        for (edge in edges) {
            val key = edge[0]
            val value = edge[1]
            val list = map.getOrDefault(key, mutableListOf())
            list.add(value)
            map[key] = list
        }

        dfs(map.firstNotNullOf { it.key })
        return diameter
    }

    fun dfs(node: Int): Int {
        if (map[node] == null) return 1
        var max1 = 0
        var max2 = 0
        for (n in map[node]!!) {
            var depth = dfs(n)
            if (depth > max1) {
                max2 = max1
                max1 = depth
            } else if (depth > max2) {
                max2 = depth
            }
        }
        diameter = maxOf(diameter, max1 + max2)
        return max1 + 1
    }
}

fun main() {
    TreeDiameter.treeDiameter(arrayOf(intArrayOf(0,1), intArrayOf(0,2)))
}