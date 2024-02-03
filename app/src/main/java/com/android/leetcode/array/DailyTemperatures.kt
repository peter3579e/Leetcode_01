package com.android.leetcode.array

import java.util.Stack

object DailyTemperatures {
    fun dailyTemperatures(temperatures: IntArray): IntArray {
        var stack = Stack<Int>()
        var ans = IntArray(temperatures.size){0}
        for (i in 0..temperatures.size-1) {
            while(stack.isNotEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                var topIndex = stack.pop()
                ans[topIndex] = i - topIndex
            }
            stack.push(i)
        }

        return ans
    }
}

fun main() {
    DailyTemperatures.dailyTemperatures(intArrayOf(73,74,75,71,69,72,76,73))
}