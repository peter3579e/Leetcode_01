package com.android.leetcode.graph

object RedundantConnectionII {
    fun findRedundantDirectedConnection(edges: Array<IntArray>): IntArray {
        if (edges.isEmpty()) return intArrayOf()
        val adjMap = hashMapOf<Int,MutableList<Int>>()
        val childToParent = hashMapOf<Int,MutableList<Int>>()
        for(edge in edges) {
            val parent = edge[0]
            val child = edge[1]
            adjMap[parent] = adjMap.getOrDefault(parent,mutableListOf()).apply {
                this.add(child)
            }
            childToParent[child] = childToParent.getOrDefault(child,mutableListOf()).apply {
                this.add(parent)
            }
        }

        val seen = mutableSetOf<Int>()
        val seenMap = hashMapOf<Int,Boolean>()
        var seenNode = -1
        var foundCircle = false
        var prev = -1

        fun dfs(node:Int): Boolean {
            if (seenMap.contains(node)) {
                if (seenMap[node]!! == false) {
                    foundCircle = true
                    seenNode = node
                    return true // found circle
                }
            }
            if(seen.contains(node)) {
                seenNode = node
            }

            seen.add(node)
            seenMap[node] = false

            for(num in adjMap[node] ?: listOf()) {
                prev = node
                if(dfs(num)) return true
            }
            seenMap[node] = true
            return false
        }

        for((key,value) in adjMap) {
            if(dfs(key)) break
        }

        if(foundCircle) {
            return intArrayOf(prev,seenNode)
        }

        var max = Int.MIN_VALUE

        for(num in childToParent[seenNode]!!) {
            max = maxOf(max, num)
        }

        return  intArrayOf(max,seenNode)
    }
}

fun main() {
    RedundantConnectionII.findRedundantDirectedConnection(arrayOf(intArrayOf(5,2), intArrayOf(5,1), intArrayOf(3,1),
        intArrayOf(3,4),
        intArrayOf(3,5)
    ))
}