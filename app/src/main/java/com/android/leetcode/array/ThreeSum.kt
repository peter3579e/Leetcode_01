package com.android.leetcode.array


//Medium
object ThreeSum {
    /*
    Brute solve failed not able to eliminate duplicated output, the duplication was solved by
    sorting the array at the beginning. However, the solution has exceed the time limit
     */
    fun threeSumFailed(nums: IntArray): List<List<Int>> {
        val sortedArray = nums.sortedArray()
        var answer = mutableListOf<List<Int>>()
        for (i in sortedArray.indices) {
            var map = hashMapOf<Int, Int>()
            var firstPointer = sortedArray[i]
            for (j in i+1..<sortedArray.size) {
                var secondPointer = sortedArray[j]
                var goal = - (firstPointer + secondPointer)
                if (map.containsValue(goal)) {
                    if (!answer.contains(listOf(sortedArray[i],sortedArray[j],goal))) {
                        answer.add(listOf(sortedArray[i],sortedArray[j],goal))
                    }
                }
                map[j] = secondPointer
            }
        }
        return answer
    }


    //using two pointer with pointer lo and hi
    fun threeSum(nums: IntArray): List<List<Int>> {
        val sortedArray = nums.sortedArray()
        val answer = mutableListOf<List<Int>>()
        for (i in sortedArray.indices){
            if (i==0 || sortedArray[i-1] != sortedArray[i]) {
                twoSum(sortedArray, i, answer)
            }
        }
        return answer
    }

    private fun twoSum(nums: IntArray, i: Int, answer: MutableList<List<Int>>) {
        var lo = i+1
        var hi = nums.size-1
        while (lo < hi) {
            var sum = nums[lo]+nums[hi]+nums[i]
            if (sum == 0) {
                answer.add(listOf(nums[i],nums[lo++],nums[hi--]))
                while (lo < hi && nums[lo] == nums[lo - 1]) ++lo
            }else if (sum < 0) {
                lo++
            }else if (sum > 0) {
                hi--
            }
        }
    }

    //using hashmap
    fun threeSumHashMap(nums: IntArray): List<List<Int>> {
        val sortedArray = nums.sortedArray() // O(n log n)
        val answer = mutableListOf<List<Int>>()
        for (i in sortedArray.indices){
            if (i==0 || sortedArray[i-1] != sortedArray[i]) {
                twoSumHashMap(sortedArray, i, answer)
            }
        }
        return answer
    }

    private fun twoSumLoAndHi(nums: IntArray, i: Int, answer: MutableList<List<Int>>) {
        var lo = i+1
        var hi = nums.size-1
        while (lo < hi) {
            var sum = nums[lo] + nums[hi] + nums[i]
            if (sum == 0) {
                answer.add(listOf(nums[lo++],nums[hi--],nums[i]))
                while(lo < nums.size && nums[lo]==nums[lo-1]) lo++
            }else if (sum > 0) {
                hi--
            }else {
                lo ++
            }
        }
    }

    private fun twoSumHashMap(nums: IntArray, i: Int, answer: MutableList<List<Int>>) {
        var map = hashMapOf<Int,Int>()
        var pointer = i+1
        while (pointer < nums.size) {
            var goal = -(nums[i] + nums[pointer])
            if (map.containsValue(goal)) {
                answer.add(listOf(nums[i],nums[pointer], goal))
                while (pointer+1 < nums.size && nums[pointer+1] == nums[pointer]) pointer++
            }
            map[pointer] = nums[pointer]
            pointer++
        }
    }
}

fun main() {
    println(ThreeSum.threeSumHashMap(intArrayOf(-1,0,1,2,-1,-4)).toString())
}