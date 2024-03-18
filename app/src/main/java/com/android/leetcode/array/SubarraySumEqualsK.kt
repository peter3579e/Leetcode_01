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
        var map = hashMapOf<Int, Int>()
        var prefixSum = 0
        map[0] = 1
        for (i in 0 until nums.size) {
            prefixSum = prefixSum + nums[i]
            var key = prefixSum - k
            count = count + map.getOrDefault(key, 0)
            map[prefixSum] = map.getOrDefault(prefixSum, 0) + 1
        }

        return count
    }
}

fun main() {
    var string = "petep"
    print(SubarraySumEqualsK.subarraySum2(intArrayOf(-2,1, 2, -2, 5, -2,  1), 3))
}