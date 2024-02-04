package com.android.leetcode.listNode

object ReverseLinkedList {
    fun reverseList(head: ListNode?): ListNode? {

        var cur: ListNode? = head
        var prev: ListNode? = null

        while(cur != null) {
            var tempNext: ListNode? = cur.next
            cur.next = prev
            prev = cur
            cur = tempNext
        }

        return prev
    }
}