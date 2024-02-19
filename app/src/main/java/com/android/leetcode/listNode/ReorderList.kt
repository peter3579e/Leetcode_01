package com.android.leetcode.listNode

object ReorderList {
    var dummyHead: ListNode? = ListNode(0)
    var cur: ListNode? = dummyHead
    fun reorderList(head: ListNode?): Unit {
        cur!!.next = head
        cur = cur!!.next
        recursion(head)
    }

    fun recursion(start:ListNode?): Boolean {
        if(start == null) return false
        if(recursion(start.next)) return true
        var temp = cur!!.next
        cur!!.next = start
        start.next = temp
        cur = temp
        if(cur!!.next == start) {
            cur!!.next = null
            return true
        }
        return false
    }
}