package com.android.leetcode.treeNode.dfs



object ConvertBinarySearchTreeToSortedDoublyLinkedList {

    class Node(var `val`: Int) {
        var left: Node? = null
        var right: Node? = null
    }

    val dummyHead = Node(0)
    var cur = dummyHead
    fun treeToDoublyList(root:Node?): Node? {


        fun dfs(node: Node?) {
            if(node == null) return

            dfs(node.left)
            cur.right = node
            node.left = cur
            cur = cur.right!!
            dfs(node.right)
        }

        dfs(root)

        dummyHead.right?.left = cur
        cur.right = dummyHead.right

        return dummyHead.right
    }
}

fun main() {
    val four = ConvertBinarySearchTreeToSortedDoublyLinkedList.Node(4)
    val two = ConvertBinarySearchTreeToSortedDoublyLinkedList.Node(2)
    val five = ConvertBinarySearchTreeToSortedDoublyLinkedList.Node(5)
    val one = ConvertBinarySearchTreeToSortedDoublyLinkedList.Node(1)
    val three = ConvertBinarySearchTreeToSortedDoublyLinkedList.Node(3)
    four.left = two
    four.right = five
    two.left = one
    two.right = three
    var node: ConvertBinarySearchTreeToSortedDoublyLinkedList.Node? = ConvertBinarySearchTreeToSortedDoublyLinkedList.treeToDoublyList(four)

    while (node != null) {
        println(node.`val`)
        node = node.right
    }
}