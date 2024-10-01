package com.android.leetcode.listNode

object RemoveDuplicatesFromAnUnsortedLinkedList {
    fun deleteDuplicatesUnsorted(head: ListNode?): ListNode? {
        if(head == null) return null
        val map = hashMapOf<Int,Pair<ListNode,ListNode?>>()
        val duplicated = mutableSetOf<Int>()
        val dummyHead = ListNode(0)
        dummyHead.next = head
        var prev = dummyHead
        var cur = dummyHead.next

        while(cur != null) {
            if(!map.contains(cur.`val`) && !duplicated.contains(cur.`val`)) {
                map[cur.`val`] = Pair(prev,cur.next)
            } else if(map.contains(cur.`val`) && !duplicated.contains(cur.`val`)) {
                prev.next = cur.next
                duplicated.add(cur.`val`)
                var (nodePrev,nodeNext) = map[cur.`val`]!!
                if (nodeNext == cur) nodeNext = cur.next
                nodePrev.next = nodeNext
            } else if(map.contains(cur.`val`) && duplicated.contains(cur.`val`)) {
                prev.next = cur.next
            }
            cur = cur.next
            prev.next?.let {
                prev = it
            }
        }

        return dummyHead.next
    }

    fun deleteDuplicatesUnsorted2(head: ListNode?): ListNode? {
        if(head == null) return null
        var cur = head
        val map = hashMapOf<Int,Int>()
        val duplicated = mutableSetOf<Int>()
        while(cur != null) {
            map[cur.`val`] = map.getOrDefault(cur.`val`, 0) + 1
            if(map[cur.`val`]!! > 1) duplicated.add(cur.`val`)
            cur = cur.next
        }

        val dummyHead = ListNode(0)
        dummyHead.next = head
        cur = head
        var prev = dummyHead

        while(cur != null) {
            if(duplicated.contains(cur.`val`)) {
                prev.next = cur.next
            } else {
                prev.next?.let {
                    prev = it
                }
            }
            cur = cur.next
        }

        return dummyHead.next
    }
}

fun main() {
    val one = ListNode(2)
    val two = ListNode(1)
    val three = ListNode(1)
    val two2 = ListNode(2)
    one.next = two
    two.next = three
    three.next = two2
    RemoveDuplicatesFromAnUnsortedLinkedList.deleteDuplicatesUnsorted2(one)
}