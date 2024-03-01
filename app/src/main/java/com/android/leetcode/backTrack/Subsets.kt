package com.android.leetcode.backTrack

object Subsets {
    var ans = mutableListOf<List<Int>>(listOf())
    fun subsets(nums: IntArray): List<List<Int>> {
        if(nums.isEmpty()) return ans
        backTrack(nums,mutableListOf(),0)
        return ans
    }

    fun backTrack(nums: IntArray, temp: MutableList<Int>, index: Int) {

        for(i in index until nums.size) {
            var cur = nums[i]
            temp.add(cur)
            ans.add(temp.toList())
            backTrack(nums,temp,i+1)
            temp.removeLast()
        }
    }
}

fun main() {
    var array = ArrayDeque<Char>()
    array.addFirst('0')
    array.addLast('1')
    print(String(array.toCharArray()))
    var array1 = arrayListOf<Int>()
//    Subsets.subsets(intArrayOf(1,2,3))
}