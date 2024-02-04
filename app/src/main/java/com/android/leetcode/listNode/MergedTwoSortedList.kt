package com.android.leetcode.listNode

object MergedTwoSortedList {
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {

        var dummyHead = ListNode(0)
        var cur = dummyHead
        var pointer1: ListNode? = list1
        var pointer2: ListNode? = list2

        while(pointer1 != null && pointer2 != null) {

            if(pointer1.`val` >= pointer2.`val`) {
                cur.next = pointer2
                pointer2 = pointer2.next
                cur = cur.next!!
            }else {
                cur.next = pointer1
                pointer1 = pointer1.next
                cur = cur.next!!
            }
        }

        if(pointer1 != null) {
            cur.next = pointer1
        } else if(pointer2 != null) {
            cur.next = pointer2
        }

        return dummyHead.next
    }
}