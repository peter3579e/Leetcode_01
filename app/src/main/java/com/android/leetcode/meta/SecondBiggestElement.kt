package com.android.leetcode.meta

//2024 meta interview question
object SecondBiggestElement {

    /*
    Given a int 4520901
    Find the second biggest element
    9542100
    9542010

    22345
    54322
    54232
     */

    fun secondBiggestElement(n: IntArray): IntArray {
        n.sortDescending()
        for (i in n.size-1 downTo 0) {
            if (n[i-1] > n[i]) {
                n[i-1] = n[i].also { n[i] = n[i-1] }
                break
            }
        }
        return n
    }
}

fun main() {
    println(SecondBiggestElement.secondBiggestElement(intArrayOf(2,2,3,4,5)).toList())
}