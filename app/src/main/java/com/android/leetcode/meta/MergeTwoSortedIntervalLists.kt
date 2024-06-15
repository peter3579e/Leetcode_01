package com.android.leetcode.meta

import kotlin.math.max

//2024 Question
object MergeTwoIntervalLists {

    /*
    Given A and B two interval lists, A has no overlap inside A and B has no overlap inside B. Write the function to merge two interval lists, output the result with no overlap. Ask for a very efficient solution

    A naive method can combine the two list, and sort and apply merge interval in the leetcode, but is not efficient enough.

    For example,
    A: [1,5], [10,14], [16,18]
                        ^
    B: [2,6], [8,10], [11,20]
                              ^

    [1,6] [8,20]

    output [1,6], [8, 20]

    [1,6] [8,20]

    A: [1,5], [7,14],
                      ^
                        ^
    B: [2,6], [8,10], [11,20]
                        ^

    [1,14]


    ******************
     *****     **** **********
     */



    fun mergeTwoInterval(A: List<Pair<Int,Int>>, B: List<Pair<Int,Int>>): List<Pair<Int,Int>> {

        val ans = mutableListOf<Pair<Int,Int>>()

        var pointer1 = 0
        var pointer2 = 0

        while (pointer1 < A.size || pointer2 < B.size) {
            var interval: Pair<Int,Int>
            if (pointer1 == A.size) {
                interval = B[pointer2]
                pointer2++
            } else if (pointer2 == B.size) {
                interval = A[pointer1]
                pointer1++
            } else if (A[pointer1].first <= B[pointer2].first) {
                interval = A[pointer1]
                pointer1++
            } else {
                interval = B[pointer2]
                pointer2++
            }

            if (ans.isEmpty() || ans.last().second < interval.first) {
                ans.add(interval)
            } else {
                val top = ans.removeLast()
                ans.add(Pair(minOf(top.first,interval.first), max(top.second,interval.second)))
            }
        }

        return ans
    }







    fun mergeTwo(arr1: List<Pair<Int,Int>>, arr2: List<Pair<Int,Int>>) : List<Pair<Int,Int>> {


        var p1 = 0
        var p2 = 0
        val ans = ArrayList<Pair<Int,Int>>()

        fun merge(pair: Pair<Int,Int>) {
            if (ans.isEmpty() || ans[ans.size-1].second < pair.first) {
                ans.add(pair)
            } else {
                val top = ans.removeLast()
                ans.add(Pair(minOf(top.first,pair.first), maxOf(top.second,pair.second)))
            }
        }

        while (p1 < arr1.size || p2 < arr2.size) {
            if (p1 == arr1.size) {
                merge(arr2[p2])
                p2++
            } else if (p2 == arr2.size) {
                merge(arr1[p1])
                p1++
            } else {
                if (arr1[p1].first <= arr2[p2].first) {
                    merge(arr1[p1])
                    p1++
                } else {
                    merge(arr2[p2])
                    p2++
                }
            }
        }


        return ans
    }

}

fun main() {
    print(MergeTwoIntervalLists.mergeTwoInterval(listOf(Pair(1,5), Pair(10, 14), Pair(16,18)), listOf(Pair(2,6), Pair(8,10), Pair(11,20))))
}