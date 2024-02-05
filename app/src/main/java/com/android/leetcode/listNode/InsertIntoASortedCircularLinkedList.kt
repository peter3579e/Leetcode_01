package com.android.leetcode.listNode

object InsertIntoASortedCircularLinkedList {

    class Node(var `val`: Int) {
        var next: Node? = null
    }

    fun insert(head: Node?, insertVal: Int): Node? {

        if(head == null) {
            var node = Node(insertVal)
            node.next = node
            return node
        }

        var dummyHead = Node(0)
        dummyHead.next = head
        var cur: Node? = dummyHead

        while(cur != null) {
            cur = cur.next
            if(cur!!.`val` <= insertVal && cur.next!!.`val` >= insertVal || cur.next == dummyHead.next){
                var tempNext = cur!!.next
                cur!!.next = Node(insertVal)
                cur?.next!!.next = tempNext
                break
            }else if(cur.`val` > cur.next!!.`val`) {
                if(insertVal >= cur.`val` || insertVal <= cur.next!!.`val`) {
                    var tempNext = cur!!.next
                    cur!!.next = Node(insertVal)
                    cur?.next!!.next = tempNext
                    break
                }
            }
        }

        return dummyHead.next

    }
}