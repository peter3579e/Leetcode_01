package com.android.leetcode.binarySearch

object PeakIndexInAMountainArray {
    fun peakIndexInMountainArray(arr: IntArray): Int {
        var l = 0
        var r = arr.size - 1
        while(l<r) {
            var mid = (l+r) / 2
            if (arr[mid] > arr[mid+1]) r = mid
            else l = mid+1
        }
        return l
    }
}