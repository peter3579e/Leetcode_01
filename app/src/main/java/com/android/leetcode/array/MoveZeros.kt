package com.android.leetcode.array

object MoveZeros {
    fun moveZeroes(nums: IntArray): Unit {

        for(i in 0..nums.size-2) {
            var lo = i
            var nonZero = 0
            if(nums[lo] != 0) continue

            for(j in i+1..nums.size-1) {
                if(nums[j] != 0) {
                    nonZero = j
                    break
                }
            }
            if (nonZero == 0) break
            nums[i] = nums[nonZero]
            nums[nonZero] = 0
        }

    }

}

fun main() {
    MoveZeros.moveZeroes(intArrayOf(0,1,0,3,12))
}