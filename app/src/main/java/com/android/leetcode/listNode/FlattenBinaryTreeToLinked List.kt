
package com.android.leetcode.listNode

object FlattenBinaryTreeToLinked{

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    var cur: TreeNode? = TreeNode(0)

    fun flatten(root: TreeNode?): Unit {
        if(root != null) {
            dfs(root)
        }
    }

    fun dfs(start: TreeNode?) {
        if(start == null) return
        var temp = TreeNode(start.`val`)
        temp.left = start.left
        temp.right = start.right
        cur!!.right = start
        cur!!.right!!.left = null
        cur = cur!!.right
        dfs(temp.left)
        dfs(temp.right)
    }
}

fun main() {
    var one = FlattenBinaryTreeToLinked.TreeNode(1)
    var two = FlattenBinaryTreeToLinked.TreeNode(2)
    var three = FlattenBinaryTreeToLinked.TreeNode(3)
    var four = FlattenBinaryTreeToLinked.TreeNode(4)
    var five = FlattenBinaryTreeToLinked.TreeNode(5)
    var six = FlattenBinaryTreeToLinked.TreeNode(6)

    one.left = two
    one.right = five
    two.left = three
    two.right = four
    five.right = six
    FlattenBinaryTreeToLinked.flatten(one)
}