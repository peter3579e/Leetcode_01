package com.android.leetcode.windowSlicing

object PermutationInString {
    fun checkInclusion(s1: String, s2: String): Boolean {
        if(s1.length > s2.length) return false

        val s1Map = s1.groupingBy{ it }.eachCount().toMutableMap()
        var s2Map = hashMapOf<Char,Int>()
        var count = 0
        for(end in 0 until s2.length) {
            var start = end
            while(s1Map.contains(s2[start])) {
                s2Map[s2[start]] = s2Map.getOrDefault(s2[start],0) + 1
                if(s2Map[s2[start]]!! == s1Map[s2[start]]!!) count++ else if (s2Map[s2[start]]!! > s1Map[s2[start]]!!) break
                if(count == s1Map.size) return true
                start++
            }
            count = 0
            s2Map = hashMapOf<Char,Int>()
        }
        return false
    }

    fun checkInclusionOptimal(s1: String, s2: String): Boolean {

        if(s1.length > s2.length) return false

        val s1Arr = IntArray(26) { 0 }
        val s2Arr = IntArray(26) { 0 }

        for(i in 0 until s1.length) {
            s1Arr[s1[i] - 'a'] ++
            s2Arr[s2[i] - 'a'] ++
        }

        var matches = 0

        for(i in 0 until 26) {
            if(s1Arr[i] == s2Arr[i]) matches++
        }

        var start = 0

        for(end in s1.length until s2.length) {
            if(matches == 26) return true
            var char = s2[end]
            s2Arr[char - 'a'] ++
            if(s1Arr[char - 'a'] == s2Arr[char - 'a']) {
                matches++
            } else if(s1Arr[char - 'a'] + 1 == s2Arr[char - 'a']) {
                matches--
            }

            char = s2[start]
            s2Arr[char - 'a'] --
            if(s1Arr[char - 'a'] == s2Arr[char - 'a']) {
                matches++
            } else if(s1Arr[char - 'a'] - 1 == s2Arr[char - 'a']) {
                matches--
            }
            start++
        }

        return false
    }
}


class Solution {
    fun checkInclusion(s1: String, s2: String): Boolean {
        if (s1.length > s2.length) return false
        val s1map = IntArray(26)
        val s2map = IntArray(26)
        for (i in 0 until s1.length) {
            s1map[s1[i].code - 'a'.code]++
            s2map[s2[i].code - 'a'.code]++
        }
        var count = 0
        for (i in 0..25) {
            if (s1map[i] == s2map[i]) count++
        }
        for (i in 0 until s2.length - s1.length) {
            val r = s2[i + s1.length].code - 'a'.code
            val l = s2[i].code - 'a'.code
            if (count == 26) return true
            s2map[r]++
            if (s2map[r] == s1map[r]) {
                count++
            } else if (s2map[r] == s1map[r] + 1) {
                count--
            }
            s2map[l]--
            if (s2map[l] == s1map[l]) {
                count++
            } else if (s2map[l] == s1map[l] - 1) {
                count--
            }
        }
        return count == 26
    }
}

fun main() {
    PermutationInString.checkInclusionOptimal("adc","dcda")
}