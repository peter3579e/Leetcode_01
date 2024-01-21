package com.android.leetcode.array

object MergeSortedArray {

    //the time complexity is O(n+m)
    // the space complexity is O(n+m)
    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        var point1 = nums1.size-1-n
        var point2 = nums2.size-1
        var w = n+m
        for(i in w-1 downTo 0) {
            if (point2>=0 && point1>= 0){
                nums1[i] = if (nums2[point2] >= nums1[point1]) {
                    nums2[point2--]
                } else {
                    nums1[point1--]
                }
            }
            else if(point2 >= 0) nums1[i] = nums2[point2--]
            else nums1[i] = nums1[point1--]
        }
    }
}

fun main() {
    MergeSortedArray.merge(intArrayOf(1,2,3,0,0,0), 3, intArrayOf(2,5,6), 3)
}