package com.android.leetcode.mergeSort

// time complexity is O(NlogN)
// Space complexity is O(logN)
object MergeSort {
    fun mergeSort(arr: IntArray): IntArray {
        if (arr.size <= 1) {
            return arr
        }

        val middle = arr.size / 2
        val left = arr.sliceArray(0..middle - 1)
        val right = arr.sliceArray(middle..arr.size-1)

        return merge(mergeSort(left), mergeSort(right))
    }

    fun merge(leftArray: IntArray, rightArray: IntArray): IntArray {
        val mergedArray = IntArray(leftArray.size + rightArray.size)

        var leftIndex = 0
        var rightIndex = 0
        var mergedIndex = 0

        while (leftIndex < leftArray.size && rightIndex < rightArray.size) {
            if (leftArray[leftIndex] <= rightArray[rightIndex]) {
                mergedArray[mergedIndex] = leftArray[leftIndex]
                leftIndex++
            } else {
                mergedArray[mergedIndex] = rightArray[rightIndex]
                rightIndex++
            }
            mergedIndex++
        }

        while (leftIndex < leftArray.size) {
            mergedArray[mergedIndex] = leftArray[leftIndex]
            leftIndex++
            mergedIndex++
        }

        while (rightIndex < rightArray.size) {
            mergedArray[mergedIndex] = rightArray[rightIndex]
            rightIndex++
            mergedIndex++
        }

        return mergedArray
    }




    fun mergeSort2(intArray: IntArray): IntArray {
        if (intArray.size <= 1) {
            return intArray
        }

        val middle = (0 + intArray.size-1) / 2
        val left = intArray.copyOfRange(0,middle)
        val right = intArray.copyOfRange(middle,intArray.size-1)

        return merge2(mergeSort2(left), mergeSort2(right))
    }

    fun merge2(leftArray: IntArray, rightArray: IntArray): IntArray {
        var l = 0
        var r = 0
        var k = 0
        val tempArray = IntArray(leftArray.size + rightArray.size)

        while (l < leftArray.size && r < rightArray.size) {
            if (leftArray[l] <= rightArray[r]) {
                tempArray[k] = leftArray[l++]
            } else {
                tempArray[k] = rightArray[r++]
            }
            k++
        }

        while (l < leftArray.size) {
            tempArray[k++] = leftArray[l++]
        }

        while (r < rightArray.size) {
            tempArray[k++] = rightArray[r++]
        }

        return tempArray
    }
}

fun main() {
    val arr = intArrayOf(12, 11, 13, 5, 6, 7)
    val sortedArr = MergeSort.mergeSort(arr)

    var list = ArrayDeque<MutableList<Int>>()
    list.contains(listOf(1))

    println("Sorted array: ${sortedArr.joinToString(", ")}")
}