package com.android.leetcode.backTrack

object GenerateParentheses {
    fun generateParenthesis(n: Int): List<String> {

        val ans = mutableListOf<String>()

        /*
        b '', "", 0, 0
        b '(', "", 0, 1
        b '(', "(", 0, 2
        b
         */

        fun backTrack( temp: StringBuilder, numLeft: Int, numRight: Int) {
            if(temp.length == n*2) {
                if(numLeft == numRight) {
                    ans.add(temp.toString())
                }
                return
            }

            // add (
            backTrack(temp.append('('),numLeft,numRight+1)
            temp.deleteAt(temp.length-1)

            if(numRight > numLeft) {
                backTrack(temp.append(')'),numLeft+1,numRight)
                temp.deleteAt(temp.length-1)
            }
        }
        backTrack(StringBuilder(),0,0)
        return ans
    }
}

fun main() {
    print(GenerateParentheses.generateParenthesis(1))
}