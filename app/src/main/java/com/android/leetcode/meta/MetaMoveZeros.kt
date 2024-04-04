package com.android.leetcode.meta

object MetaMoveZeros {
    //  0, 1, 0, 3, 12
    //  1  0  3  0  12
    //  1  0  3 0  12
    fun moveZeros(arr: IntArray, k: Int): IntArray {
        var start = 0

        // Move non-zero elements towards the beginning of the array
        for (i in arr.indices) {
            if (arr[i] != 0) {
                // Swap non-zero element with zero element at 'start' position
                arr[i] = arr[start].also { arr[start] = arr[i] }
                start++
            }
        }

        start-- // Decrement start as it's currently pointing to the first zero element

        // Calculate zero count and required shift
        val zeroCount = arr.size - start - 1
        val requiredShift = start * k

        // Calculate the index from where shifting of non-zero elements should start
        var right = arr.size - 1 - (zeroCount - requiredShift)

        // Shift non-zero elements based on required shifts
        while (start > 0) {
            arr[start] = arr[right].also { arr[right] = arr[start] }
            right -= (k + 1)
            start--
        }

        return arr
    }

    fun moveZeroes(nums: IntArray, k: Int): IntArray {
        // First move all zeroes to the end
        var writePointer = 0
        for (readPointer in nums.indices) {
            if (nums[readPointer] != 0) {
                nums[writePointer] = nums[readPointer]
                writePointer++
            }
        }

        // Record the position of the last non-zero element
        val nonZeroPos = writePointer - 1

        // Assign zeroes at the end
        for (i in writePointer until nums.size) {
            nums[i] = 0
        }

        // If all elements are zeroes, return the list
        if (nonZeroPos < 0) {
            return nums
        }

        // Shift all non-zeroes by curPos*k positions except the first one
        for (i in nonZeroPos downTo 1) {
            val target = minOf(i + (i * k), nums.size - 1)
            if (nums[target] != 0) { // If the target position is not zero, skip
                continue
            }
            // Swap the non-zero element with the target position
            nums[i] = nums[target].also { nums[target] = nums[i] }
        }

        return nums
    }

}

fun main() {
    println(MetaMoveZeros.moveZeroes(intArrayOf(0, 1, 0, 3, 12), 1).toList())
//    println(MetaMoveZeros.moveZeros(intArrayOf(0, 1, 0, 3, 0, 12), 1).toList())
//    println(MetaMoveZeros.moveZeros3(intArrayOf(0, 1, 0, 3, 0, 12), 2))
//    println(MetaMoveZeros.moveZeros(intArrayOf(2, 0, 0, 7, 0, 9, 8, 0, 0), 1))
//    println(MetaMoveZeros.moveZeros(intArrayOf(2, 0, 0, 7, 0, 9, 8, 0, 0, 0), 2))
//    println(MetaMoveZeros.moveZeros(intArrayOf(1, 2, 3, 4, 5), 1))
    //  0, 1, 0, 3, 0, 12
    //
    /*
    [1, 0, 3, 0, 12]
    [1, 0, 3, 0, 12, 0]
    [1, 0, 0, 3, 0, 12]
     2, 7, 9, 8, 0, 0, 0, 0, 0 -> zeroCnt = 5, require shift 3   k = 1   right = arr.size - zeroCnt - requireshift
    [2, 0, 7, 0, 9, 0, 8, 0, 0]


     2, 0, 0, 7, 0, 9, 8, 0, 0, 0
     2, 7  9  8  0  0  0  0  0  0  k = 2     zeroCnt = 6  requireshift = 3    right = 0
    [2, 0, 0, 7, 0, 0, 9, 0, 0, 8]
    [1, 2, 3, 4, 5]
    * */

}