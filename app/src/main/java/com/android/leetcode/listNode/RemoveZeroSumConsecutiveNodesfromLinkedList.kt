package com.android.leetcode.listNode

object RemoveZeroSumConsecutiveNodesfromLinkedList {
    //failed
    fun removeZeroSumSublists(head: ListNode?): ListNode? {

        val dummyHead = ListNode(0)
        dummyHead.next = head
        val sumToNode = hashMapOf<Int,ListNode>(0 to dummyHead)
        var cur = head
        var prefixSum = 0

        while(cur != null) {
            prefixSum = prefixSum + cur.`val`
            if(sumToNode.contains(prefixSum)) {
                sumToNode[prefixSum]!!.next = cur.next
            } else {
                sumToNode[prefixSum] = cur
            }
            cur = cur.next
        }

        return dummyHead.next
    }
    //sucess
    fun removeZeroSumSublists2(head: ListNode?): ListNode? {
        val dummyHead = ListNode(0)
        dummyHead.next = head
        val sumToNode = hashMapOf<Int, ListNode>()
        var cur: ListNode? = dummyHead
        var prefixSum = 0

        while (cur != null) {
            prefixSum += cur.`val`

            if (sumToNode.containsKey(prefixSum)) {
                // Remove nodes from sumToNode[prefixSum]!!.next until cur
                var nodeToRemove = sumToNode[prefixSum]!!.next
                var removeSum = prefixSum + nodeToRemove!!.`val`
                while (nodeToRemove != cur) {
                    sumToNode.remove(removeSum)
                    nodeToRemove = nodeToRemove!!.next
                    removeSum += nodeToRemove!!.`val`
                }
                // Update next pointer of the previous node to skip the zero-sum sublist
                sumToNode[prefixSum]!!.next = cur.next
            } else {
                // Store prefix sum and its corresponding node
                sumToNode[prefixSum] = cur
            }
            cur = cur.next
        }

        return dummyHead.next
    }
}
fun arrayToListNode(nums: IntArray): ListNode? {
    if (nums.isEmpty()) return null

    var head: ListNode? = ListNode(nums[0])
    var current: ListNode? = head

    for (i in 1 until nums.size) {
        current?.next = ListNode(nums[i])
        current = current?.next
    }

    return head
}

fun main() {
    val nums = intArrayOf(1, 3, 2, -3, -2, 5, 5, -5, 1)
    val head = arrayToListNode(nums)
    RemoveZeroSumConsecutiveNodesfromLinkedList.removeZeroSumSublists(head)

}