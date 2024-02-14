package com.android.leetcode.array

import java.util.Stack

object RomanToInteger {
    fun romanToInt(s: String): Int {

        var map = hashMapOf<Char, Int>(
            'I' to 1,
            'V' to 5,
            'X' to 10,
            'L' to 50,
            'C' to 100,
            'D' to 500,
            'M' to 1000
        )

        if(s.isEmpty()) return 0
        if(s.length == 1) return map[s[0]]!!

        var stack = Stack<Int>()
        var sum = 0

        for(i in s) {
            if(stack.isEmpty() || map[i]!! <= stack.peek()) {
                stack.push(map[i]!!)
            }else if(map[i]!! > stack.peek()) {
                var num = map[i]!! - stack.pop()
                sum = sum + num
            }
        }

        while(stack.isNotEmpty()){
            sum = sum + stack.pop()
        }


        return sum
    }
}