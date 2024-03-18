package com.android.leetcode.array

import java.util.Stack
import java.util.TreeMap

object ExclusiveTimeOfFunctions {
    fun exclusiveTime(n: Int, logs: List<String>): IntArray {
        var end = 0
        var stack = Stack<Int>()
        var res = IntArray(n){0}
        for( string in logs) {
            val charArray = string.split(":")
            var id = charArray[0].toInt()
            var time = charArray[2].toInt()
            var start = if (charArray[1] == "start") true else false
            if(start) {
                if(stack.isNotEmpty()) {
                    val prevId = stack.peek()!!
                    res[prevId] = res[prevId] + (time - end)
                }
                stack.push(id)
                end = time
            }else {
                val prev = stack.pop()!!
                res[prev] = res[prev] + (time+1 - end)
                end = time + 1
            }
        }

        return res
    }
}

fun main() {
    var tree = TreeMap<Int,Int>()
}