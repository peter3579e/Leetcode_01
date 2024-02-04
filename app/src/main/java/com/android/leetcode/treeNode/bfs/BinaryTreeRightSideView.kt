package com.android.leetcode.treeNode.bfs

import com.android.leetcode.treeNode.dfs.TreeNode
import java.util.LinkedList
import java.util.Queue

object BinaryTreeRightSideView {

    fun rightSideView(root: TreeNode?): List<Int> {

        if (root == null) return listOf()

        var queue: Queue<TreeNode> = LinkedList<TreeNode>()
        var list = mutableListOf<MutableList<Int>>()
        queue.offer(root)

        while (queue.isNotEmpty()) {

            var size = queue.size
            var temp = mutableListOf<Int>()

            for(i in 0 until size) {
                var curNode = queue.poll()
                temp.add(curNode.`val`)
                if(curNode.left != null) queue.offer(curNode.left)
                if(curNode.right != null) queue.offer(curNode.right)
            }

            list.add(temp)
        }

        var ans = mutableListOf<Int>()
        for(i in list) {
            ans.add(i[i.size-1])
        }

        return ans
    }

    //Optimized
    fun rightSideView2(root: TreeNode?): List<Int> {
        if (root == null) return listOf()

        var queue: Queue<TreeNode> = LinkedList<TreeNode>()
        var list = mutableListOf<Int>()
        queue.offer(root)

        while (queue.isNotEmpty()) {

            var size = queue.size

            for(i in 0 until size) {
                var curNode = queue.poll()
                if(i == 0) list.add(curNode.`val`)
                if(curNode.right != null) queue.offer(curNode.right)
                if(curNode.left != null) queue.offer(curNode.left)
            }

        }

        return list
    }
}

fun main() {
    var one = TreeNode(1)
    var two = TreeNode(2)
    var three = TreeNode(3)
    var four = TreeNode(4)
    var five = TreeNode(5)

    one.left = two
    one.right = three


    BinaryTreeRightSideView.rightSideView(one)

}