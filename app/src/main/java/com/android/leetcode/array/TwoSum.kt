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
        // space O(N)
        // time O(N) + O(n)
        var map = hashMapOf<Int,Int>()
        nums.forEachIndexed { index, number ->
            val goal = target - number
            if (map[goal] != null) return intArrayOf(map[goal]!!,index)
            map[number] = index
        }
        return intArrayOf()
    }

    fun twoPointer(nums: IntArray, target: Int): IntArray {
        var lo = 0
        var hi = nums.size-1

        while (lo < hi) {
            var sum = nums[lo] + nums[hi]
            if (sum == target) return intArrayOf(lo,hi)
            else if (sum < target) {
                lo++
            }else {
                hi--
            }
        }
        return  intArrayOf()
    }
}

fun main () {
    println(TwoSum.twoPointer(intArrayOf(2,7,11,15), 9).toList())
}