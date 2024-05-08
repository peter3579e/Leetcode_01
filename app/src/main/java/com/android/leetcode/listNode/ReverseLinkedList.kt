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

    var lastNode = ListNode(0)

    fun reverseList2(head: ListNode?): ListNode? {
        if(head == null) return head

        findNext(head)!!.next = null
        return lastNode
    }

    fun findNext(node: ListNode?): ListNode? {
        if(node!!.next == null) {
            lastNode = node
            return node
        }

        reverseList(node.next)!!.next = node
        return node
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
    ReverseLinkedList.reverseList2(one)
}