package com.android.leetcode.listNode

import kotlin.math.sign

object DoublyLinkedList {

    var dummyHead: Node? = null
    var dummyTail: Node? = null
    var size = 0

    init {
        dummyHead?.next = dummyTail
        dummyTail?.prev = dummyHead
    }

    fun get(index: Int): Int {
        if (index < 0 || index > size) return -1
        var cur = dummyHead
        var count = 0
        while (count < index) {
            cur = cur?.next
        }
        return cur?.data ?: -1
    }

    fun addAtHead(`val`: Int) {
        var node = Node(`val`)
        if (dummyHead == null) {
            dummyHead = null
            dummyTail = null
        }else {
            dummyHead?.next = node
        }
    }

    fun addAtTail(`val`: Int) {

    }

    fun addAtIndex(index: Int, `val`: Int) {

    }

    fun deleteAtIndex(index: Int) {

    }


    class Node(val data: Int) {
        var prev: Node? = null
        var next: Node? = null
    }
}

fun main() {
    with(DoublyLinkedList) {
        addAtHead(1)
        addAtTail(3)
        addAtIndex(1,2)
        get(1)
        deleteAtIndex(1)
        get(1)
    }
}