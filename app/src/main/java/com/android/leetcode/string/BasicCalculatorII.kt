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

    fun calculate3(s: String): Int {
        if(s.isEmpty()) return 0
        var i = 0
        var preCal = '+'
        val stack = Stack<Int>()
        while(i < s.length) {
            if(s[i].isDigit()) {
                var temp = 0
                while(i < s.length && s[i].isDigit()) {
                    temp = temp*10 + s[i].digitToInt()
                    i++
                }
                i--
                var newNum = 0
                when {
                    preCal == '+' -> stack.push(temp)
                    preCal == '-' -> {
                        stack.push(-temp)
                    }
                    preCal == '/' -> {
                        newNum = stack.pop()!! / temp
                        stack.push(newNum)
                    }
                    preCal == '*' -> {
                        newNum = stack.pop()!! * temp
                        stack.push(newNum)
                    }
                }
            } else if(s[i] != ' ') {
                preCal = s[i]
            }
            i++
        }

        var ans = 0

        while(stack.isNotEmpty()) {
            ans = ans + stack.pop()!!
        }

        return ans
    }
}

fun main() {
    BasicCalculatorII.calculate3("12-3*4")
}
