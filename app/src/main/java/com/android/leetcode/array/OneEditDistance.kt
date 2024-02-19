package com.android.leetcode.array

object OneEditDistance {

    fun isOneEditDistance(s: String, t: String): Boolean {
        val ns = s.length
        val nt = t.length

        // Ensure that s is shorter than t.
        if (ns > nt) return isOneEditDistance(t, s)

        // The strings are NOT one edit away distance
        // if the length diff is more than 1.
        if (nt - ns > 1) return false
        for (i in 0 until ns) { // if strings have the same length
            if (s[i] != t[i])
                return if (ns == nt) s.substring(i + 1) == t.substring(i + 1) else s.substring(i) == t.substring(
                    i + 1)
        }

        // If there are no diffs in ns distance
        // The strings are one edit away only if
        // t has one more character.
        return ns + 1 == nt
    }

    fun isOneEditDistance2(s: String, t: String): Boolean {

        var slength = s.length
        var tlength = t.length

        if(slength > tlength) return isOneEditDistance2(t,s)

        if(tlength - slength > 1) return false

        for(i in 0 until s.length) {
            if(s[i] != t[i]) {
                if(slength == t.length) {
                    return if(s.substring(i+1) == t.substring(i+1)) true else false
                } else {
                    return if(s.substring(i) == t.substring(i+1)) true else false
                }
            }
        }

        return slength + 1 == t.length
    }
}

fun main() {
    OneEditDistance.isOneEditDistance2("ab","ba")
}