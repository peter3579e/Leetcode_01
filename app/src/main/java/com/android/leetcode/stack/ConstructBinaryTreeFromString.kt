package com.android.leetcode.stack

import java.util.Stack

object ConstructBinaryTreeFromString {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun str2tree(s: String): TreeNode? {


        fun dfs(index: Int): Pair<TreeNode?, Int> {
            var i = index
            var num = 0
            var sign = 1

            if (s[i].isDigit() || s[i] == '-') {
                if (s[i] == '-') sign = -1 else num = s[i].digitToInt()
                while (i + 1 < s.length && s[i + 1].isDigit()) {
                    i++
                    num = num * 10 + s[i].digitToInt()
                }
                i++
            }
            val node = TreeNode(num * sign)
            var returnNode: Pair<TreeNode?, Int>
            if (s[i] == '(') {
                returnNode = dfs(i + 1)
                node.left = returnNode.first
                i = returnNode.second + 1
            }

            if (node.left != null && s[i] == '(') {
                returnNode = dfs(i + 1)
                node.right = returnNode.first
                i = returnNode.second + 1
            }

            return Pair(node, i)
        }

        return dfs(0).first
    }
}

fun main() {
    print(ConstructBinaryTreeFromString.str2tree("-4(2(3)(1))(6(5)(7))"))
}