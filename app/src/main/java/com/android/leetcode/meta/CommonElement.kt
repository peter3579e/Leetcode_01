package com.android.leetcode.meta

import kotlin.math.min

/*
Given three integer arrays arr1, arr2 and arr3 sorted in strictly increasing order, return a sorted array of only the integers that appeared in all three arrays.
Example 1:
Input: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
Output: [1,5]
Explanation: Only 1 and 5 appeared in the three arrays.
TC: O(m+n+k)
 */
object CommonElement {

    fun commonElement(arr1: IntArray, arr2: IntArray, arr3: IntArray): List<Int> {
            var pointer1 = 0
            var pointer2 = 0
            var pointer3 = 0
            val ans = mutableListOf<Int>()

            while (pointer1 < arr1.size && pointer2 < arr2.size && pointer3 < arr3.size) {
                if (arr1[pointer1] == arr2[pointer2] && arr1[pointer1] == arr3[pointer3]) {
                    ans.add(arr1[pointer1])
                    pointer1++
                    pointer2++
                    pointer3++
                } else {
                    val min = minOf(arr1[pointer1],arr2[pointer2])
                    val mostMin = minOf(min,arr3[pointer3])
                    if(arr1[pointer1] == mostMin) pointer1++
                    if(arr2[pointer2] == mostMin) pointer2++
                    if(arr3[pointer3] == mostMin) pointer3++
                }
            }
        return ans
    }
}

fun main() {
    print(CommonElement.commonElement(intArrayOf(1,2,3,4,5), intArrayOf(1,2,5,7,9), intArrayOf(1,3,4,5,8)))
}
