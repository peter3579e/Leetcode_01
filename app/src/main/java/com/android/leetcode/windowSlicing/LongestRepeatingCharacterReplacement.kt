package com.android.leetcode.windowSlicing

object LongestRepeatingCharacterReplacement {

    // time limit exceed
    fun characterReplacement(s: String, k: Int): Int {
        var lo = 0
        var hi = 1
        var maxWindow = 0

        while(lo <= hi && lo <= s.length && hi <= s.length) {
            if(isVaildString(s.substring(lo,hi), k)) {
                maxWindow = maxOf(maxWindow, hi - lo)
                hi++
            }else {
                ++lo
            }
        }
        return maxWindow
    }

    fun isVaildString(s:String, k: Int): Boolean {
        val max = s.groupingBy{it}
            .eachCount()
            .maxOf{ it.value}

        return s.length - max <= k
    }

    // window slides + binaray search

    fun characterReplacement2(s: String, k: Int): Int {
        var lo = 1
        var hi = s.length + 1

        while (lo + 1 < hi) {
            val mid = lo + (hi - lo) / 2

            if (canMakeValidSubstring(s, mid, k)) {
                lo = mid
            } else {
                hi = mid
            }
        }

        return lo
    }

    private fun canMakeValidSubstring(s: String, substringLength: Int, k: Int): Boolean {
        val freqMap = IntArray(26)
        var maxFrequency = 0
        var start = 0

        for (end in s.indices) {
            freqMap[s[end] - 'A']++

            if (end + 1 - start > substringLength) {
                freqMap[s[start] - 'A']--
                start++
            }

            maxFrequency = maxOf(maxFrequency, freqMap[s[end] - 'A'])
            if (substringLength - maxFrequency <= k) {
                return true
            }
        }

        return false
    }


    //window slides -> slow
    fun characterReplacement3(s: String, k: Int): Int {
        val allLetters = HashSet<Char>()

        // Collect all unique letters
        for (i in s.indices) {
            allLetters.add(s[i])
        }

        var maxLength = 0
        for (letter in allLetters) {
            var start = 0
            var count = 0

            // Initialize a sliding window for each unique letter
            var end = 0
            while (end < s.length) {
                if (s[end] == letter) {
                    // If the letter matches, increase the count
                    count++
                }

                // Bring start forward until the window is valid again
                while (!isWindowValid(start, end, count, k)) {
                    if (s[start] == letter) {
                        // If the letter matches, decrease the count
                        count--
                    }
                    start++
                }

                // At this point, the window is valid, update maxLength
                maxLength = maxOf(maxLength, end + 1 - start)
                end++
            }
        }
        return maxLength
    }

    private fun isWindowValid(start: Int, end: Int, count: Int, k: Int): Boolean {
        return end + 1 - start - count <= k
    }


    //Youtube
    fun characterReplacement4(s: String, k: Int): Int {
        var n = s.length
        var char_counts = IntArray(26)
        var start = 0
        var maxSize = 0
        var maxCount = 0

        for (end in 0..n-1) {
            char_counts[s[end] - 'A'] ++
            maxCount = maxOf(maxCount, char_counts[s[end] - 'A'])
            while ((end - start + 1) - maxCount > k) {
                char_counts[s[start] - 'A'] --
                start++
            }
            maxSize = maxOf(maxSize, end - start + 1)
        }

        return maxSize
    }
}

fun main() {
    print(LongestRepeatingCharacterReplacement.characterReplacement4("AABABBA", 1))
}