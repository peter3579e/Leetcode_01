package com.android.leetcode.treeNode.iterative

import com.android.leetcode.treeNode.dfs.TreeNode
import java.util.Stack

object IterativeBSTSearch {

    fun iterativeBSTSearch(root: TreeNode?): IntArray {
        if (root == null) return intArrayOf()
        var array = mutableListOf<Int>()
        var stack = Stack<TreeNode>()
        var cur = root
        while(stack.isNotEmpty() || cur != null) {
            while (cur != null){
                stack.push(cur)
                cur = cur.left
            }
            cur = stack.pop()
            array.add(cur.`val`)
            cur = cur.right
        }

        return array.toIntArray()
    }
}

fun main() {
    var one = TreeNode(1)
    var two = TreeNode(2)
    var three = TreeNode(3)
    var four = TreeNode(4)
    var five = TreeNode(5)

    one.left = two
    one.right = five
    two.left = three
    two.right = four

    print(IterativeBSTSearch.iterativeBSTSearch(one).toList())
}