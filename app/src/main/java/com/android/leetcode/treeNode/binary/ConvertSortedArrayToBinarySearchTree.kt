package com.android.leetcode.treeNode.binary

import com.android.leetcode.treeNode.dfs.TreeNode

object ConvertSortedArrayToBinarySearchTree {
    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        return dfs(0,nums.size-1,nums)
    }

    fun dfs(left: Int, right:Int, nums: IntArray): TreeNode? {
        if(left > right) return null
        val mid = (left+right) / 2
        val node = TreeNode(nums[mid])
        node.left = dfs(left, mid-1, nums)
        node.right = dfs(mid+1,right, nums)
        return node
    }
}

fun main() {
    var intArray = intArrayOf()
    intArray.toMutableList()
    ConvertSortedArrayToBinarySearchTree.sortedArrayToBST(intArrayOf(-10,-3,0,5,9))
}