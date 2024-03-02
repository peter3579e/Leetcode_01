package com.android.leetcode.binarySearch

object SearchInRotatedSortedArray {
    fun search(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size - 1

        while (right > left) {
            var mid = (left + right) / 2
            if (nums[mid] > nums[right]) {
                left = mid + 1
            } else {
                right = mid
            }
        }

        return if (nums[left] == target) left
        else if (target < nums[left - 1]) {
            binarySerach(0, left - 1, nums, target)
        } else {
            binarySerach(left, nums.size - 1, nums, target)
        }
    }

    fun binarySerach(left: Int, right: Int, nums: IntArray, target: Int): Int {
        var l = left
        var r = right

        while (r >= l) {
            var mid = (l + r) / 2
            if (target == nums[mid]) return mid
            if (target > nums[mid]) {
                l = mid + 1
            } else {
                r = mid - 1
            }
        }

        return -1
    }
}

fun main() {
    print(SearchInRotatedSortedArray.search(intArrayOf(4, 5, 6, 7, 0, 1, 2), 0))
}