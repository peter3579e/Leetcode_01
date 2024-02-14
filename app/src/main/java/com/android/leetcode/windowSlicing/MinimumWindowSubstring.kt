package com.android.leetcode.windowSlicing


object MinimumWindowSubstring {

    //failed
    fun minWindow(s: String, t: String): String {
        //SDFSDABCDEBANC
        // s = "ADOBEC ODEBA NC", t = "ABC"
        // ADOBEC first window
        // put s sting into a map with each char count Map<Char,count> same as t
        // loop through t, if the value in t is larger then the map[tkey] value or map[tkey] is null return ""
        //  start window size 0 var start o
        // loop through s
        // if mapt.contains(s[i]) the value mapt value -1
        //

        var mapt = t.groupingBy{it}
            .eachCount()
            .toMutableMap()

        var windowSize = Window(Int.MAX_VALUE, 0, 0)
        var start = 0

        for(i in 0 until s.length) {
            if(mapt.contains(s[i])) {
                mapt[s[i]] = mapt[s[i]]!! - 1
                while(checkIfCovered(mapt)) {
                    var size = i - start + 1
                    if(size < windowSize.size) {
                        windowSize.size = size
                        windowSize.start = start
                        windowSize.end = i
                    }
                    if(mapt.contains(s[start])) mapt[s[start]] = mapt[s[start]]!! + 1
                    start++
                }
            }
        }


        return s.substring(windowSize.start..windowSize.end)


    }

    fun minWindow2(s: String, t: String): String? {
        if (s.length == 0 || t.length == 0) {
            return ""
        }

        // Dictionary which keeps a count of all the unique characters in t.
        val dictT: MutableMap<Char, Int> = HashMap()
        for (i in 0 until t.length) {
            val count = dictT.getOrDefault(t[i], 0)
            dictT[t[i]] = count + 1
        }

        // Number of unique characters in t, which need to be present in the desired window.
        val required = dictT.size

        // Left and Right pointer
        var l = 0
        var r = 0

        // formed is used to keep track of how many unique characters in t
        // are present in the current window in its desired frequency.
        // e.g. if t is "AABC" then the window must have two A's, one B and one C.
        // Thus formed would be = 3 when all these conditions are met.
        var formed = 0

        // Dictionary which keeps a count of all the unique characters in the current window.
        val windowCounts: MutableMap<Char, Int> = HashMap()

        // ans list of the form (window length, left, right)
        val ans = intArrayOf(-1, 0, 0)
        while (r < s.length) {
            // Add one character from the right to the window
            var c = s[r]
            val count = windowCounts.getOrDefault(c, 0)
            windowCounts[c] = count + 1

            // If the frequency of the current character added equals to the
            // desired count in t then increment the formed count by 1.
            if (dictT.containsKey(c) && windowCounts[c] == dictT[c]) {
                formed++
            }

            // Try and contract the window till the point where it ceases to be 'desirable'.
            while (l <= r && formed == required) {
                c = s[l]
                // Save the smallest window until now.
                if (ans[0] == -1 || r - l + 1 < ans[0]) {
                    ans[0] = r - l + 1
                    ans[1] = l
                    ans[2] = r
                }

                // The character at the position pointed by the
                // `Left` pointer is no longer a part of the window.
                windowCounts[c] = windowCounts[c]!! - 1
                if (dictT.containsKey(c) && windowCounts[c]!! < dictT[c]!!) {
                    formed--
                }

                // Move the left pointer ahead, this would help to look for a new window.
                l++
            }

            // Keep expanding the window once we are done contracting.
            r++
        }
        return if (ans[0] == -1) "" else s.substring(ans[1], ans[2] + 1)
    }

    fun minWindow3(s: String, t: String): String {

        if(t.length > s.length || s.isEmpty() || t.isEmpty()) return ""

        var tMap = t.groupingBy{it}
            .eachCount()
            .toMutableMap()


        var windowSize = Window(Int.MAX_VALUE, 0, 0)
        var required = tMap.size
        var start = 0
        var formed = 0
        var sMap = hashMapOf<Char,Int>()
        for(end in 0 until s.length) {
            sMap[s[end]] = sMap.getOrDefault(s[end],0) + 1
            if(sMap[s[end]]!! == tMap.getOrDefault(s[end],0)) {
                formed++
            }
            while(start <= end && formed == required) {
                var size = end - start + 1
                if(size < windowSize.size) {
                    windowSize.size = size
                    windowSize.start = start
                    windowSize.end = end
                }
                var c = s[start]
                if(tMap.contains(c)) {
                    sMap[s[start]] = sMap[s[start]]!! - 1
                    if (sMap[c]!! < tMap[c]!!) {
                        formed--
                    }
                }
                start ++
            }
        }


        return if (windowSize.size == Int.MAX_VALUE) "" else s.substring(windowSize.start..windowSize.end)
    }

    data class Window(
        var size: Int = 0,
        var start: Int = 0,
        var end: Int = 0
    )

    fun checkIfCovered(map: MutableMap<Char,Int>): Boolean {
        map.forEach { (key, value) ->
            if(value > 0) return false
        }
        return true
    }
}

fun main() {
    print(MinimumWindowSubstring.minWindow3("aa", "aa"))
}