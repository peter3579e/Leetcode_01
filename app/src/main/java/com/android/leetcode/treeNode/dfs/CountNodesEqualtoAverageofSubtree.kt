package com.android.leetcode.treeNode.dfs

object CountNodesEqualtoAverageofSubtree {
    fun averageOfSubtree(root: TreeNode?): Int {
        if(root == null) return 0
        if(root.left == null && root.right == null) return root.`val`
        val list = mutableListOf<Int>()
        //Pair(count, sum)
        fun dfs(root: TreeNode?): Pair<Int,Int> {
            if(root == null) return Pair(0,0)

            var left = dfs(root.left) // 0
            var right = dfs(root.right)
            val sum = left.second + right.second + root.`val`
            val division = left.first + right.first + 1
            val avaerage = if(division != 0) sum / division else 0
            if(avaerage == root.`val` && (left.first != 0  || right.first != 0)) list.add(root.`val`)
            return Pair(division,sum)
        }

        dfs(root)
        return if (list.isNotEmpty()) list[0] else 0
    }
}

fun main() {
    val four = TreeNode(4)
    val eight = TreeNode(8)
    val five = TreeNode(5)
    val zero = TreeNode(0)
    val one = TreeNode(1)
    val six = TreeNode(6)

    four.left = eight
    four.right = five
    eight.left = zero
    eight.right = one
    five.right = six

    CountNodesEqualtoAverageofSubtree.averageOfSubtree(four)
}