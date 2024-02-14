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


        //space (O k ^ n-1)
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
    var ans = mutableListOf<List<Int>>()
    fun backTrack2(nums:IntArray, list:MutableList<Int>) {
        if(list.size == nums.size) {
            ans.add(list.toList())
            return
        }

        for(i in 0 until nums.size) {
            var cur = nums[i]
            if (list.isEmpty() || !list.contains(cur)) {
                list.add(cur)
            }else continue
            backTrack2(nums, list)
            list.removeAt(list.size-1)
        }
    }
}

fun main () {

    //123
    // if list is empty we add 1
    // if num = list[0] we continue
    // if num = list[list.size-1] continue
    println(Combinations.backTrack2( intArrayOf(1,2,3), mutableListOf()))
    println(Combinations.ans)
}