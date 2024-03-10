package com.android.leetcode.string

import java.util.Stack

object BasicCalculatorII {
    fun calculate(s: String): Int {
        var stack = Stack<Int>()
        var curNum = 0
        var operation = '+'
        var sum = 0

        for (i in 0 until s.length) {
            var char = s[i]
            if (char.isDigit()) {
                curNum = curNum * 10 + char.digitToInt()
            }
            if (!char.isDigit() && char != ' ' || i == s.length - 1) {
                if (operation == '+') {
                    stack.push(curNum)
                }
                if (operation == '-') {
                    stack.push(curNum * -1)
                }
                if (operation == '*') {
                    stack.push(curNum * stack.pop()!!)
                }
                if (operation == '/') {
                    stack.push(stack.pop()!! / curNum)
                }
                operation = char
                curNum = 0
            }
        }

        while (stack.isNotEmpty()) {
            sum += stack.pop()!!
        }

        return sum
    }
}
