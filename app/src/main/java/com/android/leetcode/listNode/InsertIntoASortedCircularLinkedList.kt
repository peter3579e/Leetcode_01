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

    fun insertOptimal(head: Node?, insertVal: Int): Node? {
        var newNode = Node(insertVal)
        if(head == null) {
            newNode.next = newNode
            return newNode
        }

        var prev = head
        var cur = head.next

        while(cur != null && prev != null) {
            if(prev.`val` <= insertVal && insertVal <= cur.`val`) {
                prev.next = newNode
                newNode.next = cur
                break
            } else if (prev.`val` > cur.`val`) {
                if(prev.`val` < insertVal || cur.`val` > insertVal) {
                    prev.next = newNode
                    newNode.next = cur
                    break
                }
            } else if(prev.next == head) {
                prev.next = newNode
                newNode.next = cur
                break
            }
            cur = cur.next
            prev = prev.next
        }
        return head
    }

    fun insert3(head: Node?, insertVal: Int): Node? {
        val node = Node(insertVal)
        if(head == null) {
            node.next = node
            return node
        }

        var prev = head
        var cur = head.next

        fun insert(): Node {
            prev!!.next = node
            node.next = cur
            return head
        }

        while(true) {
            if(prev!!.`val` <= insertVal && cur!!.`val` >= insertVal) {
                return insert()
            } else if ( prev.`val` > cur!!.`val`) {
                if (prev.`val` < insertVal || cur.`val` > insertVal) {
                    return insert()
                }
            } else if( prev.next == head) {
                return insert()
            }
            prev = prev.next!!
            cur = cur.next
        }

        return null
    }

    fun insert4(head: Node?, insertVal: Int): Node? {
        if(head == null) {
            val node = Node(insertVal)
            node.next = node
            return node
        }
        var cur = head.next!!
        var prev = head!!

        while(true) {
            if(insertVal in prev.`val`..cur.`val`) {
                break
            } else if(cur.`val` < prev.`val`) {
                if(insertVal > prev.`val` || insertVal < cur.`val`) break
            } else if(prev.next!! == head) break
            cur = cur.next!!
            prev = prev.next!!
        }

        val newNode = Node(insertVal)

        prev.next = newNode
        newNode.next = cur

        return head
    }
}

fun main() {
    val N3 = InsertIntoASortedCircularLinkedList.Node(1)
    val N4 = InsertIntoASortedCircularLinkedList.Node(3)
    val N1 = InsertIntoASortedCircularLinkedList.Node(5)
    N3.next = N4
    N4.next = N1
    N1.next = N3
    InsertIntoASortedCircularLinkedList.insert4(N3, 1)
}



