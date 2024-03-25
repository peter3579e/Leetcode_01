package com.android.leetcode.binarySearch

object FindPeakElement {
    fun findPeakElement(nums: IntArray): Int {
        return binarySearch(nums,0, nums.size-1)
    }

    fun binarySearch(nums: IntArray, l: Int, r: Int): Int {
        if (l == r) return l
        var mid = (l + r) / 2
        return if (nums[mid] > nums[mid+1]) binarySearch(nums,l,mid) else binarySearch(nums,mid+1, r)
    }

    // time complexity is O(logN) and space complexity is O(1)
    fun findPeakElement2(nums: IntArray): Int {
        var l = 0
        var r = nums.size-1
        while(l < r) {
            var mid = (l+r) / 2
            if(nums[mid] > nums[mid+1]) r = mid else l = mid+1
        }
        return l
    }

    fun findPeakElement3(nums: IntArray): Int {
        if(nums.isEmpty()) return 0

        var left = 0
        var right = nums.size-1

        while(left < right) {
            val mid = (left+right) / 2
            if(nums[mid+1] > nums[mid]) {
                left = mid + 1
            } else {
                right = mid
            }
        }

        return left
    }

    fun findPeakElementNeetcode(nums: IntArray): Int {
        if(nums.isEmpty()) return 0

        var left = 0
        var right = nums.size-1

        while(left <= right) {
            val mid = left + (right - left) / 2
            if(mid + 1 < nums.size && nums[mid+1] > nums[mid]) {
                left = mid + 1
            } else if (mid - 1 >= 0 && nums[mid-1] > nums[mid]) {
                right = mid - 1
            } else {
                return mid
            }
        }

        return left
    }
}

fun main () {
    print(FindPeakElement.findPeakElementNeetcode(intArrayOf(5,3,2,1)))
}