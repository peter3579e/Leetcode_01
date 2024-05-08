package com.android.leetcode.binarySearch

import kotlin.math.max
import kotlin.math.min

object MedianOfTwoSortedArrays {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {

        var A = nums1
        var B = nums2

        if(nums2.size < nums1.size) {
            A = nums2
            B = nums1
        }

        val total = nums1.size + nums2.size
        val half = total/2

        var left = 0
        var right = A.size-1

        while(true) {
            val indexA = (left+right) / 2
            val indexB = half - indexA - 2

            val leftA = if(indexA < 0) Double.MIN_VALUE else A[indexA].toDouble()
            val rightA = if(indexA+1 > A.size-1) Double.MAX_VALUE else A[indexA + 1].toDouble()
            val leftB = if(indexB < 0) Double.MIN_VALUE else B[indexB].toDouble()
            val rightB = if(indexB + 1 > B.size-1) Double.MAX_VALUE else B[indexB + 1].toDouble()

            if (leftA <= rightB && leftB <= rightA) {
                if(total % 2 != 0) {
                    return minOf(leftA,leftB)
                }
                return (minOf(leftA,leftB) + maxOf(rightA,rightB)) / 2.0
            }

            if(rightB < leftA) {
                right = indexA - 1
            } else {
                left = indexA + 1
            }
        }
    }

    fun findMedianSortedArrays2(nums1: IntArray, nums2: IntArray): Double {
        if (nums1.size > nums2.size) {
            return findMedianSortedArrays2(nums2, nums1)
        }
        val m = nums1.size
        val n = nums2.size
        var left = 0
        var right = m
        while (left <= right) {
            val partitionA = (left + right) / 2
            val partitionB = (m + n + 1) / 2 - partitionA
            val maxLeftA = if (partitionA == 0) Int.MIN_VALUE else nums1[partitionA - 1]
            val minRightA = if (partitionA == m) Int.MAX_VALUE else nums1[partitionA]
            val maxLeftB = if (partitionB == 0) Int.MIN_VALUE else nums2[partitionB - 1]
            val minRightB = if (partitionB == n) Int.MAX_VALUE else nums2[partitionB]
            if (maxLeftA <= minRightB && maxLeftB <= minRightA) {
                return if ((m + n) % 2 == 0) {
                    (max(maxLeftA.toDouble(), maxLeftB.toDouble()) + min(
                        minRightA.toDouble(),
                        minRightB.toDouble()
                    )) /
                            2.0
                } else {
                    max(maxLeftA.toDouble(), maxLeftB.toDouble())
                }
            } else if (maxLeftA > minRightB) {
                right = partitionA - 1
            } else {
                left = partitionA + 1
            }
        }
        return 0.0
    }
}

fun main() {
    MedianOfTwoSortedArrays.findMedianSortedArrays(intArrayOf(1,3), intArrayOf(2))
}