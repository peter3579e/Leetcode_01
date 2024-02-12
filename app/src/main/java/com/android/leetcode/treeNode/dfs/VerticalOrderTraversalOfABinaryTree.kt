package com.android.leetcode.treeNode.dfs


object VerticalOrderTraversalOfABinaryTree {

    var list = mutableListOf<TripLet>()
    data class TripLet(
        val row: Int,
        var column: Int,
        var value: Int
    )


    fun verticalTraversal(root: TreeNode?): List<List<Int>> {
        dfs(root, intArrayOf(0,0)) // O(N)
        list.sortWith { t1: TripLet, t2: TripLet ->
            if (t1.column == t2.column && t1.row == t2.row) {
                t1.value.compareTo(t2.value)
            } else if (t1.column == t2.column && t1.row != t2.row) {
                t1.row.compareTo(t2.row)
            } else {
                t1.column.compareTo(t2.column)
            }
        }

        var ans = mutableListOf<List<Int>>()

        var currColumn = mutableListOf<Int>()
        var currColumnIndex: Int = list.get(0).column

        for (triplet in list) {
            if (triplet.column == currColumnIndex) {
                currColumn.add(triplet.value)
            }else {
                currColumnIndex = triplet.column
                ans.add(currColumn)
                currColumn = mutableListOf()
                currColumn.add(triplet.value)
            }
        }
        ans.add(currColumn)


        return ans
    }

    fun dfs(start: TreeNode?, cdt: IntArray) {
        if(start == null) return
        var x = cdt[1]
        var y = cdt[0]
        list.add(TripLet(y,x,start.`val`))
        dfs(start.left, intArrayOf(y+1,x-1))
        dfs(start.right, intArrayOf(y+1,x+1))
    }
}

fun main() {
    val root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.left!!.left = TreeNode(4)
    root.left!!.right = TreeNode(5)
    root.right!!.left = TreeNode(6)
    root.right!!.right = TreeNode(7)

    println(VerticalOrderTraversalOfABinaryTree.verticalTraversal(root).toList())
}
