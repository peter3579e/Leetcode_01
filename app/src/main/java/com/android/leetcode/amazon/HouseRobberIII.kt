package com.android.leetcode.amazon

import java.util.LinkedList

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

fun rob(root: TreeNode?): Int {

    val rob = hashMapOf<TreeNode,Int>()
    val notRob = hashMapOf<TreeNode, Int>()

    fun helper(node: TreeNode?, parent: Boolean): Int {

        if(node == null) return 0

        if(parent) {
            notRob[node]?.let {
                return it
            }
            notRob[node] = helper(node.left,false) + helper(node.right, false)
            return notRob[node]!!
        }
        rob[node]?.let {
            return it
        }

        val robbing = node.`val` + helper(node.left, true) + helper(node.right, true)
        val notRobbing = helper(node.left, false) + helper(node.right, false)
        rob[node] = maxOf(robbing,notRobbing)

        return maxOf(robbing,notRobbing)
    }

    return helper(root,false)
}

fun main() {
    val three = TreeNode(3)
    val tow = TreeNode(2)
    val three2 = TreeNode(3)
    val three3 = TreeNode(3)
    val one = TreeNode(1)
    three.left = tow
    three.right = three2
    tow.right = three3
    three2.right = one
    rob(three)
}