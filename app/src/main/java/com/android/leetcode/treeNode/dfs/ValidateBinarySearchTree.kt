package com.android.leetcode.treeNode.dfs

object ValidateBinarySearchTree {
    var ans = true
    var prev: Int? = null

    fun isValidBST(root: TreeNode?): Boolean {
        dfs(root)
        return ans
    }

    fun dfs(start: TreeNode?): TreeNode? {

        if(start == null) return null

        val left = dfs(start.left)

        val right = dfs(start.right)

        if(left != null && left.`val` > start.`val` || right != null && start.`val` > right.`val`) ans = false

        return start
    }

// time O(N) Space O(N)
    fun isValidBST2(root: TreeNode?): Boolean {
        return dfs2(root, null, null)
    }

    fun dfs2(start: TreeNode?, low: Int?, hi: Int?): Boolean {
        if(start == null) return true
        if((low != null && start.`val` <= low) || (hi != null && hi <= start.`val`)){
            return false
        }
        return dfs2(start.left, low, start.`val`) && dfs2(start.right, start.`val`, hi)
    }

    // inorder time O(N) Space O(N)

    fun isValidBST3(root: TreeNode?): Boolean {
        return ifValidNode(root)
    }

    fun ifValidNode(root: TreeNode?): Boolean {
        if (root == null) return true
        if(!ifValidNode(root.left)) return false
        if (prev != null && root.`val` <= prev!!) return false
        prev = root.`val`
        return ifValidNode(root.right)
    }
}

fun main() {
    var fifth = TreeNode(5)
    var two = TreeNode(2)
    var one = TreeNode(1)
    var three = TreeNode(3)
    var four = TreeNode(4)
    var six = TreeNode(6)

    fifth.left = one
    fifth.right = four
    four.left = three
    four.right = six

    print(ValidateBinarySearchTree.isValidBST3(fifth))

}