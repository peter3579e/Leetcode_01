package com.android.leetcode.array

object LongestSubstringwithAtMostKDistinctCharacters {
    fun lengthOfLongestSubstringKDistinct(s: String, k: Int): Int {

        var map = hashMapOf<Char,Int>()
        var windowSize = 0
        var start = 0

        for(end in 0 until s.length) {
            var cur = s[end]
            map[cur] = map.getOrDefault(cur,0) + 1
            while (map.size > k) {
                windowSize = maxOf(windowSize, end-start)
                cur = s[start]
                if(map[cur]!! - 1 == 0) {
                    map.remove(cur)
                }else {
                    map[cur] = map[cur]!! - 1
                }
                start++
            }
        }

        return windowSize
    }
}

fun main() {
    LongestSubstringwithAtMostKDistinctCharacters.lengthOfLongestSubstringKDistinct("eceba",2)
}