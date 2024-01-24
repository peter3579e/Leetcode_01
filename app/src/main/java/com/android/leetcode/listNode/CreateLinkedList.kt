package com.android.leetcode.listNode

object CreateLinkedList {

    // ListNode class definition
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    // Function to create linked list from array
    fun createLinkedList(array: IntArray): ListNode? {
        if (array.isEmpty()) {
            return null
        }

        // Create the head of the linked list
        var head: ListNode? = ListNode(array[0])
        var current = head

        // Iterate through the array and add elements to the linked list
        for (i in 1 until array.size) {
            current?.next = ListNode(array[i])
            current = current?.next
        }

        return head
    }

    // Function to print the linked list
    fun printLinkedList(head: ListNode?) {
        var current = head
        while (current != null) {
            print("${current.`val`} -> ")
            current = current.next
        }
        println("null")
    }
}

fun main() {
    // Input array
    val inputArray = intArrayOf(1, 2, 6, 3, 4, 5, 6)

    // Create linked list from array
    val linkedList = CreateLinkedList.createLinkedList(inputArray)

    // Example usage
    println("Original Linked List:")
    CreateLinkedList.printLinkedList(linkedList)
}