package com.android.leetcode.meta

class MinimumRemoveParathness {

    fun minRemoveToMakeValid(s: String): String {
        // time: O(n)
        // space: O(1)
        var openCount = 0
        val sb = StringBuilder()
        for (index in s.indices) {
            val c = s[index]
            if (c == '(') {
                openCount++
                sb.append(c)
            } else if (c == ')') {
                if (openCount == 0) {
                    // it must be invalid and we would skip it
                } else {
                    openCount--
                    sb.append(c)
                }
            } else {
                sb.append(c)
            }
        }

        var index = sb.lastIndex
        while (openCount > 0 && index >= 0) {
            val c = sb[index]
            if (c == '(') {
                sb.deleteCharAt(index)
                openCount--
            }
            index--
        }
        return sb.toString()
    }


}