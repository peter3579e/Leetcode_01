package com.android.leetcode.listNode

object CopyListWithRandomPointer {
    class Node(var `val`: Int) {
        var next: Node? = null
        var random: Node? = null
    }

    fun copyRandomList(node: Node?): Node? {
        if(node == null) return null

        var dummyHead = Node(0)
        var cur: Node? = dummyHead
        var pointer: Node? = node
        var map = hashMapOf<Node,Node>()

        while(pointer != null) {
            var cloneNode: Node? = null
            if(map[pointer] != null) {
                cloneNode = map[pointer]
            }else {
                cloneNode = Node(pointer.`val`)
                map[pointer] = cloneNode
            }
            cur!!.next = cloneNode

            //random
            var randomNode: Node? = null
            if(pointer.random == null) {
                randomNode = null
            }else if(map[pointer.random] != null) {
                randomNode = map[pointer.random]!!
            }else {
                randomNode = Node(pointer.random!!.`val`)
                map[pointer.random!!] = randomNode
            }
            cur.next!!.random = randomNode
            cur = cur.next
            pointer = pointer.next
        }

        return dummyHead.next
    }
}

fun main() {
    var four = CopyListWithRandomPointer.Node(4)
    var seven = CopyListWithRandomPointer.Node(7)
    var Ntwo = CopyListWithRandomPointer.Node(-2)

    four.next = seven
    four.random = Ntwo
    seven.random = four
    seven.next = Ntwo
    Ntwo.next = null
    Ntwo.random = null


    CopyListWithRandomPointer.copyRandomList(four)

}