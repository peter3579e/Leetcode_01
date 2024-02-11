package com.android.leetcode.string

import java.util.Stack
import kotlin.math.sqrt

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

    fun minAddToMakeValid2(s: String): Int {

        var stack = Stack<Char>()

        for(i in s) {
            if(i == ')') {
                if(stack.isNotEmpty() && stack.peek() == '(') {
                    stack.pop()
                    continue
                }
            }
            stack.push(i)
        }

        return  stack.size
    }
}

fun main() {
    println(MinimumAddToMakeParenthesesValid.minAddToMakeValid("((("))
}