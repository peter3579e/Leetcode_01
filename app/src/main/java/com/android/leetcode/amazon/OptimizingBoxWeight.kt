package com.android.leetcode.amazon

object OptimizingBoxWeight {

    fun minimalHeaviestSetA(arr: Array<Int>): Array<Int> {
        // Write your code here
        val map = hashMapOf<Int,Int>()
        var sum = 0
        for(i in 0 until arr.size) {
            sum = sum + arr[i]
            map[arr[i]] = map.getOrDefault(arr[i],0) + 1
        }

        val newArr = mutableListOf<Int>()
        var count = 0
        arr.sortByDescending { it }
        for(it in arr) {
            sum = sum - it
            count = count + it
            newArr.add(it)
            map[it] = map[it]!! - 1
            if(map[it]!! == 0 && count > sum) break
        }

        return newArr.sorted().toTypedArray()
    }

}

fun main() {
    OptimizingBoxWeight.minimalHeaviestSetA(arrayOf(5,3,2,4,1,2)).forEach {
        print(it)
    }
}