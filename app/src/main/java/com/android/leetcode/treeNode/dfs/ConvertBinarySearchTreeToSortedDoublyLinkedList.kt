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

    class Node(var `val`: Int) {
        var left: Node? = null
        var right: Node? = null
    }

    var dummyHead = Node(0)
    var cur = dummyHead
    var count = 0
    var head = Node(0)
    fun treeToDoublyList(root:Node?): Node? {
        if(root == null) return null
        if(count == 0) {
            head = root
            count++
        }

        treeToDoublyList(root.left)
        cur.right = root
        cur.right!!.left = cur
        cur = cur.right!!
        treeToDoublyList(root.right)

        if(root == head) {
            dummyHead.right!!.left = cur
            cur.right = dummyHead.right
        }



        return dummyHead.right
    }

    val four = Node(4)
    val two = Node(2)
    val five = Node(5)
    val one = Node(1)
    val three = Node(3)
    four.left = two
    four.right = five
    two.left = one
    two.right = three
    var node = treeToDoublyList(four)

    while (node != null) {
        println(node.`val`)
        node = node.right
    }
}