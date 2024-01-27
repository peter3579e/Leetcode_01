package com.android.leetcode.listNode.removeAndDelete

import com.android.leetcode.listNode.CreateLinkedList

object DeleteNodeInALinkedList {

    fun deleteNode(node: CreateLinkedList.ListNode?) {

        var cur = node
        var curNext = cur!!.next
        cur.`val` = curNext!!.`val`
        cur.next = curNext.next
        curNext.next = null
    }
}

fun main() {
    var fourth = CreateLinkedList.ListNode(4)
    var fifth = CreateLinkedList.ListNode(5)
    var one = CreateLinkedList.ListNode(1)
    var nine = CreateLinkedList.ListNode(9)

    fourth.next = fifth
    fifth.next = one
    one.next = nine


    DeleteNodeInALinkedList.deleteNode(one)
    CreateLinkedList.printLinkedList(fourth)
}