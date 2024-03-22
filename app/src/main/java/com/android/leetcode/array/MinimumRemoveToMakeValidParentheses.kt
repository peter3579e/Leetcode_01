package com.android.leetcode.array

import java.util.Deque
import java.util.Stack


object MinimumRemoveToMakeValidParentheses {
    fun minRemoveToMakeValid(s: String): String {
        if(s.isEmpty() || s.length == 1) return ""

        var stack = Stack<Pair<Char, Int>>()
        var par = false
        var vaild = false
        var ans = StringBuilder()

        for(i in 0 until s.length) {
            var char = s[i]
            ans.append(char)
            if(char == '(') {
                par = true
                stack.push(Pair('(',i))
            } else if (char == ')' && stack.isNotEmpty() && stack.peek().first == '(') {
                par = true
                vaild = true
                stack.pop()!!
            } else if (char == ')') {
                par = true
                stack.push(Pair(')',i))
            }
        }

        while(stack.isNotEmpty()) {
            var index = stack.pop()!!.second
            ans.deleteAt(index)
        }

        return if(!par) s
        else if(par && vaild) {
            ans.toString()
        }else {
            ""
        }
    }

    fun minRemoveToMakeValid2(s: String): String {
        val indexesToRemove = mutableSetOf<Int>()
        val stack = Stack<Int>()
        for (i in 0 until s.length) {
            if (s[i] == '(') {
                stack.push(i)
            }
            if (s[i] == ')') {
                if (stack.isEmpty()) {
                    indexesToRemove.add(i)
                } else {
                    stack.pop()
                }
            }
        }
        // Put any indexes remaining on stack into the set.
        while (!stack.isEmpty()) indexesToRemove.add(stack.pop())
        val sb = java.lang.StringBuilder()
        for (i in 0 until s.length) {
            if (!indexesToRemove.contains(i)) {
                sb.append(s[i])
            }
        }
        return sb.toString()
    }

    fun minRemoveToMakeValid3(s: String): String {
        if(s.isEmpty()) return ""

        val stack = Stack<Pair<Char,Int>>()
        val ans = StringBuilder()

        for(i in 0 until s.length) {
            val char = s[i]
            ans.append(char)
            if(char == ')') {
                if(stack.isEmpty()) {
                    stack.push(Pair(char,i))
                    continue
                }
                if(stack.peek()!!.first == '(') {
                    stack.pop()!!
                }else {
                    stack.push(Pair(char,i))
                }
            }else if(char == '(') {
                stack.push(Pair(char,i))
            }
        }


        while(stack.isNotEmpty()) {
            val index = stack.pop()!!.second
            ans.deleteAt(index)
        }

        return ans.toString()
    }
}

 fun main() {
     MinimumRemoveToMakeValidParentheses.minRemoveToMakeValid3("))((")
 }