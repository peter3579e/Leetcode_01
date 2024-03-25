package com.android.leetcode.meta

object ValidNumber {

    fun isNumber(s: String): Boolean {
        var pointSeen = false
        var eSeen = false
        var numSeen = false
        for(i in s.indices) {
            if(s[i].isDigit()) {
                numSeen = true
            } else if(s[i] == '.') {
                if(eSeen || pointSeen) return false
                pointSeen = true
            } else if(s[i].isE()) {
                if(eSeen || !numSeen) return false
                numSeen = false
                eSeen = true
            } else if (s[i].isSign()){
                if(i != 0 && !s[i-1].isE()) return false
            } else return false
        }
        return numSeen
    }

    fun Char.isE(): Boolean = this == 'e' || this == 'E'
    fun Char.isSign(): Boolean = this == '+' || this == '-'

}