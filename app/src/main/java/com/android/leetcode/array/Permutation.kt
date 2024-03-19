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

object Solution3 {


    fun permute(nums: IntArray): List<List<Int>> {
        var ans = mutableListOf<List<Int>>()
        var map = hashMapOf<Int, Int>()
        if(nums.size == 1) return listOf(listOf(nums[0]))
        for (n in nums) {
            map[n] = map.getOrDefault(n, 0) + 1
        }
        fun backTrack(nums: IntArray, temp: MutableList<Int>) {
            if (temp.size == nums.size) {
                ans.add(temp)
                return
            }

            for((k,v) in map) {
                if(v == 0) continue
                map[k] = map[k]!! - 1
                temp.add(k)
                backTrack(nums, temp)
                temp.removeLast()
                map[k] = map[k]!! + 1
            }
        }
        backTrack(nums, mutableListOf<Int>())
        return ans
    }
}

fun main() {
    print(Solution3.permute(intArrayOf(0,-1,1)))
}