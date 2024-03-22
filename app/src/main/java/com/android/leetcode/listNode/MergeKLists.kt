package com.android.leetcode.listNode

import java.util.PriorityQueue

object MergeKLists {
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        val pq = PriorityQueue<ListNode>() { a, b -> a.`val` - b.`val` }
        lists.filter { it != null }.forEach { pq.add(it) }
        val res = ListNode(-1)
        var tail = res
        while (pq.isNotEmpty()) {
            val head = pq.poll()
            tail.next = head
            tail = tail.next!!
            if (head.next != null)
                pq.add(head.next)
        }
        return res.next
    }


    fun mergeKListsOptimal(lists: Array<ListNode?>): ListNode? {
        var newList: MutableList<ListNode?> = lists.toMutableList()
        var size = newList.size


        while(size > 1) {
            val mergerdList: MutableList<ListNode?> = mutableListOf()
            for (i in 0 until newList.size step 2) {
                val l1: ListNode? = newList[i]
                val l2: ListNode? = if(i+1 < newList.size) newList[i+1] else null
                mergerdList.add(merge2List(l1,l2))
            }
            newList = mergerdList
            size = newList.size
        }

        return newList.firstOrNull()
    }

    private fun merge2List(l1: ListNode?, l2: ListNode?): ListNode? {
        val dummy = ListNode(0)
        var cur: ListNode? = dummy
        var list1: ListNode? = l1
        var list2: ListNode? = l2

        while(list1 != null && list2 != null) {
            if(list1.`val` > list2.`val`) {
                cur!!.next = list2
                list2 = list2.next
            } else {
                cur!!.next = list1
                list1 = list1.next
            }
            cur = cur!!.next
        }

        if(list1 != null) {
            cur!!.next = list1
        }

        if(list2 != null) {
            cur!!.next = list2
        }

        return dummy.next
    }

    fun mergeKListsMostOptimal(lists: Array<ListNode?>): ListNode? {
        if (lists.isEmpty()) return null

        var interval = 1
        while (interval < lists.size) {
            for (i in 0 until lists.size - interval step interval * 2) {
                lists[i] = mergeSort(lists[i], lists[i + interval])
            }
            interval *= 2
        }

        return lists[0]
    }

    private fun mergeSort(l1: ListNode?, l2: ListNode?): ListNode? {

        var cur1 = l1
        var cur2 = l2
        val dummy = ListNode(0)
        var pointer = dummy

        while(cur1 != null && cur2 != null) {
            if(cur1.`val` > cur2.`val`) {
                pointer.next = cur2
                cur2 = cur2.next
            } else {
                pointer.next = cur1
                cur1 = cur1.next
            }
            pointer = pointer!!.next!!
        }

        if(cur1 != null) {
            pointer.next = cur1
        }

        if(cur2 != null) {
            pointer.next = cur2
        }

        return dummy.next
    }
}

fun main() {
    val one = ListNode(1)
    val four = ListNode(4)
    val five = ListNode(5)
    val one1 = ListNode(1)
    val three = ListNode(3)
    val four2 = ListNode(4)
    val two = ListNode(2)
    val six = ListNode(6)
    one.next = four
    four.next = five
    one1.next = three
    three.next = four2
    two.next = six

    MergeKLists.mergeKListsMostOptimal(arrayOf(one,one1,two))
}