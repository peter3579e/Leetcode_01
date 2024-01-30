package com.android.leetcode.listNode

//Time complexity : O(n)O(n)O(n), where nnn is the number of nodes in the Linked List.
//
//The recursive function is run once for each of the nnn nodes, and the body of the recursive function is O(1)O(1)O(1). Therefore, this gives a total of O(n)O(n)O(n).
//
//Space complexity : O(n)O(n)O(n), where nnn is the number of nodes in the Linked List.
//
//I hinted at the start that this is not using O(1)O(1)O(1) space. This might seem strange, after all we aren't creating any new data structures. So, where is the O(n)O(n)O(n) extra memory we're using? Understanding what is happening here requires understanding how the computer runs a recursive function.
//
//Each time a function is called within a function, the computer needs to keep track of where it is up to (and the values of any local variables) in the current function before it goes into the called function. It does this by putting an entry on something called the runtime stack, called a stack frame. Once it has created a stack frame for the current function, it can then go into the called function. Then once it is finished with the called function, it pops the top stack frame to resume the function it had been in before the function call was made.
//
//Before doing any palindrome checking, the above recursive function creates nnn of these stack frames because the first step of processing a node is to process the nodes after it, which is done with a recursive call. Then once it has the nnn stack frames, it pops them off one-by-one to process them.
//
//So, the space usage is on the runtime stack because we are creating nnn stack frames. Always make sure to consider what's going on the runtime stack when analyzing a recursive function. It's a common mistake to forget to.
object PalindromeLinkedList {

    var curHead = CreateLinkedList.ListNode(0)
    fun isPalindrome(head: CreateLinkedList.ListNode?): Boolean {
        curHead = head!!
        return recursive(head)
    }

    fun recursive(curEnd: CreateLinkedList.ListNode?): Boolean {
        if (curEnd != null) {
            if (!recursive(curEnd.next)) return false
            if (curEnd.`val` != curHead.`val`) return false
           if(curHead.next != null) curHead = curHead.next!!
        }
        return true
    }
}

fun main() {
    var one = CreateLinkedList.ListNode(1)
    var two = CreateLinkedList.ListNode(2)
    var two1 = CreateLinkedList.ListNode(2)
    var one1 = CreateLinkedList.ListNode(1)

    one.next = two
    two.next = two1
    two1.next = one1

    PalindromeLinkedList.isPalindrome(one)
}