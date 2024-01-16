package com.android.leetcode.binarySearch

object BinarySearch {

    // the time complexity O(logN)
    fun binarySearch(array: IntArray, target: Int): Int {
        var lo = 0
        var hi = array.size-1
        var cur: Int = 0
        while (lo <= hi) {
            cur = lo + hi / 2
            if (array[cur] == target) {
                return cur
            } else if (array[cur] > target) {
                hi --
            }else {
                lo++
            }
        }
        return 0
    }
}

fun main () {
    print(BinarySearch.binarySearch(intArrayOf(1,2,3,4,5,6,7,8,9,10),0))
}