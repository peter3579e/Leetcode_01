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

    //failed
    fun copyRandomList3(node: Node?): Node? {

        val indexToNode = hashMapOf<Int,Node?>()
        val randomMap = hashMapOf<Int,Node?>()
        val randomNodeToIndex = hashMapOf<Node?,Int>()
        var cur = node
        val dummyHead = Node(0)
        var newCur: Node? = dummyHead
        var count = 0

        while(cur != null) {
            val newNode = Node(cur.`val`)
            newCur!!.next = newNode
            indexToNode[count] = newNode
            randomNodeToIndex[cur] = count
            randomMap[count] = cur.random
            count++
            cur = cur.next
            newCur = newCur.next
        }

        newCur = dummyHead
        newCur = newCur.next
        count = 0

        while(newCur != null) {
            val randomNum = randomMap[count]
            val index = randomNodeToIndex[randomNum]
            newCur.random = indexToNode[index]
            newCur = newCur.next
        }

        return dummyHead.next
    }


    fun copyRandomListdfs(node: Node?): Node? {

        val dummyNode = Node(0)
        var newCur = dummyNode
        var cur = node

        findNode(cur,newCur)

        return dummyNode.next
    }

    val oldToNew = hashMapOf<Node,Node>()

    fun findNode(node1:Node?, node2:Node?) {
        if(node1 == null) return
        var cur = node1
        var newCur = node2
        val newNode = Node(cur!!.`val`)
        oldToNew[cur!!] = newNode
        newCur!!.next = newNode
        newCur = newCur.next!!
        findNode(cur.next,newCur)
        cur?.random?.let { newCur.random = oldToNew[it]  }
    }

    fun copyRandomList4(node: Node?): Node? {

        var pointer: Node? = node
        val dummyHead = Node(0)
        var cur = dummyHead
        val oldToNew = hashMapOf<Node,Node>()

        while(pointer != null) {
            var newRandom: Node? = null
            pointer.random?.let {
                if(oldToNew.contains(it)) {
                    newRandom = oldToNew[it]!!
                } else {
                    newRandom = Node(it.`val`)
                    oldToNew[it] = newRandom!!
                }
            }
            var newNode: Node?
            if(oldToNew.contains(pointer)) {
                newNode = oldToNew[pointer]!!
            } else {
                newNode = Node(pointer.`val`)
                oldToNew[pointer] = newNode
            }
            newNode.random = newRandom
            cur.next = newNode
            pointer = pointer.next
            cur = cur.next!!
        }


        return dummyHead.next
    }

}

fun main() {
    var four = CopyListWithRandomPointer.Node(4)
    var seven = CopyListWithRandomPointer.Node(7)
    var Ntwo = CopyListWithRandomPointer.Node(-2)

    // 4 -> 7 -> -2

    four.next = seven
    four.random = Ntwo
    seven.random = four
    seven.next = Ntwo
    Ntwo.next = null
    Ntwo.random = null


    CopyListWithRandomPointer.copyRandomList(four)

}