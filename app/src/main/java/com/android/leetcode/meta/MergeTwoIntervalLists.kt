package com.android.leetcode.meta

object MergeTwoIntervalLists {

    fun mergerIntervals(A: List<Pair<Int, Int>>, B: List<Pair<Int, Int>>) {
        var i = 0
        var j = 0
        val res = mutableListOf<Pair<Int, Int>>()

        while (i < A.size || j < B.size) {
            val curr: Pair<Int, Int>
            if (i == A.size) {
                curr = B[j]
                j++
            } else if (j == B.size) {
                curr = A[i]
                i++
            } else if (A[i].first < B[j].first) {
                curr = A[i]
                i++
            } else {
                curr = B[j]
                j++
            }
            if (res.isNotEmpty() && res.last().second >= curr.first) {
                res[res.lastIndex] = Pair(res.last().first, maxOf(res.last().second, curr.second))
            } else {
                res.add(curr)
            }
        }
    }
}

fun main() {
    MergeTwoIntervalLists.mergerIntervals(listOf(Pair(1,5), Pair(10, 14), Pair(16,18)), listOf(Pair(2,6), Pair(8,10), Pair(11,20)))
}