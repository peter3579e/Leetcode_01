package com.android.leetcode.treeNode.dfs

object FindDistanceInABinaryTree {
    fun findDistance(root: TreeNode?, p: Int, q: Int): Int {
        if(p == q || root == null) return 0

        val adj = hashMapOf<Int, List<Int?>>()

        fun dfs(parent: TreeNode?, node: TreeNode?) {
            if(node == null) return

            var left: Int? = node.left?.`val` ?: null
            var right: Int? = node.right?.`val` ?: null
            adj[node.`val`] = listOf(parent?.`val` ?: null ,left,right)
            dfs(node,node.left)
            dfs(node,node.right)
        }

        dfs(null,root)
        var seen = mutableSetOf<Int>()
        var ans = 0
        fun dfsSearch(node: Int, count: Int): Boolean {
            if(seen.contains(node)) return false
            seen.add(node)
            if(node == q) {
                ans = count
                return true
            }

            for(newNode in adj[node] ?: listOf()) {
                newNode?.let {
                    if(dfsSearch(it,count+1)) return true
                }
            }

            return false
        }

        dfsSearch(p,0)

        return ans
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
    one.left = zero
    one.right = eight
    two.left = seven
    two.right = four
//    FindDistanceInABinaryTree.findDistance(three,5,0)

    print(0%3)
}