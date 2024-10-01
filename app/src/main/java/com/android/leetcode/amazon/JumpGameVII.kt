package com.android.leetcode.amazon

import java.util.PriorityQueue
import java.util.TreeMap

fun canReach(s: String, minJump: Int, maxJump: Int): Boolean {

    val n: Int = s.length
    var pre = 0
    val dp = BooleanArray(n)
    dp[0] = true
    for (i in 1 until n) {
        if (i >= minJump && dp[i - minJump]) pre++
        if (i > maxJump && dp[i - maxJump - 1]) pre--
        dp[i] = pre > 0 && s[i] == '0'
    }
    return dp[n - 1]
}

fun minimumKeypresses(s: String): Int {
    var ans = 0
    val map = hashMapOf<Char,Int>()

    for(char in s) {
        map[char] = map.getOrDefault(char, 0) + 1
    }

    val sortedMap = map.toList().sortedByDescending {
        (key, value) -> value
    }

    var count = 1
    for((key,value) in sortedMap) {
        ans = ans + if(count <= 9) value else value * (Math.ceil(count.toDouble()/9.toDouble())).toInt()
        count++
    }

    return ans
}

fun main() {
    minimumKeypresses("apple")
}