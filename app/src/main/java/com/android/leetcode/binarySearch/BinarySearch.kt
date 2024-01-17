package com.android.leetcode.binarySearch

object BinarySearch {

    // the time complexity O(logN)
    fun binarySearch(array: IntArray, target: Int): Int {
        var lo = 0
        var hi = array.size-1
        return binary(array, lo, hi, target)
    }

    // time complexity is O(logN) and space complexity is O(1)
    private fun binary(array: IntArray, l: Int, r: Int, target: Int): Int {
        var mid = (l + r) / 2
        if (array[mid] == target) return mid
        return if (array[mid] > target) binary(array, l, mid-1, target)
        else
            binary(array, mid+1, r, target)
    }

    // time complexity is O(logN) and space complexity is O(1)
    fun binarySearch2(array: IntArray, target: Int): Int {
        var l = 0
        var r = array.size-1
        while (l <= r) {
            val mid = (l+r) / 2
            if (array[mid] == target) return mid
            else if (array[mid] > target) r = mid - 1
            else l = mid + 1
        }
        return -1
    }
}

fun main () {
    print(BinarySearch.binarySearch2(intArrayOf(1,2,3,4,5,6),6))
}