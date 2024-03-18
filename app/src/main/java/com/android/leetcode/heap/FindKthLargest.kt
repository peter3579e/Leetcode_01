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
    fun findKthLargestQuickSelect(nums: IntArray, k: Int): Int {
        fun quickSelect(l: Int, r: Int, nums: IntArray, k: Int): Int {
            var pivot = nums[r]
            var p = l
            var temp = 0
            for (i in l until r) {
                if (nums[i] <= pivot) {
                    //swap
                    temp = nums[p]
                    nums[p] = nums[i]
                    nums[i] = temp
                    p++
                }
            }

            temp = nums[p]
            nums[p] = pivot
            nums[r] = temp

            return when {
                nums.size - k < p -> quickSelect(l, p - 1, nums, k)
                nums.size - k > p -> quickSelect(p + 1, r, nums, k)
                else -> nums[p]
            }
        }

        return quickSelect(0, nums.size - 1, nums, k)
    }

    fun findKthLargestWhile(nums: IntArray, k: Int): Int {
        var from = 0
        var to = nums.lastIndex
        fun swap(a: Int, b: Int) { nums[a] = nums[b].also { nums[b] = nums[a] } }
        val target = nums.size - k
        while (from <= to) {
            var pi = from
            var pivot = nums[to]
            for (i in from until to) if (nums[i] < pivot) swap(i, pi++)
            swap(to, pi)

            if (pi == target) return nums[pi]
            if (pi < target) from = pi + 1
            if (pi > target) to = pi - 1
        }
        return -1
    }
}

fun main() {
    FindKthLargest.findKthLargestWhile(intArrayOf(3,2,1,5,6,4),2)
}