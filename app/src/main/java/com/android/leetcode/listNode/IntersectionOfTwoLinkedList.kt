package com.android.leetcode.listNode

// time complexity is O(n)
// space complexity is O(1)
object IntersectionOfTwoLinkedList {

    fun getIntersectionNode(headA:ListNode?, headB:ListNode?):ListNode? {
        if(headA == null || headB == null) return null

        var map = hashMapOf<Int, ListNode>()
        var curA: ListNode? = headA

        while(curA != null) {
            map[curA.`val`] = curA
            curA = curA.next
        }

        var curB = headB

        while(curB != null) {
            if(map.containsValue(curB)) return curB
            curB = curB.next
        }

        return null
    }

    // Optimized
    fun getIntersectionNode2(headA:ListNode?, headB:ListNode?):ListNode? {
        if(headA == null || headB == null) return null

        var apointer:ListNode? = headA
        var bpointer:ListNode? = headB

        while(apointer != bpointer) {
            if (apointer == null){
                apointer = headB
            }else{
                apointer = apointer.next
            }

            if (bpointer == null){
                bpointer = headA
            }else{
                bpointer = bpointer.next
            }
        }

        return apointer
    }
}