package com.android.leetcode.listNode

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

object RemoveNthNodeFromEndOfList {
    // my solution
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {

        var cur: ListNode? = head
        var size = 0

        while(cur != null) {
            cur = cur.next
            size ++
        }

        var dummyHead = ListNode(n)
        dummyHead.next = head
        var prev: ListNode? = dummyHead
        cur = head

        for(i in 0 until size-n) {
            prev = cur
            cur = cur?.next
        }

        prev?.next = cur?.next


        return dummyHead.next
    }


    // Optiomal
    // time complexity O(N)
    // space complexity is O(1)

    fun removeNthFromEnd2(head: ListNode?, n: Int): ListNode? {
        if(head!!.next == null) return null
        var dummyHead = ListNode(0)
        dummyHead.next = head
        var firstPointer: ListNode? = dummyHead
        var secondPointer: ListNode? = dummyHead

        for(i in 0..n) {
            firstPointer = firstPointer!!.next
        }

        while(firstPointer != null) {
            firstPointer = firstPointer.next
            secondPointer = secondPointer!!.next
        }

        secondPointer!!.next = secondPointer!!.next?.next

        return dummyHead.next
    }
}

fun main() {
    var one = ListNode(1)
    var two = ListNode(2)
    var three = ListNode(3)
    var four = ListNode(4)
    var five = ListNode(5)
    one.next = two
    two.next = three
    three.next = four
    four.next = five
    RemoveNthNodeFromEndOfList.removeNthFromEnd(one, 2)
}