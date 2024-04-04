package com.android.leetcode.backTrack

object SplittingAStringIntoDescendingConsecutiveValues {
    fun splitString(s: String): Boolean {

        fun backtrack(i:Int,prev:Long):Boolean {

            if(i==s.length){
                return true
            }

            var curr = 0L

            for(j in i until s.length){
                curr = curr * 10 + s[j].digitToInt()
                if(prev<=curr) break
                if(curr+1==prev && backtrack(j+1,curr)) return true
            }

            return false

        }

        var sub = 0L

        for(i in 0 until s.length-1){
            sub = sub * 10 + s[i].digitToInt()
            if(backtrack(i+1,sub)) return true
        }

        return false
    }

    fun splitString2(s: String): Boolean {
        fun backTrack(prev: Long, index: Int): Boolean {

            if(index == s.length) {
                return true
            }

            var cur = 0L

            for (i in index until s.length) {
                cur = cur*10 + s[i].digitToInt()

                if(cur >= prev) break

                if(cur+1 == prev && backTrack(cur,index+1)) return true
            }

            return false
        }

        var temp = 0L

        for(i in 0 until s.length-1) {
            temp = temp * 10 + s[i].digitToInt()
            if(backTrack(temp, i+1)) return true
        }

        return false
    }
}

fun main() {
    SplittingAStringIntoDescendingConsecutiveValues.splitString2("050043")
}