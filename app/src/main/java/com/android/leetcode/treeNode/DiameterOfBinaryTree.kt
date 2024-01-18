package com.android.leetcode.treeNode
class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
//543
object DiameterOfBinaryTree {

    var diameter = 0
    fun diameterOfBinaryTree(root: TreeNode?): Int {
        dfs(root)
        return diameter
    }

    fun dfs(start: TreeNode?): Int {
        if (start == null)  return  0
        var leftPath = dfs(start.left)
        var rightPath = dfs(start.right)
        diameter = maxOf(diameter, leftPath+rightPath)
        return maxOf(leftPath,rightPath)  + 1
    }
}

fun main () {
    var first = TreeNode(1)
    var second = TreeNode(2)
    var third = TreeNode(3)
    var fourth = TreeNode(4)
    var fifth = TreeNode(5)

    first.left = second
    first.right = third
    second.left = fourth
    second.right = fifth

    print(DiameterOfBinaryTree.diameterOfBinaryTree(first))
//    print(DiameterOfBinaryTree.array)

}