package com.android.leetcode.treeNode.binary

import com.android.leetcode.treeNode.dfs.TreeNode
import java.util.LinkedList

import java.util.Queue





object ConstructBnaryTreefromInorderAndPostorderTraversal {
    var post_idx = 0
    lateinit var postorder: IntArray
    lateinit var inorder: IntArray
    var idx_map = HashMap<Int, Int>()

    fun helper(in_left: Int, in_right: Int): TreeNode? {
        // if there are no elements to construct subtrees
        if (in_left > in_right) return null

        // pick up post_idx element as a root
        val root_val = postorder[post_idx]
        val root = TreeNode(root_val)

        // root splits inorder list
        // into left and right subtrees
        val index = idx_map[root_val]!!

        // recursion
        post_idx--
        // build the right subtree
        root.right = helper(index + 1, in_right)
        // build the left subtree
        root.left = helper(in_left, index - 1)
        return root
    }

    fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
        this.postorder = postorder
        this.inorder = inorder
        // start from the last postorder element
        post_idx = postorder.size - 1

        // build a hashmap value -> its index
        var idx = 0
        for (`val` in inorder) idx_map[`val`] = idx++
        return helper(0, inorder.size - 1)
    }
}

object Solution10 {
    fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
        val map = hashMapOf<Int,Int>()
        var lastIndex = postorder.size-1
        for(i in 0 until inorder.size) {
            map[inorder[i]] = i
        }
        fun helper(left: Int, right: Int): TreeNode? {
            if(left >right) return null
            val node = postorder[lastIndex]
            val root = TreeNode(node)
            val index = map[node]!!
            lastIndex--
            root.right = helper(index+1,right)
            root.left = helper(left,index-1)
            return root
        }
        return helper(0,lastIndex)
    }
}
fun main() {
    Solution10.buildTree(intArrayOf(9,3,15,20,7), intArrayOf(9,15,7,20,3))
}