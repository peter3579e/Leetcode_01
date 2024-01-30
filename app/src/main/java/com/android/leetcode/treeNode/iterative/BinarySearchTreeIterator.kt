package com.android.leetcode.treeNode.iterative

import com.android.leetcode.treeNode.TreeNode
import java.util.Stack

class BinarySearchTreeIterator(root: TreeNode?) {

    var stack = Stack<Int>()

    init {
        dfs(root)
    }

    fun next(): Int {
        return stack.pop()
    }

    fun hasNext(): Boolean {
        return stack.isEmpty()
    }

    private fun dfs(root: TreeNode?) {
        if(root == null) return
        dfs(root.left)
        stack.push(root.`val`)
        dfs(root.right)
    }
}

class BinarySearchTreeIteratorIterative(root: TreeNode?) {

    var stack = Stack<TreeNode>()
    var cur = root

    init {
        while(cur != null) {
            while(cur != null) {
                stack.push(cur)
                cur = cur?.left
            }
        }
    }

    fun next(): Int {
        var cur = stack.pop()
        var newCur = cur.right
        while (newCur != null) {
            stack.push(newCur)
            newCur = newCur.left
        }
        return cur.`val`
    }

    fun hasNext(): Boolean {
        return stack.isNotEmpty()
    }
}

fun main() {
    var seven = TreeNode(7)
    var third = TreeNode(3)
    var fifteen = TreeNode(15)
    var nine = TreeNode(9)
    var twenty = TreeNode(20)

    seven.left = third
    seven.right = fifteen
    fifteen.left = nine
    fifteen.right = twenty

    var binary = BinarySearchTreeIteratorIterative(seven)
    print(binary.next())
    binary.next()
}