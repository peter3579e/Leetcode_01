package com.android.leetcode.treeNode.dfs

object MinimumTimetoCollectAllApplesinaTree {
    fun minTime(n: Int, edges: Array<IntArray>, hasApple: List<Boolean>): Int {

        val adj = hashMapOf<Int,MutableList<Int>>()

        for(edge in edges) {
            val origin = edge[0]
            val dest = edge[1]
            adj[origin] = adj.getOrDefault(origin,mutableListOf()).apply {
                this.add(dest)
            }
        }

        fun dfs(n: Int): Int {


            var totalTime = 0
            var childTime = 0

            if(hasApple[n]) totalTime = totalTime + 2

            for(num in adj[n] ?: listOf()) {
                childTime = childTime + dfs(num)
            }

            if(childTime > 0 && n != 0) totalTime = totalTime + childTime + 2

            return totalTime
        }

        return dfs(0)
    }
}

fun main() {
    val arr = mutableListOf<Int>()
    arr.toIntArray()
    val int = arrayOf<IntArray>()
    int.toMutableList()
    val preSumMatrix = Array(2) { IntArray(2) { 0 } }
    var list = mutableListOf<Int>()
    list.toIntArray()
    //[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]
    MinimumTimetoCollectAllApplesinaTree.minTime(7, arrayOf(intArrayOf(0,1), intArrayOf(0,2),
        intArrayOf(1,4), intArrayOf(1,5), intArrayOf(2,3), intArrayOf(2,6)), listOf(false,false,true,false,true,true,false))
}