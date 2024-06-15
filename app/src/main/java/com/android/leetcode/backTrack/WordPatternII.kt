package com.android.leetcode.backTrack

import java.util.Arrays


object WordPatternII {
    fun wordPatternMatch(pattern: String, s: String): Boolean {
        val symbolMap: MutableMap<Char, String> = HashMap()
        val wordSet: MutableSet<String> = HashSet()

        return isMatch(s, 0, pattern, 0, symbolMap, wordSet)
    }

    private fun isMatch(
        s: String, sIndex: Int, pattern: String, pIndex: Int, symbolMap: MutableMap<Char, String>,
        wordSet: MutableSet<String>
    ): Boolean {
        // Base case: reached end of pattern
        if (pIndex == pattern.length) {
            return sIndex == s.length // True iff also reached end of s
        }

        // Get current pattern character
        val symbol = pattern[pIndex]

        // This symbol already has an associated word
        if (symbolMap.containsKey(symbol)) {
            val word = symbolMap[symbol]
            // Check if we can use it to match s[sIndex...sIndex + word.length()]
            if (!s.startsWith(word!!, sIndex)) {
                return false
            }
            // If it matches continue to match the rest
            return isMatch(s, sIndex + word!!.length, pattern, pIndex + 1, symbolMap, wordSet)
        }

        // This symbol does not exist in the map
        for (k in sIndex + 1..s.length) {
            val newWord = s.substring(sIndex, k)
            if (wordSet.contains(newWord)) {
                continue
            }
            // Create or update it
            symbolMap[symbol] = newWord
            wordSet.add(newWord)
            // Continue to match the rest
            if (isMatch(s, k, pattern, pIndex + 1, symbolMap, wordSet)) {
                return true
            }
            // Backtracking
            symbolMap.remove(symbol)
            wordSet.remove(newWord)
        }
        // No mappings were valid
        return false
    }

    fun wordPatternMatch2(pattern: String, s: String): Boolean {
        val symbolMap = hashMapOf<Char, String>()
        val visited = mutableSetOf<String>()

        fun isMatch(pIndex: Int, sIndex: Int): Boolean {
            var word = ""
            if(pIndex == pattern.length) {
                return sIndex == s.length
            }

            val symbol = pattern[pIndex]
            // check if the word is correct
            if(symbolMap.contains(symbol)) {
                word = symbolMap[symbol]!!
                if(!s.startsWith(word,sIndex)) return false
                return isMatch(pIndex+1,sIndex+word.length)
            }

            // if it doesnt exist in the symbol
            for(i in sIndex+1 until s.length) {
                word = s.substring(sIndex,i)
                if(visited.contains(word)) continue
                symbolMap[symbol] = word
                visited.add(word)
                if(isMatch(pIndex+1,i)) return true
                symbolMap.remove(symbol)
                visited.remove(word)
            }
            return false
        }

        return isMatch(0,0)
    }

    fun wordPatternMatch3(pattern: String, s: String): Boolean {
        val symbols = arrayOfNulls<String>(26)
        Arrays.fill(symbols, "")
        val wordSet: MutableSet<String> = HashSet()

        return isMatch(s, 0, pattern, 0, symbols, wordSet)
    }

    private fun isMatch(
        s: String, sIndex: Int, pattern: String, pIndex: Int,
        symbols: Array<String?>, wordSet: MutableSet<String>
    ): Boolean {
        // Base case: reached end of pattern
        if (pIndex == pattern.length) {
            return sIndex == s.length // True if and only if also reached end of s
        }

        // Get current pattern character
        val symbol = pattern[pIndex]

        // This symbol already has an associated word
        if (symbols[symbol.code - 'a'.code] != "") {
            val word = symbols[symbol.code - 'a'.code]
            // Check if we can use it to match s[sIndex...sIndex + word.length()]
            if (!s.startsWith(word!!, sIndex)) {
                return false
            }
            // If it matches continue to match the rest
            return isMatch(s, sIndex + word!!.length, pattern, pIndex + 1, symbols, wordSet)
        }

        // Count the number of spots the remaining symbols in the pattern take
        var filledSpots = 0
        for (i in pIndex + 1 until pattern.length) {
            val p = pattern[i]
            filledSpots += if (symbols[p.code - 'a'.code] == "") 1 else symbols[p.code - 'a'.code]!!.length
        }

        // This symbol does not have an associated word
        for (k in sIndex + 1..s.length - filledSpots) {
            val newWord = s.substring(sIndex, k)
            if (wordSet.contains(newWord)) continue
            // Create or update it
            symbols[symbol.code - 'a'.code] = newWord
            wordSet.add(newWord)
            // Continue to match the rest
            if (isMatch(s, k, pattern, pIndex + 1, symbols, wordSet)) {
                return true
            }
            // Backtracking
            symbols[symbol.code - 'a'.code] = ""
            wordSet.remove(newWord)
        }
        // No mappings were valid
        return false
    }
}

fun main() {
    WordPatternII.wordPatternMatch("abab", "e")
}