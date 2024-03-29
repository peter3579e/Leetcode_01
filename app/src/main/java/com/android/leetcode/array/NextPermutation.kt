package com.android.leetcode.array

object NextPermutation {
    fun nextPermutation(nums: IntArray) {
        var i = nums.size - 2
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--
        }
        if (i >= 0) {
            var j = nums.size - 1
            while (nums[j] <= nums[i]) {
                j--
            }
            swap(nums, i, j)
        }
        reverse(nums, i + 1)
        println(nums.toList())
    }

    private fun reverse(nums: IntArray, start: Int) {
        var i = start
        var j = nums.size - 1
        while (i < j) {
            swap(nums, i, j)
            i++
            j--
        }
    }

    private fun swap(nums: IntArray, i: Int, j: Int) {
        val temp = nums[i]
        nums[i] = nums[j]
        nums[j] = temp
    }
}

object Solution {
    fun nextPermutation(nums: IntArray): Unit {
        var left = nums.size - 2
        var right = nums.size - 1
        while(left >= 0 && nums[right] <= nums[left]) {
            left--
            right--
        }

        if(left >= 0) {
            var newRight = nums.size-1
            while(nums[left] >= nums[newRight]) {
                newRight--
            }
            swap(left,newRight,nums)
        }

        reverse(right,nums.size-1,nums)


    }

    private fun reverse(n1:Int, n2:Int, nums: IntArray) {
        var newN1 = n1
        var newN2 = n2

        while(newN2 > newN1) {
            swap(newN1,newN2,nums)
            newN1++
            newN2--
        }
    }

    private fun swap(n1: Int, n2:Int, nums: IntArray) {
        var temp = nums[n1]
        nums[n1] = nums[n2]
        nums[n2] = temp
    }
}

object SolutionRevise {
    fun nextPermutation(nums: IntArray): Unit {
        var index = -1
        for (i in nums.size-1 downTo 1) {
            if(nums[i] > nums[i-1]) {
                index = i
                break
            }
        }

        if(index != -1) {
            var swapIndex = -1

            for (i in nums.size-1 downTo 1) {
                if(nums[i] > nums[index]) {
                    swapIndex = i
                    break
                }
            }
            // swap
            reverseOrSwap(nums, index, swapIndex, true)
            // reverse
            reverseOrSwap(nums, index+1, nums.size-1, false)
        }
    }

    private fun reverseOrSwap(nums: IntArray, start: Int, end: Int, swap: Boolean) {
        var startIndex = start
        var endIndex = end

        while (startIndex < endIndex) {
            val temp = nums[endIndex]
            nums[endIndex] = nums[startIndex]
            nums[startIndex] = temp
            if(swap) break
            startIndex ++
            endIndex --
        }
    }
}

fun main() {
    var string ="bca"
    var array = string.toCharArray()
    var sort = array.sorted()
    var map = hashMapOf<Int,Int>()
    map.contains(2)
    val key: String = java.lang.String.valueOf(sort)
    println(key)
    SolutionRevise.nextPermutation(intArrayOf(1,3,2))
    // 1,5,8,5,7,6,4,3,1
}