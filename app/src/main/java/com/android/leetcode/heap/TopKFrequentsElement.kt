package com.android.leetcode.heap

import java.util.PriorityQueue
import java.util.Queue


object TopKFrequentsElement {

    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        if(nums.isEmpty()) return intArrayOf()
        if (nums.size == k) return nums

        var map = hashMapOf<Int,Int>()
        /*
        {2,4}
        {1,3}
        {3,1}
        {4,1}
         */

        for (i in nums) {
            map[i] = map.getOrDefault(i,0) + 1
        }

        val heap = PriorityQueue {
                n1: Int, n2: Int ->
            map[n2]!! - map[n1]!!
        }

        map.forEach { i,j ->
            heap.offer(i)
//            if (heap.size > k) heap.poll()
        }
        var count = 0
        var ans = IntArray(k)

        while (heap.isNotEmpty() && count < k ) {
            ans[count] = heap.poll()!!
            count ++
        }
        return ans
    }

    fun topKFrequent2(nums: IntArray, k: Int): IntArray {
        // O(1) time
        if (k == nums.size) {
            return nums
        }

        // 1. Build hash map: character and how often it appears
        // O(N) time
        val count = hashMapOf<Int,Int>()
        for (n in nums) {
            count[n] = count.getOrDefault(n, 0) + 1
        }

        // init heap 'the less frequent element first'
        val heap = PriorityQueue { n1: Int?, n2: Int? ->
            count[n1]!! - count[n2]!!
        }

        // 2. Keep k top frequent elements in the heap
        // O(N log k) < O(N log N) time
        for (n in count.keys) {
            heap.add(n)
            if (heap.size > k) heap.poll()
        }

        // 3. Build an output array
        // O(k log k) time
        val top = IntArray(k)
        for (i in k - 1 downTo 0) {
            top[i] = heap.poll()!!
        }
        return top
    }


    fun bucketSolve(nums: IntArray, k: Int): IntArray {

        val numToCount = hashMapOf<Int,Int>()

        for (n in nums) {
            numToCount[n] = numToCount.getOrDefault(n,0) + 1
        }

        val arr = List<MutableList<Int>>(nums.size + 1) {mutableListOf()}

        for ((key,value) in numToCount) {
            arr[value].add(key)
        }

        val ans = IntArray(k) {0}
        var index = 0

        for(i in arr.size-1 downTo 0) {
            if(arr[i].isNotEmpty()) {
                for(n in arr[i]) {
                    ans[index] = n
                    index++
                    if(index == k ) break
                }
                if(index == k ) break
            }
        }

        return ans
    }
}

fun main() {
    print(TopKFrequentsElement.bucketSolve(intArrayOf(1), 1).toList())
}