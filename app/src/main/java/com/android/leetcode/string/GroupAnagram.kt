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

    fun groupAnagrams3(strs: Array<String>): List<List<String>> {
        if(strs.isEmpty() || strs.size == 1) return listOf()
        val map = hashMapOf<String,MutableList<String>>()

        for(string in strs) {
            val alphaArray = IntArray(26){0}
            for(char in string) {
                alphaArray[char - 'a'] = alphaArray[char - 'a'] + 1
            }
            // form the string
            val newString = StringBuilder()
            for(i in 0 until alphaArray.size) {
                val num = alphaArray[i]
                if(num != 0) {
                    repeat(num) {
                        newString.append("$i%")
                    }
                }
            }
            val str = newString.toString()
            map[str] = map.getOrDefault(str, mutableListOf()).apply {
                this.add(string)
            }
        }

        val ans = mutableListOf<List<String>>()

        for((key,value) in map) {
            ans.add(value)
        }

        return ans
    }
}

fun main() {
    print(GroupAnagram.groupAnagrams3(arrayOf("eat","tea","tan","ate","nat","bat")))
}