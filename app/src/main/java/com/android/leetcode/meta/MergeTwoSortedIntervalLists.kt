package com.android.leetcode.meta

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

    output [1,6], [8, 20]

    [1,6] [8,20]
     */



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
    print(MergeTwoIntervalLists.mergeTwo(listOf(Pair(1,5), Pair(10, 14), Pair(16,18)), listOf(Pair(2,6), Pair(8,10), Pair(11,20))))
}