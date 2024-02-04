package com.android.leetcode.listNode

object OddEvenLinkedList {
    fun oddEvenList(head: ListNode?): ListNode? {
        if(head == null) return null

        var dummyHead = ListNode(0)
        dummyHead.next = head
        var oddPointer: ListNode? = head
        var evenPointer: ListNode? = head!!.next
        var dummyEven = ListNode(0)
        dummyEven.next = evenPointer

        while(oddPointer != null && oddPointer.next != null && evenPointer != null && evenPointer.next != null) {

            oddPointer.next = oddPointer.next!!.next
            evenPointer.next = evenPointer.next!!.next
            oddPointer = oddPointer.next
            evenPointer = evenPointer.next
        }

        oddPointer!!.next = dummyEven.next

        return dummyHead.next
    }
}