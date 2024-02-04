package com.android.leetcode.treeNode.dfs


class Node(var `val`: Int) {
    var children: List<Node?> = listOf()
}

object DiameterOfNArrayTree {

    var diameter = 0

    fun diameter(root: Node?): Int {
        dfs(root)
        return diameter
    }

    fun dfs(start: Node?): Int {
        if (start?.children?.isEmpty()!!) return 1
        var max1 = 0
        var max2 = 0
        for (i in start.children) {
            var depth = dfs(i)
            if (depth > max1) {
                max2 = max1
                max1 = depth
            } else if (depth > max2) {
                max2 = depth
            }
        }
        diameter = maxOf(diameter, max1+max2)

        return maxOf(max1,max2) + 1
    }
}

fun main() {
    var first = Node(1)
    var second = Node(2)
    var third = Node(3)
    var forth = Node(4)
    var fifth = Node(5)
    var sixth = Node(6)

    first.children = listOf(third,second,forth)
    third.children = listOf(fifth, sixth)
    print(DiameterOfNArrayTree.diameter(first))
}