package com.android.leetcode.treeNode.dfs

// the time complexity is O(N)
// spce is O(1)
object SumRootToLeafNumber {
    fun sumNumbers(root: TreeNode?): Int {
        if(root == null) return 0
        return dfs(root, null)
    }

    fun dfs(root: TreeNode?, prev: Int?): Int {
        if(root == null) return 0
        var sum = if (prev != null) prev*10 + root.`val` else root.`val`
        if(root.left == null && root.right == null) return sum
        return dfs(root.left, sum) + dfs(root.right, sum)
    }
}

// the time complexity is O(N)
// spce is O(K) k is number of leaf node
object SumRootToLeafNumberMySolution {
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

    print(SumRootToLeafNumber.sumNumbers(one))
}