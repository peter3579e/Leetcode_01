package com.android.leetcode.amazon

import java.util.PriorityQueue

fun appendCharacters(s: String, t: String): Int {

    val sorted = s.toCharArray().sortedBy {
        t.indexOf(it)
    }.joinToString("")

    var index = 0
    while (index < s.length && index < t.length)  {
        if(s[index] != t[index]) {
            return t.length - index
        }
        index++
    }

    return 0
}