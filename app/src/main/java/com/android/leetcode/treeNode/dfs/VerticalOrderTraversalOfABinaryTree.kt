package com.android.leetcode.treeNode.dfs

import java.util.LinkedList


object VerticalOrderTraversalOfABinaryTree {

    var list = mutableListOf<TripLet>()
    data class TripLet(
        val row: Int,
        var column: Int,
        var value: Int
    )


    fun verticalTraversal(root: TreeNode?): List<List<Int>> {
        dfs(root, intArrayOf(0,0)) // O(N)
        list.sortWith { t1: TripLet, t2: TripLet ->
            if (t1.column == t2.column && t1.row == t2.row) {
                t1.value.compareTo(t2.value)
            } else if (t1.column == t2.column && t1.row != t2.row) {
                t1.row.compareTo(t2.row)
            } else {
                t1.column.compareTo(t2.column)
            }
        }

        var ans = mutableListOf<List<Int>>()

        var currColumn = mutableListOf<Int>()
        var currColumnIndex: Int = list.get(0).column

        for (triplet in list) {
            if (triplet.column == currColumnIndex) {
                currColumn.add(triplet.value)
            }else {
                currColumnIndex = triplet.column
                ans.add(currColumn)
                currColumn = mutableListOf()
                currColumn.add(triplet.value)
            }
        }
        ans.add(currColumn)


        return ans
    }

    fun dfs(start: TreeNode?, cdt: IntArray) {
        if(start == null) return
        var x = cdt[1]
        var y = cdt[0]
        list.add(TripLet(y,x,start.`val`))
        dfs(start.left, intArrayOf(y+1,x-1))
        dfs(start.right, intArrayOf(y+1,x+1))
    }

}

object Soltion5 {
    data class TripLet(
        var node: TreeNode,
        val row: Int,
        var col: Int
    )


    fun verticalTraversal(root: TreeNode?): List<List<Int>> {
        if(root == null) return listOf()

        var queue = LinkedList<TripLet>()
        queue.offer(TripLet(root,0,0))
        var list = mutableListOf<TripLet>()

        while (queue.isNotEmpty()) {
            for(t in 0 until queue.size) {
                val cur = queue.poll()
                val row = cur.row
                val col = cur.col
                val node = cur.node
                list.add(cur)
                node!!.left?.let { queue.offer(TripLet(it,row+1,col-1)) }
                node!!.right?.let { queue.offer(TripLet(it, row+1, col+1)) }
            }
        }

        list.sortWith { t1: TripLet, t2: TripLet ->
            if (t1.col != t2.col) {
                t1.col.compareTo(t2.col)
            } else if (t1.row != t2.row) {
                t1.row.compareTo(t2.row)
            } else {
                t1.node.`val`.compareTo(t2.node.`val`)
            }
        }

        var prev: Int? = null

        var ans = mutableListOf<MutableList<Int>>()

        for(t in list) {
            var col = t.col
            var value = t.node.`val`
            if(prev != col) {
                ans.add(mutableListOf(value))
            }else {
                ans[ans.size-1].add(value)
            }
            prev = col
        }

        return ans
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
