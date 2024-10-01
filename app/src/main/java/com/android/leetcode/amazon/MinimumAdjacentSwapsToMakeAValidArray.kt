package com.android.leetcode.amazon

class MinimumAdjacentSwapsToMakeAValidArray {
    fun minimumSwaps(nums: IntArray): Int {
        if(nums.size == 1) return 0
        var maxNum = Int.MIN_VALUE
        var maxIndex = Int.MIN_VALUE
        var minNum = Int.MAX_VALUE
        var minIndex = Int.MAX_VALUE

        for(i in 0 until nums.size) {
            val num = nums[i]
            if(num < minNum) {
                minNum = num
                minIndex = i
            }
            if(num > maxNum) {
                maxNum = num
                maxIndex = i
            } else if(num == maxNum && i > maxIndex) {
                maxIndex = i
            }
        }


        return if (minIndex > maxIndex) {
            (nums.size - maxIndex - 1) + (minIndex - 1)
        } else {
            nums.size - maxIndex - 1 + minIndex
        }
    }
}

fun main() {
    print(MinimumAdjacentSwapsToMakeAValidArray().minimumSwaps(intArrayOf(3,4,5,5,3,1)))
}