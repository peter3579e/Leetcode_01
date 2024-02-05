package com.android.leetcode.listNode.rotate

import com.android.leetcode.listNode.CreateLinkedList
import com.android.leetcode.listNode.ListNode

object RotateList {
    // faile
    fun rotateRight1(head: CreateLinkedList.ListNode?, k: Int): CreateLinkedList.ListNode? {
        var map = hashMapOf<Int, CreateLinkedList.ListNode>()
        var cur = head
        var count = 0
        while(cur != null) {
            map[count] = cur
            cur = cur.next
            count ++
        }
        var size = map.size

        if(size == k) return head
        var rotate = if(k >= size)  k % size else  k

        var newHead = map[size-rotate]
        map[size-rotate-1]?.next = null
        var newCur = newHead
        var newCount = 0
        while(newCount < rotate){
            if(newCur?.next == null) {
                newCur?.next = head
            }
            newCur = newCur?.next
            newCount ++
        }
        return newHead
    }


    //fail
    fun rotateRight2(head: CreateLinkedList.ListNode?, k: Int): CreateLinkedList.ListNode? {
        if(head == null) return null
        if(head.next == null) return head

        var size = 1
        var cur = head
        while(cur?.next != null) {
            cur = cur.next
            size++
        }

        var tail = cur
        tail?.next = head

        if(size == k) return head
        var rotate = if(k > size) size - k else k
        var newHead: CreateLinkedList.ListNode = CreateLinkedList.ListNode(0)
        var newTail: CreateLinkedList.ListNode = CreateLinkedList.ListNode(0)
        var newCur = head

        for(i in 0..rotate) {
            if(i == rotate-1) newTail = newCur!!
            if(i == rotate) newHead = newCur!!
            newCur = newCur!!.next
        }

        newTail.next = null

        return newHead
    }

    fun rotateRight(head: CreateLinkedList.ListNode?, k: Int): CreateLinkedList.ListNode? {
        // base cases
        if (head == null) return null
        if (head.next == null) return head

        // close the linked list into the ring
        var oldTail = head
        var n = 1
        while (oldTail!!.next != null) {
            n++
            oldTail = oldTail.next
        }
        oldTail.next = head

        // find new tail: (n - k % n - 1)th node
        // and new head: (n - k % n)th node
        var rotate = if (k >= n) k % n else k
        var newTail = head
        for (i in 0 until n - rotate - 1) {
            newTail = newTail?.next
        }
        val newHead = newTail?.next

        // break the ring
        newTail?.next = null

        return newHead
    }
}

class Solution {
    fun rotateRight(head: ListNode?, k: Int): ListNode? {
        if(head == null) return null
        if(head.next == null) return head

        var dummyHead = ListNode(0)
        dummyHead.next = head
        var cur: ListNode? = head

        var size = 1

        while(cur!= null && cur.next != null) {
            size ++
            cur = cur.next
        }

        var rotate = k%size

        if(rotate == 0) return head

        var newCur = head

        for(i in 0 until size - rotate - 1) {
            newCur = newCur!!.next
        }
        var temp = newCur!!.next
        newCur!!.next = null
        cur!!.next = head
        dummyHead.next = temp

        return dummyHead.next
    }
}

fun main() {
    var solution = Solution()
    var zero = ListNode(0)
    var first = ListNode(1)
    var second = ListNode(2)

    zero.next = first
    first.next = second

    solution.rotateRight(zero, 4)
}