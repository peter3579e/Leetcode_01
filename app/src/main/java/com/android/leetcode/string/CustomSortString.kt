package com.android.leetcode.string

object CustomSortString {
    fun customSortString(order: String, s: String): String {

        val array = s.toCharArray()
        // Define the custom comparator
        val newArray  = array.sortedWith { c1, c2 ->
            // The index of the character in order determines the value to be sorted by
            order.indexOf(c1) - order.indexOf(c2)
        }

        return String(newArray.toCharArray())
    }

    fun optimalCustomSortString(order: String, s: String): String {

        val sMapper = s.groupingBy { it }
            .eachCount()
            .toMutableMap()
        val ans = StringBuilder()
        for (c in order) {
            if (sMapper.contains(c)) {
                repeat(sMapper[c]!!) {
                    ans.append(c)
                }
                sMapper.remove(c)
            }
        }

        for ((key, value) in sMapper) {
            repeat(value) {
                ans.append(key)
            }
        }

        return ans.toString()
    }
}