package com.android.leetcode.array

import java.util.Stack

object BuildingsWithAnOceanView {
    // TIME IS O(N)
    //SPACE IS O(N)
    fun findBuildings(heights: IntArray): IntArray {

        //O(N)
        var stack = Stack<Int>()

        // O(N)
        for(i in 0..heights.size-1) {
            while(stack.isNotEmpty() && heights[i] >= heights[stack.peek()]) {
                stack.pop()
            }
            stack.push(i)
        }

        //O(K)
        var ans = IntArray(stack.size)

        //O(K)
        for (i in ans.size-1 downTo 0) {
            ans[i] = stack.pop()
        }

        return ans
    }

    fun findBuildings2(heights: IntArray): IntArray {

        var stack = Stack<Int>()
        stack.push(heights.size-1)

        for(i in heights.size-2 downTo 0) {
            if(stack.isNotEmpty() && heights[i] >= heights[stack.peek()]) {
                stack.push(i)
            }
        }

        var ans = IntArray(stack.size)

        for (i in 0..ans.size-1) {
            ans[i] = stack.pop()
        }

        return ans
    }
}

fun main () {
    BuildingsWithAnOceanView.findBuildings2(intArrayOf(4,2,3,1))
}