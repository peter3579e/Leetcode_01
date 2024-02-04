package com.android.leetcode.listNode

// time complexity is O(N)
// space complexity is O(1)
object LinkedListCycleII {
    fun detectCycle(head: ListNode?): ListNode? {
        var slow: ListNode? = head
        var fast: ListNode? = head

        while(fast != null && fast.next != null) {
            slow = slow!!.next
            fast = fast.next!!.next
            if(slow == fast) break
        }

        if(fast == null || fast.next == null) return null

        fast = head

        while(fast != slow) {
            fast = fast!!.next
            slow = slow!!.next

        }

        return slow
    }
}