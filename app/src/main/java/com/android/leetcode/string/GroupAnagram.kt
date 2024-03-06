package com.android.leetcode.string

import java.util.Arrays


object GroupAnagram {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        if(strs.isEmpty() || strs.size == 1) return listOf(strs.toList())

        var map = hashMapOf<String, MutableList<String>>()

        for(string in strs) {
            var char = string.toCharArray().sorted().toString()
            map[char] = map.getOrDefault(char, mutableListOf()).apply {
                this.add(string)
            }
        }

        var ans = mutableListOf<List<String>>()

        map.forEach {key,value ->
            ans.add(value)
        }
        return ans
    }

    fun groupAnagrams2(strs: Array<String>): List<List<String>> {
        if (strs.isEmpty()) return listOf(listOf())
        val ans: MutableMap<String, MutableList<String>?> = HashMap()
        for (s in strs) {
            val count = IntArray(26){ 0 }
            for (c in s) {
                count[c.code - 'a'.code]++
            }
            val sb = StringBuilder("")
            for (i in 0..25) {
                sb.append('#')
                sb.append(count[i])
            }
            val key = sb.toString()
            if (!ans.containsKey(key)) ans[key] = ArrayList<String>()
            ans[key]!!.add(s)
        }

        var ans1 = mutableListOf<List<String>>()
        for((key,value) in ans) {
            ans1.add(value!!.toList())
        }
        return ans1
    }
}

fun main() {
    print(GroupAnagram.groupAnagrams(arrayOf("bdddddddddd","bbbbbbbbbbc")))
}