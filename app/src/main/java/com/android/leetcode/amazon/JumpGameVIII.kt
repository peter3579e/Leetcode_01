package com.android.leetcode.amazon

import java.util.Arrays
import java.util.Deque
import java.util.Stack
import kotlin.math.min


fun minCost(nums: IntArray, costs: IntArray): Long {
    val one = Stack<Int>()
    val two = Stack<Int>()
    val dp = LongArray(nums.size) { Long.MAX_VALUE }
    dp[0] = 0

    for (i in nums.indices) {
        while (!one.isEmpty() && nums[i] >= nums[one.peek()]) {
            dp[i] = min(dp[i].toDouble(), (dp[one.pop()] + costs[i]).toDouble())
                .toLong() // condition 1.
        }
        while (!two.isEmpty() && nums[i] < nums[two.peek()]) {
            dp[i] = min(dp[i].toDouble(), (dp[two.pop()] + costs[i]).toDouble())
                .toLong() // condition 2.
        }
        one.push(i)
        two.push(i)
    }

    return dp[nums.size - 1]
}

fun main() {
    minCost(intArrayOf(3,2,4,4,1), intArrayOf(3,7,6,4,2))
}