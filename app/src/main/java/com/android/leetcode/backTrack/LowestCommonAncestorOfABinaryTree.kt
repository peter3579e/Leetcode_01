package com.android.leetcode.backTrack

import com.android.leetcode.treeNode.dfs.TreeNode

// time complexity is O(N)
// Space is O(N)
object LowestCommonAncestorOfABinaryTree {
    var ans = TreeNode(0)
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        dfs(root, p, q)
        return ans
    }

    fun dfs(root: TreeNode?, p: TreeNode?, q: TreeNode?): Boolean {
        if(root == null) return false
        var mid = if (root.`val` == p!!.`val` || root.`val` == q!!.`val`) 1 else 0
        var left = if(dfs(root.left, p, q)) 1 else 0
        var right = if(dfs(root.right, p, q)) 1 else 0
        if(mid + left + right >= 2) ans = root
        return left + mid + right > 0
    }

    fun lowestCommonAncestor2(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null || root.`val` == p!!.`val` || root.`val` == q!!.`val`) return root
        var left = lowestCommonAncestor2(root.left, p, q)
        var right = lowestCommonAncestor2(root.right, p, q)
        if (left != null && right != null) return root
        else if (left != null) return left
        else return right
    }
}

fun main() {
    val three = TreeNode(3)
    val five = TreeNode(5)
    val one = TreeNode(1)
    val six = TreeNode(6)
    val two = TreeNode(2)
    val zero = TreeNode(0)
    val eight = TreeNode(8)
    val seven = TreeNode(7)
    val four = TreeNode(4)
    three.left = five
    three.right = one
    five.left = six
    five.right = two
    two.left = seven
    two.right = four
    one.left = zero
    one.right = eight

    print(LowestCommonAncestorOfABinaryTree.lowestCommonAncestor2(three, six, zero)?.`val`)
}