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
        // time O(NlogN)
        val sortedArray = nums.sortedArray()
        // space O(N)
        val answer = mutableListOf<List<Int>>()
        //time O(N)
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
        // O(N)
        while (lo < hi) {
            var sum = nums[lo]+nums[hi]+nums[i]
            if (sum == 0) {
                answer.add(listOf(nums[i],nums[lo++],nums[hi--]))
                while (lo < hi && nums[lo] == nums[lo - 1]) lo++
            }else if (sum < 0) {
                lo++
            }else if (sum > 0) {
                hi--
            }
        }
    }

    //using hashmap
    fun threeSumHashMap(nums: IntArray): List<List<Int>> {
        val sortedArray = nums.sortedArray() // time O(n log n)
        val answer = mutableListOf<List<Int>>() // space O(n)
        //time O(N)
        for (i in sortedArray.indices){
            if (i==0 || sortedArray[i-1] != sortedArray[i]) {
                twoSumHashMap(sortedArray, i, answer)
            }
        }
        return answer
    }

//    private fun twoSumLoAndHi(nums: IntArray, i: Int, answer: MutableList<List<Int>>) {
//        var lo = i+1
//        var hi = nums.size-1
//        while (lo < hi) {
//            var sum = nums[lo] + nums[hi] + nums[i]
//            if (sum == 0) {
//                answer.add(listOf(nums[lo++],nums[hi--],nums[i]))
//                while(lo < nums.size && nums[lo]==nums[lo-1]) lo++
//            }else if (sum > 0) {
//                hi--
//            }else {
//                lo ++
//            }
//        }
//    }

    private fun twoSumHashMap(nums: IntArray, i: Int, answer: MutableList<List<Int>>) {
        //Space O(N)
        var map = hashMapOf<Int,Int>()
        var pointer = i+1
        //time O(N)
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


    fun threeSum4(nums: IntArray): List<List<Int>> {
        val ans = mutableSetOf<List<Int>>()
        val seen = mutableSetOf<Int>()
        fun twoSum(index: Int, nums: IntArray, target: Int) {
            var i = index
            while( i < nums.size) {
                val cur = nums[i]
                val find = -(target + cur)
                if(seen.contains(find)) {
                    val sorted = listOf(target, cur, find).sorted() // Change this line
                    ans.add(sorted)
                    while (i+1 < nums.size && nums[i+1] == nums[i]) i++
                }else {
                    seen.add(cur)
                }
                i++
            }
        }
        for(i in 0 until nums.size) {
            val cur = nums[i]
            if(i == 0 || nums[i-1] != nums[i]) {
                twoSum(i+1, nums, cur)
            }
        }

        return ans.toList()
    }

    fun threeSumf(nums: IntArray): List<List<Int>> {
        nums.sort()
        var ans = mutableSetOf<List<Int>>()
        for(i in 0 until nums.size-1) {
            val cur = nums[i]
            var left = nums[i+1]
            var right = nums[nums.size-1]
            while( left < right) {
                val sum = cur + left + right
                if(sum == 0) {
                    ans.add(listOf(cur,left,right))
                    left ++
                    right --
                } else if (sum < 0) {
                    right --
                } else {
                    left ++
                }
            }
        }

        return ans.toList()
    }
}

fun main() {
    println(ThreeSum.threeSumf(intArrayOf(0,0,0)).toString())
}