package com.android.leetcode.array

import java.lang.StringBuilder

object FourSum {
    fun fourSumFail(nums: IntArray, target: Int): List<List<Int>> {
        nums.sort()

        val ans = mutableListOf<List<Int>>()

        fun threeSum(num1: Int, index: Int) {
            for (i in index until nums.size - 1) {
                if (i != index && nums[i] == nums[i - 1]) continue
                //twosum
                val cur = nums[i]
                var left = i + 1
                var right = nums.size - 1
                while (left < right) {
                    val sum = num1 + cur + nums[left] + nums[right]
                    if (sum > target) {
                        right--
                    } else if (sum < target) {
                        left++
                    } else {
                        ans.add(listOf(num1, cur, nums[left], nums[right]))
                        left++
                        right--
                        while (left < right && nums[left] == nums[left - 1]) left++
                    }
                }
            }
        }

        for (i in 0 until nums.size - 1) {
            if (i != 0 && nums[i] == nums[i - 1]) continue
            threeSum(nums[i], i + 1)
        }

        return ans
    }

    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
        nums.sort()
        val ans = mutableListOf<List<Int>>()
        // -2 -1 0 0 1 2
        fun KSum (k: Int, start: Int, newTarget: Int, temp: MutableList<Int>) {
            if (k != 2) {
                for (i in start until nums.size-k+1) {
                    if(i != start && nums[i-1] == nums[i]) continue
                    temp.add(nums[i])
                    KSum(k-1, i+1, newTarget - nums[i], temp)
                    temp.removeLast()
                }
            } else {
                var left = start
                var right = nums.size-1

                while(left < right) {
                    val sum = nums[left] + nums[right]
                    if(sum == newTarget) {
                        ans.add(temp + listOf(nums[left],nums[right]))
                        left ++
                        right --
                        while(left < right && nums[left - 1] == nums[left]) left ++
                    } else if (sum < newTarget) {
                        left ++
                    } else {
                        right --
                    }
                }
            }
        }

        KSum(4,0,target, mutableListOf<Int>())

        return ans
    }
}

fun main() {

    print(FourSum.fourSum(intArrayOf(1,0,-1,0,-2,2),0))
}