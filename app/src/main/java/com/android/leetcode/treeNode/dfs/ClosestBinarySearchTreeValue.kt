package com.android.leetcode.treeNode.dfs

object ClosestBinarySearchTreeValue {
    var dif = Double.MAX_VALUE
    var node = 0
    fun closestValue(root: TreeNode?, target: Double): Int {
        if(root == null) return 0
        val difference = Math.abs(root.`val`.toDouble() - target)
        if(difference <= dif) {
            dif = difference
            node = root.`val`
        }

        closestValue(root.left, target)
        closestValue(root.right, target)
        return node
    }

    fun closestValue2(root: TreeNode?, target: Double): Int {
        var closest = root?.`val` ?: -1
        var closestGap = Double.MAX_VALUE
        fun dfs(root: TreeNode?, target: Double) {
            if(root == null) return
            val dif = Math.abs(root.`val`.toDouble() - target)
            if(dif < closestGap) {
                closest = root.`val`
                closestGap = dif
            }

            if(root.`val` > target) {
                dfs(root.left, target)
            } else {
                dfs(root.right, target)
            }
        }
        dfs(root, target)
        return closest
    }
}

fun main() {
    var set = mutableSetOf<Int>()
    set.remove(2)
    val four = TreeNode(4)
    val two = TreeNode(2)
    val five = TreeNode(5)
    val one = TreeNode(1)
    val thr = TreeNode(3)
//    four.left = two
//    four.right = five
//    two.left = one
//    two.right = thr

    one.right = two
    ClosestBinarySearchTreeValue.closestValue2(one,3.428571)
}