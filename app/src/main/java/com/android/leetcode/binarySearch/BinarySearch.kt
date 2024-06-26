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
    // this binary search is for graph, not for finishing a existing element in the array
    fun binarySearch2(array: IntArray, target: Int): Int {
        var l = 0
        var r = array.size-1
        while (l < r) {
            val mid = (l+r) / 2
            if (array[mid] >= target) r = mid
            else l = mid + 1
        }
        return l
    }

    fun binarySearch3(arr: IntArray, target: Int): Int {

        var left = 0
        var right = arr.size-1

        while(left <= right) {
            var mid = (left+right)/2
            if(arr[mid] == target) return mid
            else if (arr[mid] > target) right = mid - 1
            else left = mid + 1
        }

        return -1
    }

    fun binarySearch4(arr: IntArray, target: Int): Int {

        var left = 0
        var right = arr.size-1

        while(left < right) {
            var mid = left + (right-left)/2
            if(arr[mid] == target) return mid
            else if (arr[mid] < target) left = mid + 1
            else right = mid
        }

        return left
    }

    fun subarraysDivByK(nums: IntArray, k: Int): Int {
        val n = nums.size
        var prefixMod = 0
        var result = 0

        // There are k mod groups 0...k-1.
        val modGroups = IntArray(k)
        modGroups[0] = 1

        for (num in nums) {
            // Take modulo twice to avoid negative remainders.
            prefixMod = (prefixMod + num % k + k) % k
            // Add the count of subarrays that have the same remainder as the current
            // one to cancel out the remainders.
            result += modGroups[prefixMod]
            modGroups[prefixMod]++
        }

        return result
    }
}

fun main () {
    println(BinarySearch.binarySearch4(intArrayOf(1,2,3,4,5,6), 1))

}