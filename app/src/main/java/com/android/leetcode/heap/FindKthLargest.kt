package com.android.leetcode.heap

import java.util.PriorityQueue

object FindKthLargest {
    fun findKthLargest(nums: IntArray, k: Int): Int {

        //O(k)
        var heap = PriorityQueue<Int>()

        // time O(N)
        for(i in nums) {
            //O(logN)
            heap.offer(i)
            if(heap.size > k) heap.poll()
        }

        return heap.peek()
    }
}