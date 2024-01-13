package com.android.leetcode.backTrack

object Combinations {
    private var combination = mutableListOf(listOf<Int>())
    var range = 0
    var listSize = 0

    fun combine(n: Int, k: Int): List<List<Int>> {
        if(n == 0) return listOf(listOf())
        range = n
        listSize = k
        backTrack(1, mutableListOf<Int>())
        combination.removeAt(0)

        return combination
    }

    private fun backTrack(start: Int, temp: MutableList<Int>) {
        if (temp.size == listSize) {
            combination.add(temp.toList())
            return
        }

        for(i in start..range) {
            var cur = i
            temp.add(cur)
            backTrack(i+1, temp)
            temp.removeAt(temp.size-1)
        }
    }
}

fun main () {
    println(Combinations.combine(4,2))
}