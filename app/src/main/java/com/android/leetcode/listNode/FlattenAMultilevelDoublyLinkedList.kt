package com.android.leetcode.listNode


object FlattenAMultilevelDoublyLinkedList {

    class Node(var `val`: Int) {
        var prev: Node? = null
        var next: Node? = null
        var child: Node? = null
    }

    var cur: Node? = null

    fun flatten(root: Node?): Node? {

        if (root == null) return null

        var dummyHead = Node(0)
        dummyHead.next = root
        cur = dummyHead
        dfs(root)

        dummyHead.next?.prev = null

        return dummyHead.next
    }

    fun dfs(root: Node?) {
        if (root == null) return
        cur!!.next = root
        root.prev = cur
        cur = cur!!.next
        var tempNext: Node? = root.next
        if (root.child != null) {
            dfs(root.child)
            root.child = null
        }
        if (tempNext != null) dfs(tempNext)
    }
}