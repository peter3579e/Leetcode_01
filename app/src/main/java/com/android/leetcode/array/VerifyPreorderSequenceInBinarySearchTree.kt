package com.android.leetcode.array

import java.util.Stack

object VerifyPreorderSequenceInBinarySearchTree {
    fun verifyPreorderFail(preorder: IntArray): Boolean {
        if(preorder.size == 1) return true

        var height = 1.0
        var num = 1
        /*
        height 1.0
        num 1
        size 5


         */
        val first = preorder[0]
        while(num < preorder.size) {
            num = num + (Math.pow(2.0,height)).toInt()
            height++
        }

        for(i in 1..num/2) {
            if(preorder[i] >= first) {
                return false
            }
        }

        for(i in num/2+1..preorder.size-1) {
            if(preorder[i] <= first) {
                return false
            }
        }

        return true
    }

    fun verifyPreorder(preorder: IntArray): Boolean {
        var minLimit = Int.MIN_VALUE
        val stack = Stack<Int>()

        for(num in preorder) {
            while(stack.isNotEmpty() && stack.peek() < num) {
                minLimit = stack.pop()
            }

            if(num <= minLimit) {
                return false
            }

            stack.push(num)
        }

        return true
    }
}

fun main() {
    VerifyPreorderSequenceInBinarySearchTree.verifyPreorder(intArrayOf(5,2,1,3,6))
}