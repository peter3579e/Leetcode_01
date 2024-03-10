package com.android.leetcode.listNode


object LinkedListFrequency {
    // my solution
    fun frequenciesOfElements(head: ListNode?): ListNode? {
        var cur = head
        var map = hashMapOf<Int,Int>()

        while(cur != null) {
            map[cur!!.`val`] = map.getOrDefault(cur!!.`val`,0) + 1
            cur = cur.next
        }

        val dummyHead = ListNode(0)
        var cur2 = dummyHead
        for((key,value) in map) {
            cur2.next = ListNode(value)
            cur2 = cur2.next!!
        }

        return dummyHead.next
    }

    fun frequenciesOfElementsOptimal(head: ListNode?): ListNode? {
        val frequencies: MutableMap<Int, ListNode> = HashMap()
        var current = head
        var freqHead: ListNode? = null
        // 1,1,2,1,2,3
        //
        // map -> 1 : ListNode(1,null)
        // Process the linked list, storing
        // frequency ListNodes in the hashtable
        while (current != null) {
            // Increment frequency of existing element
            if (frequencies.containsKey(current.`val`)) {
                val frequencyNode = frequencies[current.`val`]
                frequencyNode!!.`val` += 1
                // New element, create hashtable entry with frequency node
            } else {
                val newFrequencyNode = ListNode(1)
                newFrequencyNode.next = freqHead
                frequencies[current.`val`] = newFrequencyNode
                freqHead = newFrequencyNode
            }
            current = current.next
        }
        return freqHead
    }
}

fun main() {
    var one = ListNode(1)
    var one1 = ListNode(1)
    var two = ListNode(2)
    var one2 = ListNode(1)
    var two1 = ListNode(2)
    var three = ListNode(3)
    one.next = one1
    one1.next = two
    two.next = one2
    one2.next = two1
    two1.next = three
    LinkedListFrequency.frequenciesOfElementsOptimal(one)
}