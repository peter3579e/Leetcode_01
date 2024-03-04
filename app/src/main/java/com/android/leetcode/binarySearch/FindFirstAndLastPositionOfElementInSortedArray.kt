package com.android.leetcode.binarySearch

object FindFirstAndLastPositionOfElementInSortedArray {

    fun searchRange(nums: IntArray, target: Int): IntArray {
        if(nums.isEmpty()) return intArrayOf(-1,-1)
        var index = binarySearch(nums, target)
        if(index == -1) return intArrayOf(-1,-1)

        var left = index
        var right = index
        while(isVaild(left, nums.size) && nums[left] == target || isVaild(right, nums.size) && nums[right] == target) {
            if(isVaild(left, nums.size) && nums[left] == target) {
                left --
            }else if(isVaild(right, nums.size) && nums[right] == target) {
                right ++
            }
        }

        return intArrayOf(left+1,right-1)
    }

    private fun isVaild(index:Int, size: Int) = index >= 0 && index < size

    private fun binarySearch(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size-1

        while (left <= right) {
            var mid = (left + right) / 2
            if (nums[mid] == target) return mid
            else if (nums[mid] > target) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        return -1
    }
}

fun main() {
    FindFirstAndLastPositionOfElementInSortedArray.searchRange(intArrayOf(1),1)
}