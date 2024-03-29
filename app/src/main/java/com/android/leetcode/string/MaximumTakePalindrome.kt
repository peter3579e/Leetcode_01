package com.android.leetcode.string

object MaximumTakePalindrome {

    // a,a,b
    //a,b,b,c,b,a,
    // a,e,d,d,f,e,c

    /*
    * 1. if s.length < 2 return true
    * 2. use two pointer from the start and the end of the string,
    * 3. if the elements are different -> ignore one of them at a time to see if it will become a palindrome
    * 4. if not return false
    * 5. return true at the end
    * */

    fun validPalindrome(s: String): Boolean {
        if (s.length <= 2) return true
        var first = 0
        var second = s.length-1

        while (first < second) {
            if (s[first] != s[second]) {
                if(!checkPalindrome(first+1, second, s) && !checkPalindrome(first, second-1, s)) return false
            }
            first++
            second--
        }
        return true
    }

    fun checkPalindrome(start: Int, end: Int, s: String): Boolean {
        var first = start
        var second = end
        while(first < second) {
            if(s[first] != s[second]) return false
            first++
            second--
        }
        return true
    }

    fun validPalindrome2(s: String): Boolean {

        var left = 0
        var right = s.length-1

        while (left < right) {
            if(s[left] != s[right]) {
                if(!checkPalidrom(left+1,right,s) && !checkPalidrom(left,right-1,s)) return false
            }
            left++
            right--
        }

        return true
    }

    fun checkPalidrom(left:Int, right:Int, s: String): Boolean {
        var start = left
        var end = right
        while(start < end) {
            if(s[start] != s[end]) return false
            start ++
            end --
        }
        return true
    }
}

fun main() {
    print(MaximumTakePalindrome.validPalindrome2("deeee"))
}