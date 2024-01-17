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
}

fun main () {
    print(FindPeakElement.findPeakElement(intArrayOf(1, 2, 3, 1)))
}