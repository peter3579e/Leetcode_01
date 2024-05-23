package com.android.leetcode.stack.monotonicStack

import java.util.Stack

object TrappingRainWater {
    fun trap(height: IntArray): Int {

        var ans = 0
        var area = 0
        var edgeMax = Int.MIN_VALUE
        val stack = Stack<Int>()

        for(i in 0 until height.size) {
            val cur = height[i]
            while(stack.isNotEmpty() && cur > edgeMax) {
                edgeMax = minOf(edgeMax,cur)
                val prevHeight = stack.pop()!!
                area = if(edgeMax == 0) 0 else area + (edgeMax - prevHeight) * 1
            }
            ans = ans + area
            area = 0
            stack.push(cur)
            edgeMax = maxOf(edgeMax,cur)
        }
        return ans
    }
}

fun main() {
    TrappingRainWater.trap(intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1))
}