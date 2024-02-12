package com.android.leetcode.treeNode.dfs

import java.util.PriorityQueue

object VerticalOrderTraversalOfABinaryTree {

    var map = hashMapOf<Int, MutableList<Int>>()

    fun verticalTraversal(root: TreeNode?): List<List<Int>> {
        dfs(root, intArrayOf(0,0)) // O(N)
        var heap = PriorityQueue {n1: Int?, n2: Int? ->
            n1!!.compareTo(n2!!)
        }
        map.forEach {key, value ->
            heap.offer(key)
        } //O(NlogN)

        var ans = mutableListOf<List<Int>>()

        while(heap.isNotEmpty()) {
            var cur = heap.poll()
            ans.add(map[cur]!!)
        }//O(NlogN)

        return ans
    }

    fun dfs(start: TreeNode?, cdt: IntArray) {
        if(start == null) return
        var x = cdt[1]
        var y = cdt[0]
        var list = map.getOrDefault(cdt[1],  mutableListOf())
        list.add(start.`val`)
        list.sort()
        map[cdt[1]] = list
        dfs(start.left, intArrayOf(y+1,x-1))
        dfs(start.right, intArrayOf(y+1,x+1))
    }
}

fun main() {
    val root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.left!!.left = TreeNode(4)
    root.left!!.right = TreeNode(5)
    root.right!!.left = TreeNode(6)
    root.right!!.right = TreeNode(7)

    println(VerticalOrderTraversalOfABinaryTree.verticalTraversal(root).toList())
}
