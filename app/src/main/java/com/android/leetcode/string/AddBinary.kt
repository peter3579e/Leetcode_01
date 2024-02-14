package com.android.leetcode.string

import java.math.BigInteger

object AddBinary {
    fun addBinary(a: String, b: String): String {

        var pointerA = a.length-1
        var pointerB = b.length-1
        var one = 0
        var ans = ArrayDeque<String>()

        while(pointerA >= 0 && pointerB >= 0) {
            var sum = a[pointerA].toInteger() + b[pointerB].toInteger() + one
            if(sum > 1) {
                one = 1
                sum = sum % 2
            }else {
                one = 0
            }
            ans.addFirst(sum.toString())
            pointerA--
            pointerB--
        }

        while(pointerA >= 0) {
            var sum = a[pointerA].toInteger() + one
            if(sum > 1) {
                one = 1
                sum = sum % 2
            }else {
                one = 0
            }
            ans.addFirst(sum.toString())
            pointerA--
        }

        while(pointerB >= 0) {
            var sum = b[pointerB].toInteger() + one
            if(sum > 1) {
                one = 1
                sum = sum % 2
            }else {
                one = 0
            }
            ans.addFirst(sum.toString())
            pointerB--
        }

        if(one > 0) ans.addFirst("1")

        var ansString = StringBuilder()

        while(ans.isNotEmpty()) {
            ansString.append(ans.removeFirst())
        }

        return ansString.toString()
    }

    fun Char.toInteger():Int {
        return this.toString().toInt()
    }
}

fun main() {
    print(AddBinary.addBinary("1010","1011"))
}