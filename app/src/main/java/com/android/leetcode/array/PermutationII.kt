package com.android.leetcode.array

import java.util.LinkedList


object PermutationII {
    var ans = mutableListOf<List<Int>>()
    var temp = mutableListOf<Int>()
    var map = hashMapOf<Int,Int>()
    fun permuteUnique(nums: IntArray): List<List<Int>> {

        for(i in nums) {
            map[i] = map.getOrDefault(i,0) + 1
        }
        backTrack(nums)
        return ans
    }

    fun backTrack(nums: IntArray) {
        if(temp.size == nums.size) {
            ans.add(temp.toList())
            return
        }

        for (i in map) {
            var key = i.key
            var value = i.value
            if(value == 0) continue
            temp.add(key)
            map[key] = map[key]!! - 1
            backTrack(nums)
            map[key] = map[key]!! + 1
            temp.removeAt(temp.size-1)
        }
    }
}

fun main() {
    PermutationII.permuteUnique(intArrayOf(1,1,2))
}