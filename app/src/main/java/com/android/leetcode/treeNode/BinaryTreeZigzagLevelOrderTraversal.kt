package com.android.leetcode.treeNode

import java.util.LinkedList

object BinaryTreeZigzagLevelOrderTraversal {

    // time Complexity is O(N)
    // space is O(N)
    fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
        var ans = mutableListOf<List<Int>>()
        if(root == null) return ans
        var queue = LinkedList<TreeNode>()
        queue.offer(root)

        var isLeft2Right = true
        while(queue.isNotEmpty()) {
            var size = queue.size
            var tempArray = mutableListOf<Int>()

            for(i in 0 until size){
                var curr = queue.poll()
                if(isLeft2Right) {
                    tempArray.add(curr.`val`)
                }else {
                    tempArray.add(0, curr.`val`)
                }

                if (curr.left != null) queue.offer(curr.left)
                if (curr.right != null) queue.offer(curr.right)
            }
            ans.add(tempArray.toList())
            isLeft2Right = !isLeft2Right
        }
        return ans
    }
}

fun main() {
    var three = TreeNode(3)
    var nine = TreeNode(9)
    var twenty = TreeNode(20)
    var fifteen = TreeNode(15)
    var seven = TreeNode(7)

    three.left = nine
    three.right = twenty
    twenty.left = fifteen
    twenty.right = seven

    BinaryTreeZigzagLevelOrderTraversal.zigzagLevelOrder(three)
}