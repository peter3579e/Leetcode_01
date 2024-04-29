package com.android.leetcode.windowSlicing

class SlidingWindowMaximum {
    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        if(k > nums.size) return intArrayOf()

        var start = 0
        val ans = IntArray(nums.size-k+1){ 0 }
        val dequeue = ArrayDeque<Int>()

        for (i in 0 until k) {
            while(dequeue.isNotEmpty() && nums[i] >= nums[dequeue.last()]) {
                dequeue.removeLast()
            }
            dequeue.add(i)
        }
        ans[start] = nums[dequeue.first()]
        start++

        for( end in k until nums.size) {
            if(dequeue.first() == end - k) {
                dequeue.removeFirst()
            }
            while(dequeue.isNotEmpty() && nums[end] >= nums[dequeue.last()]) {
                dequeue.removeLast()
            }
            dequeue.add(end)
            ans[start] = nums[dequeue.first()]
            start++
        }

        return ans
    }
}