package com.android.leetcode.general

object Pow {


    // run time exceed
    fun myPow1(x: Double, n: Int): Double {
        if(n == 0) return 1.0
        var num = if(n < 0) 1.0/x else x
        var multiply = num

        repeat(Math.abs(n)-1) {
            num = num * multiply
        }

        return num
    }

    fun myPow(x: Double, n: Int): Double {
        if(n == 0) return 1.0
        var num = if(n < 0) 1.0/x else x

        return pow(num,Math.abs(n.toDouble()))
    }

    private fun pow (x: Double, n: Double): Double {
        if  (n == 1.0) return x
        var sum = 0.0
        if(n%2 == 0.0) {
            sum = pow(x*x, n/2)
        }else {
            sum = x * pow(x*x, (n-1)/2)
        }

        return sum
    }
}

fun main() {
    Pow.myPow(2.0,-2)
}