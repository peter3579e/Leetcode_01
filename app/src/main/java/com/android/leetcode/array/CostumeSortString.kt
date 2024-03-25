package com.android.leetcode.array

object CostumeSortString {

    fun customSortString(order: String, s: String): String {

        val sMap = s.groupingBy { it }.eachCount().toMutableMap()

        val ans = StringBuilder()
        for(char in order) {
            if(!sMap.contains(char)) continue
            repeat(sMap[char]!!) {
                ans.append(char)
            }
            sMap.remove(char)
        }

        for((key,value) in sMap) {
            repeat(value) {
                ans.append(key)
            }
        }

        return ans.toString()
    }

    //or
    fun customSortStringBad(order: String, s: String): String {
        val arr = s.toCharArray()
        var newArr = arr.sortedWith() {c1, c2 ->
            order.indexOf(c1).compareTo(order.indexOf(c2))
        }

        return String(newArr.toCharArray())
    }
}