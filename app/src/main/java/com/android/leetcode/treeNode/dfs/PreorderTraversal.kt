package com.android.leetcode.treeNode.dfs

object PreorderTraversal {

    fun preorderTraversal(root: TreeNode?, tempList: MutableList<Int>): List<Int> {
        if(root == null) return mutableListOf()

        tempList.add(root!!.`val`)
        preorderTraversal(root!!.left, tempList)
        preorderTraversal(root!!.right, tempList)
        return tempList.toList()
    }
}

fun main() {
    var one = TreeNode(1)
    var two = TreeNode(2)
    var three = TreeNode(3)
    one.right = two
    two.left = three

    print(PreorderTraversal.preorderTraversal(one, mutableListOf()))
}

