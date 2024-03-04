package com.android.leetcode.array

import java.util.Stack

object MergeIntervals {

    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        if(intervals.isEmpty()) return arrayOf()
        var sort = intervals.sortedBy {
            it.first()
        }

        var stack = Stack<IntArray>()
        stack.push(intervals[0])

        for(i in 1 until intervals.size) {
            var prev = stack.peek()!!
            var cur = intervals[i]
            if(prev[1] >= cur[0]) {
                stack.pop()!!
                var arr = intArrayOf(prev[0], maxOf(prev[1],cur[1]))
                stack.push(arr)
            } else {
                stack.push(cur)
            }
        }

        var ans = Array<IntArray>(stack.size) { intArrayOf()}
        var count = stack.size-1

        while(stack.isNotEmpty()) {
            ans[count] = stack.pop()!!
            count--
        }

        return ans
    }
}

fun main() {
    MergeIntervals.merge(arrayOf(intArrayOf(1,4), intArrayOf(0,4)))
}