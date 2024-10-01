package com.android.leetcode.amazon

fun maximumSubarraySum(nums: IntArray, k: Int): Long {

    var set = mutableSetOf<Long>()
    var ans = 0L
    var start = 0
    var count = 0L

    for(end in 0 until nums.size) {
        val num = nums[end].toLong()
        if(set.contains(num)) {
            start = end
            set = mutableSetOf(num)
            continue
        }
        count = count + num
        set.add(num)
        if(set.size == k) {
            ans = maxOf(ans,count)
            set.remove(nums[start].toLong())
            count = count - nums[start].toLong()
            start++
        }
    }

    return ans
}

fun main() {
    maximumSubarraySum(intArrayOf(1,5,4,2,9,9,9),3)
}