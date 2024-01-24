package com.android.leetcode.listNode

object RemoveLinkedListElements {


    fun removeElements(head: CreateLinkedList.ListNode?, `val`: Int): CreateLinkedList.ListNode? {

        var dummyHead = CreateLinkedList.ListNode(0)

        dummyHead.next = head

        var prev = dummyHead
        var cur = dummyHead.next

        while(cur != null) {
            if (cur.`val` == `val`) {
                prev.next = cur.next
            }else {
                prev = cur
            }
            cur = cur.next
        }

        return dummyHead.next
    }
}

fun main() {
    val inputArray = intArrayOf(1,2,6,3,4,5,6)

    // Create linked list from array
    val linkedList = CreateLinkedList.createLinkedList(inputArray)

    CreateLinkedList.printLinkedList(RemoveLinkedListElements.removeElements(linkedList, 6))
}