package com.android.leetcode.stack

import java.lang.StringBuilder
import java.util.Stack

object EvaluateReversePolishNotation {
    fun evalRPN(tokens: Array<String>): Int {
        val stack = Stack<Int>()
        for(s in tokens) {
            var num = 0
            if(s.length == 1 && !s[0].isDigit()) {
                val n1 = stack.pop()
                val n2 = stack.pop()
                num = s[0].calculation(n1,n2)
            } else {
                num = s.toInt()
            }
            stack.push(num)
        }

        return stack.pop()
    }

    private fun Char.calculation(n1: Int, n2: Int): Int {
        return when(this) {
            '+' -> n1 + n2
            '-' -> n1 - n2
            '*' -> n1 * n2
            '/' -> n1 / n2
            else -> 0
        }
    }
}

fun main() {
    val s = StringBuilder()
    s.toString()
    EvaluateReversePolishNotation.evalRPN(arrayOf("4","13","5","/","+"))
}