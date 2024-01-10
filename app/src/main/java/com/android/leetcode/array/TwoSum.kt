package com.android.leetcode.array

//Easy
object TwoSum {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        for (i in nums.indices) {
            var cur = nums[i]
            for (j in i+1..<nums.size) {
                var pointer = nums[j]
                if (cur + pointer == target) {
                    return intArrayOf(i,j)
                }
            }
        }
        return intArrayOf()
    }

    // using map to replace pointer to find the remain number
    fun optimizedTwoSum(nums: IntArray, target: Int): IntArray {
        var map = hashMapOf<Int,Int>()
        nums.forEachIndexed { index, i ->
            val goal = target - index
            if (map[goal] != null) return intArrayOf(map[goal]!!,i)
            map[i] = index
        }
        return intArrayOf()
    }
}

fun main () {
    println(TwoSum.optimizedTwoSum(intArrayOf(2,7,11,15), 9))
}