package com.android.leetcode.binarySearch

object FindAValley {
    /*
      [1,3,5,6,9]
     */

    fun findAValley(arr: IntArray): Int {

        var left = 0
        var right = arr.size - 1

        while ( left <= right) {
            val mid = left + (right-left) / 2
            if (mid+1 < arr.size && arr[mid+1] < arr[mid]) {
                left = mid + 1
            } else if (mid - 1 >= 0 && arr[mid-1] < arr[mid]) {
                right = mid - 1
            } else {
                return mid
            }
        }

        return left
    }
}

fun main() {
    print(FindAValley.findAValley(intArrayOf(1,2,1,5)))
}