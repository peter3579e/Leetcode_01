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


    fun reorderList2(head: ListNode?): Unit {
        var cur = head
        fun recursion(node: ListNode?): Boolean {
            if(node == null) {
                return false
            }

            if(recursion(node.next)) return true

            if(node == cur) return true

            val next = cur!!.next
            cur!!.next = node
            cur!!.next!!.next = next
            cur = next
            return false
        }
        recursion(head)
        cur!!.next = null
    }
}

fun main() {
    val one = ListNode(1)
    val two = ListNode(2)
    val three = ListNode(3)
    val four = ListNode(4)
    val five = ListNode(5)
    one.next = two
    two.next = three
    three.next = four
    four.next = five
    ReorderList.reorderList(one)
}