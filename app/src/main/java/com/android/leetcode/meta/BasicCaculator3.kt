package com.android.leetcode.meta

import java.util.Stack

object BasicCaculator3 {

    fun calculate(s: String): Int {

        var num = 0
        var op = '+'
        var stack = Stack<Any>()

        fun helper(oper: Char, num: Int) {
            when {
                oper == '+' -> stack.push(num)
                oper == '-' -> stack.push(-1*num)
                oper == '*' -> stack.push(stack.pop() as Int * num)
                oper == '/' -> stack.push(stack.pop() as Int / num)
            }
        }

        for(char in s) {
            if (char.isDigit()) {
                num = num*10 + char.digitToInt()
            } else if (char == '(') {
                stack.push(op)
                num = 0
                op = '+'
            } else if (char in listOf('+','-','*','/',')')) {
                helper(op,num)
                if (char == ')') {
                    num = 0
                    while (stack.peek() is Int) {
                        num = num + stack.pop() as Int
                    }
                    op = stack.pop() as Char
                    helper(op,num)
                }
                num = 0
                op = char
            }
        }
        helper(op,num)

        return stack.sumOf { it as Int }
    }
}

fun main() {
    BasicCaculator3.calculate("2*(5+5*2)/3+(6/2+8)")
}