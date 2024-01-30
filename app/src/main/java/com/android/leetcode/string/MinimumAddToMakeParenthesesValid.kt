package com.android.leetcode.string

// TIME O(N)
// SPACE O(1)
object MinimumAddToMakeParenthesesValid {
    fun minAddToMakeValid(s: String): Int {


        var ans = 0
        var bal = 0

        for(i in 0..s.length-1) {
            if(s[i] == '(' ) {
                bal += 1
            } else {
                bal -= 1
            }

            if(bal == -1) {
                ans ++
                bal ++
            }
        }


        return  ans + bal
    }
}