package com.android.leetcode.meta

import java.util.Stack

object BasicCalculator {

    /*
    (1+(4+5+2)-3)+(6+8)

    stack 0,1,
    res 12
    sign 1
    cur 0
 */
    fun calculate(s: String): Int {

        var res = 0
        var cur = 0
        var sign = 1
        val stack = Stack<Int>()

        for (char in s) {
            if (char.isDigit()) {
                cur = cur * 10 + char.digitToInt()
            } else if (char in listOf('+','-')) {
                res = res + cur * sign

                sign = if (char == '-') -1 else 1

                cur = 0
            } else if (char == '(') {
                stack.push(res)
                stack.push(sign)

                res = 0
                sign = 1
            } else if (char == ')') {
                res = res + cur * sign

                res = res * stack.pop()!!
                res = res + stack.pop()!!

                cur = 0
                sign = 1
            }
        }

        return res + cur*sign
    }
}