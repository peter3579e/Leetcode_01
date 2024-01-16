package com.android.leetcode.backTrack

object CombinationSum {
    var answer = mutableListOf(listOf<Int>())

    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        if(candidates.isEmpty()) return listOf(listOf<Int>())

        var newArray = candidates.sortedArray() // O(NlogN)

        backTrack(0, newArray, 0, target, mutableListOf<Int>())

        answer.removeAt(0)

        return answer
    }

    private fun backTrack(start: Int, array: IntArray, pointer:Int, target: Int, tempList: MutableList<Int>) {
        if(start > target) {
            return
        } else if (start == target) {
            answer.add(tempList.toList())
            return
        }
        for(i in pointer..array.size-1) {
            tempList.add(array[i])
            backTrack(start+array[i], array, i, target, tempList)
            tempList.removeAt(tempList.size-1)
        }
    }
}

fun main() {
    print(CombinationSum.combinationSum(intArrayOf(2,3,6,7), 7))
}