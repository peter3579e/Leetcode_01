package com.android.leetcode.binarySearch

class RandomPickWithWeight(w: IntArray) {
    val N = w.size
    val preSum: IntArray = w

    init {
        for (i in 1 until N) {
            preSum[i] += preSum[i - 1]
        }
    }

    fun pickIndex(): Int {
        val weight = (1..preSum[N - 1]).random()

        var lo = 0; var hi = N - 1
        while (lo <= hi) {
            val mid = (hi + lo) / 2
            if (preSum[mid] >= weight) {
                hi = mid - 1
            } else {
                lo = mid + 1
            }
        }

        return lo
    }
}

fun main() {
    var random = RandomPickWithWeight(intArrayOf(1,3))
    random.pickIndex()
    random.pickIndex()
    random.pickIndex()
    random.pickIndex()
}