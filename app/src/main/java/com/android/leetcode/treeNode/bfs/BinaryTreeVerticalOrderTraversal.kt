package com.android.leetcode.treeNode.bfs

import java.util.Collections
import java.util.LinkedList
import java.util.Queue


object BinaryTreeVerticalOrderTraversal {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
    fun verticalOrderOriginal(root: TreeNode?): List<List<Int>> {

        var queue = LinkedList<NodeNcor>()
        var start = NodeNcor(root, 0, 0)
        queue.offer(start)

        var list = mutableListOf<NodeNcor>()

        while (queue.isNotEmpty()) {
            for(i in 0 until queue.size) {
                var cur = queue.poll()
                var node = cur.node!!
                var row = cur.row
                var col = cur.col
                list.add(cur)
                if(cur.node!!.left != null) {
                    queue.offer(
                        NodeNcor(
                            node?.left!!,
                            row+1,
                            col-1
                        )
                    )
                }
                if(cur.node!!.right != null) {
                    queue.offer(
                        NodeNcor(
                            node?.right!!,
                            row+1,
                            col+1
                        )
                    )
                }
            }
        }

        list.sortWith {t1:NodeNcor, t2:NodeNcor ->
            t1.col.compareTo(t2.col)
        }

        var ans = mutableListOf<MutableList<Int>>()
        var prev: Int? = null
        for (nc in list) {
            if(prev != nc.col) {
                ans.add(mutableListOf(nc.node!!.`val`))
            }else if(prev == nc.col) {
                ans[ans.size-1].add(nc.node!!.`val`)
            }
            prev = nc.col
        }

        return ans
    }

    data class NodeNcor(
        var node: TreeNode? = null,
        var row: Int,
        var col: Int
    )

    //Optimal
    fun verticalOrder(root: TreeNode?): List<List<Int>> {
        if(root == null) return listOf()
        var queue = LinkedList<Pair<Int,TreeNode>>()
        queue.offer(Pair(0,root))
        var map = hashMapOf<Int, MutableList<Int>>()

        while (queue.isNotEmpty()) {
            for (i in 0 until queue.size) {
                var cur = queue.poll()
                var col = cur.first
                var node = cur.second
                map[col] = map.getOrDefault(col, mutableListOf(node.`val`)).apply {
                    this.add(node.`val`)
                }
                if (node.left != null) {
                    queue.offer(
                        Pair(col - 1, node.left!!)
                    )
                }
                if (node.right != null) {
                    queue.offer(
                        Pair(
                            col + 1, node.right!!
                        )
                    )
                }
            }
        }

        var min = map.minOf { it.key }
        var max = map.maxOf { it.key }

        var ans = mutableListOf<List<Int>>()
        for (col in min..max) {
            ans.add(map[col]!!)
        }

        return ans
    }
}

object Solution3 {
    fun verticalOrder(root: BinaryTreeVerticalOrderTraversal.TreeNode?): List<List<Int>> {
        root ?: return emptyList()

        val orderMap = mutableMapOf<Int, MutableList<Int>>()
        val queue = ArrayDeque<Pair<BinaryTreeVerticalOrderTraversal.TreeNode, Int>>()

        queue.addLast(Pair(root, 0))

        while (queue.isNotEmpty()) {
            val (node, level) = queue.removeFirst()
            orderMap[level] = orderMap.getOrDefault(level, mutableListOf()).apply {
                this.add(node.`val`)
            }
            node.left?.let { queue.addLast(Pair(it, level - 1)) }
            node.right?.let { queue.addLast(Pair(it, level + 1)) }
        }

        val min = orderMap.keys.minWith(compareBy{it})
        val max = orderMap.keys.maxWith(compareBy{it})

        val ans = mutableListOf<MutableList<Int>>()

        for (i in min..max) {
            orderMap[i]?.let { ans.add(it) }
        }

        return ans
    }
}

object Soluton123 {
    fun verticalOrder(root: BinaryTreeVerticalOrderTraversal.TreeNode?): List<List<Int>> {
        if(root == null) return listOf(listOf())
        val list = mutableListOf<TripLet>()

        fun dfs(root: BinaryTreeVerticalOrderTraversal.TreeNode?, row: Int, column: Int) {
            if (root == null) return
            list.add(TripLet(root.`val`, row, column))
            dfs(root.left, row+1, column-1)
            dfs(root.right, row+1, column+1)
        }

        dfs(root, 0, 0)

        list.sortWith {n1,n2 ->
            if(n1.col != n2.col) {
                n1.col.compareTo(n2.col)
            } else {
                n1.row.compareTo(n2.row)
            }
        }

        val ans = mutableListOf<List<Int>>()
        var pre = list[0].col
        var temp = mutableListOf<Int>()

        for (triplet in list) {
            if(triplet.col == pre) {
                temp.add(triplet.value)
            } else {
                ans.add(temp.toList())
                temp = mutableListOf<Int>()
                temp.add(triplet.value)
                pre = triplet.col
            }
        }

        if(temp.isNotEmpty()) {
            ans.add(temp.toList())
        }

        return ans
    }

    data class TripLet(
        val value: Int,
        val row: Int,
        val col: Int
    )
}

object SolutionMostOptimal {
    fun verticalOrder(root: BinaryTreeVerticalOrderTraversal.TreeNode?): List<List<Int>> {
        if(root == null) return listOf()
        val map = hashMapOf<Int, MutableList<Int>>()
        var minCol = Int.MAX_VALUE
        var maxCol = Int.MIN_VALUE
        val queue = LinkedList<Pair<BinaryTreeVerticalOrderTraversal.TreeNode,Int>>()
        queue.offer(Pair(root,0))

        while(queue.isNotEmpty()) {
            repeat(queue.size) {
                val cur = queue.poll()!!
                map[cur.second] = map.getOrDefault(cur.second,mutableListOf()).apply {
                    this.add(cur.first!!.`val`)
                }
                minCol = minOf(minCol, cur.second)
                maxCol = maxOf(maxCol, cur.second)
                cur.first.left?.let{ queue.offer(Pair(it,cur.second-1))}
                cur.first.right?.let{ queue.offer(Pair(it,cur.second+1))}
            }
        }
        var ans = mutableListOf<List<Int>>()

        for(i in minCol..maxCol) {
            ans.add(map[i]!!)
        }

        return ans
    }
}

fun main() {
    var three = BinaryTreeVerticalOrderTraversal.TreeNode(3)
    var nine = BinaryTreeVerticalOrderTraversal.TreeNode(9)
    var twenty = BinaryTreeVerticalOrderTraversal.TreeNode(20)
    var fiften = BinaryTreeVerticalOrderTraversal.TreeNode(15)
    var seven = BinaryTreeVerticalOrderTraversal.TreeNode(7)
    three.left = nine
    three.right = twenty
    twenty.left = fiften
    twenty.right = seven
    print(Soluton123.verticalOrder(three))
}