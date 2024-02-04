package com.android.leetcode.listNode

object AddTwoNumber {

    // My solution
    // Time complexity: O(N+M)
    // SpaceComplexity: O(1)
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {

        var dummyHead = ListNode(0)
        var cur = dummyHead
        var plusOne = false
        var pointer1: ListNode? = l1
        var pointer2: ListNode? = l2

        while(pointer1 != null && pointer2 != null) {
            var sum = if(plusOne) pointer1.`val`+ pointer2.`val` + 1 else pointer1.`val`+ pointer2.`val`
            var num = if(sum > 9) {
                plusOne = true
                sum%10
            }else{
                plusOne = false
                sum
            }
            var newNode = ListNode(num)
            cur.next = newNode
            cur = cur.next!!
            pointer1 = pointer1.next
            pointer2 = pointer2.next
        }

        while(pointer1 != null) {
            var sum = if(plusOne) pointer1.`val` + 1 else pointer1.`val`
            var num = if(sum > 9) {
                plusOne = true
                sum%10
            }else{
                plusOne = false
                sum
            }
            var newNode = ListNode(num)
            cur.next = newNode
            cur = cur.next!!
            pointer1 = pointer1.next
        }

        while(pointer2 != null) {
            var sum = if(plusOne) pointer2.`val` + 1 else pointer2.`val`
            var num = if(sum > 9) {
                plusOne = true
                sum%10
            }else{
                plusOne = false
                sum
            }
            var newNode = ListNode(num)
            cur.next = newNode
            cur = cur.next!!
            pointer2 = pointer2.next
        }

        if(plusOne) cur.next = ListNode(1)

        return dummyHead.next
    }


    fun addTwoNumbers2(l1: ListNode?, l2: ListNode?): ListNode? {

        var dummyHead = ListNode(0)
        var cur = dummyHead
        var plusOne = 0
        var pointer1: ListNode? = l1
        var pointer2: ListNode? = l2

        while(pointer1 != null || pointer2 != null || plusOne == 1) {
            var first = pointer1?.`val` ?: 0
            var second = pointer2?.`val` ?: 0
            var sum = first+second+plusOne
            var num = if(sum > 9) {
                plusOne = 1
                sum%10
            }else{
                plusOne = 0
                sum
            }
            var newNode = ListNode(num)
            cur.next = newNode
            cur = cur.next!!
            pointer1 = pointer1?.next
            pointer2 = pointer2?.next
        }


        return dummyHead.next
    }
}

fun main() {
    var two = ListNode(2)
    var three = ListNode(3)
    var four = ListNode(4)
    two.next = four
    four.next = three

    var five = ListNode(5)
    var six = ListNode(6)
    var four2 = ListNode(4)

    five.next = six
    six.next = four2

    AddTwoNumber.addTwoNumbers2(two,five)
}