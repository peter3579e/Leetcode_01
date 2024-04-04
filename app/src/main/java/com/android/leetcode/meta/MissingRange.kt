package com.android.leetcode.meta

/*
* Variation of Missing Ranges where missing elements are less than or equal to 2, add them individually. Otherwise add a hyphen for the missing range

So for array = [2, 3, 6, 11, 13, 18, 75], lower = 0, upper = 99, we should return [0,1,4,5,7-10,12,14-17,19-74,76-99]

Anyone have suggestions, please lmk. Can easily solve missing ranges but problem becomes tricky when accounting for edges of range
*
* */

fun findMissingRanges(nums: IntArray, lower: Int, upper: Int): List<String> {
    val result = mutableListOf<String>()

    if (nums[0] - lower in 1..2) {
        for (i in lower until nums[0]) {
            result.add(i.toString())
        }
    } else if (nums[0] - lower > 2) {
        result.add(lower.toString() + '-' + (nums[0]-1).toString())
    }

    var prev = nums[0]

    for (i in 1 until nums.size) {
        if (nums[i] - prev in 2..3) {
            result.addAll((prev+1 until nums[i]).map(Int::toString))
        } else if (nums[i] - prev > 2) {
            result.add((prev+1).toString() + '-' + (nums[i]-1).toString())
        }
        prev = nums[i]
    }

    if (upper - nums[nums.lastIndex] in 1..2) {
        for (i in nums[nums.lastIndex]+1 until upper) {
            result.add(i.toString())
        }
    } else if (upper - nums[nums.lastIndex] > 2) {
        result.add((nums[nums.lastIndex]+1).toString() + '-' + upper)
    }

    return result
}


fun main() {
    val array = intArrayOf(2, 3, 6, 11, 13, 18, 75)
    val lower = 0
    val upper = 99
    val result = findMissingRanges(array, lower, upper)
    println(result)
}