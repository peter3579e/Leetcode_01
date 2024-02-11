package com.android.leetcode.array

import java.util.PriorityQueue
import kotlin.math.sqrt

object KClosestPointsToOrigin {
    fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
        val map = hashMapOf<IntArray, Int>()

        for(i in points) {
            var distance = Math.pow(i[0].toDouble(),2.0) + Math.pow(i[1].toDouble(), 2.0)
            map[i] = distance.toInt()
        }

        val heap = PriorityQueue {n1: IntArray, n2: IntArray ->
            map[n2]!!.compareTo(map[n1]!!)
        }

        map.forEach { key, value ->
            heap.offer(key)
            if(heap.size > k) heap.poll()
        }

        var ans = Array(k) { IntArray(2) }


        for (i in 0 until k) {
            ans[i] = heap.poll()
        }

        return ans
    }
}

fun main() {
    println(KClosestPointsToOrigin.kClosest(arrayOf(intArrayOf(1,3), intArrayOf(-2,2)), 1)[0].toList())
}