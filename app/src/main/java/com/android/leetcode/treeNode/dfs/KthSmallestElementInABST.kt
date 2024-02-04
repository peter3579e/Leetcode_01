package com.android.leetcode.treeNode.dfs

import java.util.PriorityQueue
import java.util.Stack


object KthSmallestElementInABST {
    //my answer
    // time complexity is O(NlogN) + O(klogN)
    // space complexity is O(N)
    var queue = PriorityQueue<Int>()

    fun kthSmallest(root: TreeNode?, k: Int): Int {
        dfs(root)
        var ans = 0
        for(i in 0..k-1) {
            ans = queue.poll() // this would take O(logN) as well
        }

        return ans
    }

    fun dfs(start: TreeNode?) {
        if(start == null) return
        queue.offer(start.`val`)
        dfs(start.left)
        dfs(start.right)
    }

    //Optimize
    //Time complexity : O(N)O(N)O(N) to build a traversal.
    //Space complexity : O(N)O(N)O(N) to keep an inorder traversal.
    fun inorder(root: TreeNode?, arr: ArrayList<Int>): ArrayList<Int> {
        if (root == null) return arr
        inorder(root.left, arr)
        arr.add(root.`val`)
        inorder(root.right, arr)
        return arr
    }

    fun kthSmallest2(root: TreeNode?, k: Int): Int {
        val nums = inorder(root, ArrayList())
        return nums[k - 1]
    }

    // Optimal 2
    fun kthSmallest3(root: TreeNode?, k: Int): Int {
        var root = root
        var k = k
        val stack = Stack<TreeNode>()
        while (true) {
            while (root != null) {
                stack.push(root)
                root = root.left
            }
            root = stack.pop()
            if (--k == 0) return root.`val`
            root = root.right
        }
    }

    // or similar solution to kthSmallest3

    // the time complexity is O(N)
    // the space complexity is O(N) while N represent the height of the tree
    var ans = 0
    var count = 0
    fun kthSmallest4(root: TreeNode?, k: Int): Int {
        count = k
        dfs4(root)
        return ans
    }
    fun dfs4(root: TreeNode?) {
        if(root == null) return
        dfs(root.left)
        if(--count == 0) ans = root.`val`
        dfs(root.right)
    }
}

fun main() {
    var first = TreeNode(1)
    var second = TreeNode(2)
    var third = TreeNode(3)
    var forth = TreeNode(4)
    var fifth = TreeNode(5)
    var sixth = TreeNode(6)
    var seventh = TreeNode(7)

    fifth.left = third
    fifth.right = sixth
    third.left = second
    third.right = forth
    sixth.right = seventh

    print(KthSmallestElementInABST.kthSmallest3(fifth, 5))
}