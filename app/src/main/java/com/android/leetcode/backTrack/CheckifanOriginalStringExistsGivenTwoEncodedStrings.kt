package com.android.leetcode.backTrack

import java.util.Stack

object CheckifanOriginalStringExistsGivenTwoEncodedStrings {
    fun possiblyEquals(s1: String, s2: String): Boolean {

        var pointer1 = 0
        var pointer2 = 0
        val list1 = mutableListOf<Char>()
        val list2 = mutableListOf<Char>()
        var count1 = 0
        var count2 = 0

        while(pointer1 < s1.length || pointer2 < s2.length) {
            val char1: Char? = if(pointer1 < s1.length) s1[pointer1] else null
            val char2: Char? = if(pointer2 < s2.length) s2[pointer2] else null
            if(s1.length == s2.length && char1 != null && char2 != null && !char1.isDigit() && !char2.isDigit() && char1 != char2) return false
            char1?.let{
                if(!it.isDigit()) {
                    count1++
                } else {
                    list1.add(it)
                }
            }
            char2?.let{
                if(!it.isDigit()) {
                    count2++
                } else {
                    list2.add(it)
                }
            }
            pointer1++
            pointer2++
        }

        var lst = mutableListOf<String>()

        fun backTrack(index: Int, list: List<Char>, temp: StringBuilder) {
            /*
            append or +
             */
            if(index == list.size-1) {
                lst.add(temp.toString())
                return
            }

            //append
            backTrack(index+1,list, temp.append(list[index+1]))
            temp.deleteCharAt(temp.lastIndex)
            backTrack(index+1,list, temp.append("+" + list[index+1]))
            temp.deleteRange(temp.lastIndex-1, temp.lastIndex+1)
        }

        if(list1.isNotEmpty()) backTrack(0,list1,StringBuilder(list1[0].toString()))
        val stringList1 = lst
        lst = mutableListOf<String>()
        if(list2.isNotEmpty()) backTrack(0,list2,StringBuilder(list2[0].toString()))
        var stringList2 = lst

        val set1 = mutableSetOf<Int>()

        fun calculation(string: String): Int {
            val stack = Stack<Int>()
            var num = 0
            for( char in string) {
                if(char.isDigit()) {
                    num = num*10 + char.digitToInt()
                } else {
                    stack.push(num)
                    num = 0
                }
            }
            stack.push(num)
            num = 0

            while(stack.isNotEmpty()) {
                num = num + stack.pop()
            }
            return num
        }

        if(stringList1.isEmpty()) set1.add(count1)
        for(s in stringList1) {
            set1.add(calculation(s) + count1)
        }

        if (stringList2.isEmpty() && set1.contains(count2)) return true

        for(s in stringList2) {
            if(set1.contains(calculation(s) + count2)) return true
        }

        return false
    }
}

fun main() {
    CheckifanOriginalStringExistsGivenTwoEncodedStrings.possiblyEquals("x94", "x14")
}