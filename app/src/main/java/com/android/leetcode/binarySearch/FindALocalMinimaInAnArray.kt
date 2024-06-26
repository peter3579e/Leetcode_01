package com.android.leetcode.binarySearch

object FindALocalMinimaInAnArray {

    fun findMinima(arr: IntArray): Int {
        if (arr.size == 1) return 0
        var left = 0
        var right = arr.size-1
        while (left<right) {
            var mid = left + (right-left)/2
             if (arr[mid] > arr[mid+1]) {
                left = mid + 1
            }else {
                right = mid
            }
        }
        return left
    }
}

fun main() {
    print(FindALocalMinimaInAnArray.findMinima(intArrayOf(3, 2)))
}