package com.android.leetcode.meta

//2024 Question
object MergeTwoIntervalLists {

    /*
    Given A and B two interval lists, A has no overlap inside A and B has no overlap inside B. Write the function to merge two interval lists, output the result with no overlap. Ask for a very efficient solution

    A naive method can combine the two list, and sort and apply merge interval in the leetcode, but is not efficient enough.

    For example,
    A: [1,5], [10,14], [16,18]
    B: [2,6], [8,10], [11,20]

    output [1,6], [8, 20]
     */

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