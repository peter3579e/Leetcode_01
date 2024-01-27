package com.android.leetcode.treeNode

// the time complexity is O(N)
// spce is O(K) k is number of leaf node
object SumRootToLeafNumber {
    var ans = mutableListOf<Int>()
    fun sumNumbers(root: TreeNode?): Int {
        if(root == null) return 0
        dfs(root, null)
        return ans.sum()
    }

    fun dfs(root: TreeNode?, prev: Int?) {
        if(root == null) return
        var sum = if (prev != null) prev*10 + root.`val` else root.`val`
        dfs(root.left, sum)
        dfs(root.right, sum)
        if(root.left == null && root.right == null) ans.add(sum)
    }
}

fun main () {
    var one = TreeNode(1)
    var two = TreeNode(2)
    var three = TreeNode(3)
    var founr = TreeNode(4)
    var nine = TreeNode(9)
    one.left = two
    one.right = three

    SumRootToLeafNumber.sumNumbers(one)
}