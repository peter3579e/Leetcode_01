package com.android.leetcode.array

object SubarraySumEqualsK {
    fun subarraySum(nums: IntArray, k: Int): Int {
        var count = 0


        for(i in 0 until nums.size) {
            var sum = 0
            for(j in i until nums.size){
                sum = sum + nums[j]
                if(sum == k) {
                    count ++
                }
            }
        }

        return count
    }

    fun subarraySum2(nums: IntArray, k: Int): Int {
        var count = 0
        var map = hashMapOf<Int,Int>()
        var prefixSum = 0
        map[0] = 1
        for(i in 0 until nums.size) {
            prefixSum = prefixSum + nums[i]
            count = count + map.getOrDefault(prefixSum-k, 0)
            map[prefixSum] = map.getOrDefault(prefixSum, 0) + 1
        }

        return count
    }
}

fun main() {
    print(SubarraySumEqualsK.subarraySum2(intArrayOf(1,1,1), 2))
}