package com.android.leetcode.treeNode.dfs

import com.android.leetcode.listNode.FlattenBinaryTreeToLinked

object BinaryTreePaths {
    var list = mutableListOf<String>()
    fun binaryTreePaths(root: TreeNode?): List<String> {
        if(root == null) return listOf()
        dfs(root,StringBuilder())
        return list
    }

    fun dfs(start: TreeNode?, s: StringBuilder) {
        var startLength = s.length
        if(start == null) {
            return
        }
        if(s.isEmpty()) {
            s.append(start.`val`.toString())
        } else {
            s.append("->"+start.`val`.toString())
        }
        if(start.left == null && start.right == null) list.add(s.toString())
        dfs(start.left,s)
        dfs(start.right,s)
        s.delete(startLength,s.length)
    }
}

fun main () {
    var one = TreeNode(1)
    var two = TreeNode(2)
    var three = TreeNode(3)
    var five = TreeNode(5)
    one.left = two
    one.right = three
    two.right = five
    BinaryTreePaths.binaryTreePaths(one)
}

