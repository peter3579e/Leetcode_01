package com.android.leetcode.listNode

// time complexity is O(NlogN)
// Space complexity is O(N)
object SortList {

    fun sortList(head: ListNode?): ListNode? {
        if (head == null || head.next == null) return head
        val mid = findMid(head)
        val rightStart = mid.next
        mid.next = null
        val sortedLeft = sortList(head)
        val sortedRight = sortList(rightStart)
        return merge(sortedLeft, sortedRight)
    }

    private fun findMid(node: ListNode?): ListNode {
        var slow = node
        var fast = node!!.next
        while (fast != null && fast.next != null) {
            slow = slow!!.next
            fast = fast!!.next?.next
        }
        return slow!!
    }

    private fun merge(head1: ListNode?, head2: ListNode?): ListNode? {
        val dummy = ListNode(0)
        var current: ListNode? = dummy
        var head11 = head1
        var head22 = head2
        while (head11 != null && head22 != null) {
            if (head11.`val` < head22.`val`) {
                current!!.next = head11
                head11 = head11.next
            } else {
                current!!.next = head22
                head22 = head22.next
            }
            current = current.next
        }
        current!!.next = head11 ?: head22
        return dummy.next
    }
}

fun main() {
    var four = ListNode(4)
    var two = ListNode(2)
    var one = ListNode(1)
    var three = ListNode(3)
    four.next = two
    two.next = one
    one.next = three
    SortList.sortList(four)
}