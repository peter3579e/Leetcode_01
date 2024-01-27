package com.android.leetcode.listNode

object DesignLinkedList {

    // singly
    class MyLinkedList() {

        private var head: Node? = null
        private var tail: Node? = null
        private var size = 0

        fun get(index: Int): Int {

            //can not get element
            if (index < 0 || index >= size) {
                return -1
            }

            var current = head
            var init = 0
            while (init < index) {
                current = current?.next
                init++
            }
            return current?.data ?: -1
        }

        fun addAtHead(`val`: Int) {

            val newNode = Node(`val`)
            //first element?
            if (head == null) {
                head = newNode
                tail = newNode
            } else {
                //more than one element
                newNode.next = head
                head = newNode
            }
            //inc the size
            size++
        }

        fun addAtTail(`val`: Int) {

            val newNode = Node(`val`)
            //first element
            if (tail == null) {
                head = newNode
                tail = newNode
            } else {
                //more than one element
                tail?.next = newNode
                tail = newNode
            }
            //inc the size
            size++

        }

        fun addAtIndex(index: Int, `val`: Int) {


            //don't add if the index is over the size.
            if (index > size) {
                return
            }

            //insert at head
            if (index == 0) {
                addAtHead(`val`)
                return
            }

            //insert at tail
            if (index == size) {
                addAtTail(`val`)
                return
            }

            //between
            var currIndex = 0
            var prev = head
            while (currIndex < index - 1) {
                prev = prev?.next
                currIndex++
            }

            val newNode = Node(`val`)
            newNode.next = prev?.next
            prev?.next = newNode

            //update size
            size++

        }

        fun deleteAtIndex(index: Int) {

            //nothing to delete for an invalid index
            if (index < 0 || index >= size) {
                return
            }

            //only one element, clear all
            if (size == 1) {
                head = null
                tail = null
                size = 0
                return
            }

            //remove from the head, then update head
            if (index == 0) {
                head = head?.next
                // anothrr optionif head.next == null then tail = null
                size--
                return
            }

            //remove from between OR from the tail
            var currentIndex = 0
            var prev = head
            while (currentIndex < index - 1) {
                prev = prev?.next
                currentIndex++
            }
            prev?.next = prev?.next?.next

            //if the tail was removed then update tail to point to the correct tail
            if (prev?.next == null) {
                tail = prev
            }
            size--
        }
    }

    //double

    class Node(val data: Int) {
        var next: Node? = null
    }
}
