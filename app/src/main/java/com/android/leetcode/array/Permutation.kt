package com.android.leetcode.array

object Permutation {
    var ans = mutableListOf<List<Int>>()
    var temp = mutableListOf<Int>()

    fun permute(nums: IntArray): List<List<Int>> {

        backTrack(nums, BooleanArray(nums.size+1) {true})
        return ans
    }

    fun backTrack(nums: IntArray, seen: BooleanArray) {
        if(temp.size == nums.size) {
            ans.add(temp.toList())
            return
        }

        for(i in 0 until nums.size) {
            var cur = nums[i]
            if (!seen[i]) continue
            temp.add(cur)
            seen[cur] = false
            backTrack(nums,seen)
            seen[cur] =true
            temp.removeAt(temp.size-1)
        }
    }
}

fun main() {
    Permutation.permute(intArrayOf(0,-1,1))
}