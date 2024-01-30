package com.android.leetcode.string

object PalidromePermutation {
    //failed
    fun canPermutePalindromeFailed(s: String): Boolean {
        var lo = 0
        var hi = s.length - 1

        while(lo <= hi) {
            if(s[lo] != s[hi]) return false
            lo++
            hi--
        }

        return true
    }

    fun canPermutePalindrome(s: String): Boolean {
        var count = 0
        for (i in 0 until 128) {
            if (count > 1) break
            var ct = 0
            for (j in s.indices) {
                if (s[j] == i.toChar()) {
                    ct++
                }
            }
            count += ct % 2
        }
        return count <= 1
    }


    fun canPermutePalindromeOptimal(s: String): Boolean {
        val map = HashMap<Char, Int>()

        // Count occurrences of each character
        for (char in s) {
            map[char] = map.getOrDefault(char, 0) + 1
        }

        // Remove characters with even counts
        val filteredMap = map.filterValues { it % 2 != 0 }

        // Check if the resulting map has at most one character with an odd count
        return when {
            s.length % 2 == 0 -> filteredMap.isEmpty()
            else -> filteredMap.size <= 1
        }
    }

    fun canPermutePalindromeOptimal2(s: String): Boolean {
        val map = HashMap<Char, Int>()

        // Count occurrences of each character
        for (char in s) {
            map[char] = map.getOrDefault(char, 0) + 1
        }

        return if (s.length % 2 == 0) {
            stringIsEven(map)
        }else{
            stringIsOdd(map)
        }
    }

    private fun stringIsOdd(map: HashMap<Char, Int>): Boolean {
        return map.filterValues { it % 2 != 0 }.size <= 1
    }

    private fun stringIsEven(map: HashMap<Char, Int>): Boolean {
        return map.filterValues { it % 2 != 0 }.isEmpty()
    }


    fun canPermutePalindromeOptimal3(s: String): Boolean {
        val map = IntArray(26){0}

        // Count occurrences of each character
        for (char in s) {
            map[char - 'a'] = map[char - 'a'] + 1
        }

        return if (s.length % 2 == 0) {
            stringIsEvenV2(map)
        }else{
            stringIsOddV2(map)
        }
    }

    private fun stringIsEvenV2(map: IntArray): Boolean {
        map.forEach {
            if (it % 2 != 0) return false
        }
        return true
    }

    private fun stringIsOddV2(map: IntArray): Boolean {
        var seenOdd = false
        map.forEach {
            if (seenOdd && it % 2 == 1) {
                return false
            }else if (!seenOdd && it % 2 == 1) {
                seenOdd = true
            }
        }
        return true
    }
}

fun main() {
    print(PalidromePermutation.canPermutePalindromeOptimal3("aab"))
}