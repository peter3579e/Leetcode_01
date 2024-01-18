package com.android.leetcode.quickSort

object QuickSort {

    fun quickSort(arr: IntArray, left: Int = 0, right: Int = arr.size - 1): IntArray {
        var start = left
        var end = right
        val pivot = arr[(left + right) / 2]

        while (start <= end) {
            while (arr[start] < pivot) {
                start++
            }
            while (arr[end] > pivot) {
                end--
            }
            if (start <= end) {
                val temp = arr[start]
                arr[start] = arr[end]
                arr[end] = temp
                start++
                end--
            }
        }

        if (left < end) {
            quickSort(arr, left, end)
        }
        if (start < right) {
            quickSort(arr, start, right)
        }
        return arr
    }


    fun quickSort2(array: IntArray, left: Int, right: Int): IntArray {
        val index = partition (array, left, right)
        if(left < index-1) { // 2) Sorting left half
            quickSort2(array, left, index-1)
        }
        if(index < right) { // 3) Sorting right half
            quickSort2(array,index, right)
        }
        return array
    }

    fun partition(array: IntArray, l: Int, r: Int): Int {
        var left = l
        var right = r
        val pivot = array[(left + right)/2] // 4) Pivot Point
        while (left <= right) {
            while (array[left] < pivot) left++ // 5) Find the elements on left that should be on right

            while (array[right] > pivot) right-- // 6) Find the elements on right that should be on left

            // 7) Swap elements, and move left and right indices
            if (left <= right) {
                swapArray(array, left,right)
                left++
                right--
            }
        }
        return left
    }

    fun swapArray(a: IntArray, b: Int, c: Int) {
        val temp = a[b]
        a[b] = a[c]
        a[c] = temp
    }
}

fun main() {
    var array = intArrayOf(12, 11, 13, 5, 6, 7)
    print(QuickSort.quickSort2(array, 0, array.size-1).toList())
}