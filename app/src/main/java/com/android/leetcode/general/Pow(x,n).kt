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

object Pow2 {
    fun myPow(x: Double, n: Int): Double {

        // base case, i.e if n = 0, return 1
        if (n == 0) {
            return 1.0
        }

        // for negative numbers
        if (n < 0) {
            var new = (x * myPow(x, -(n + 1)))
            var x = 1.0 / new
            return x
        }

        // for any recursive case
        // splitting the problem into 2 half and then solving

        val half = myPow(x, n / 2)
        return if (n % 2 == 0) {
            half * half
        } else {
            x * half * half
        }
    }

    fun myPowRecursion(x: Double, n: Int): Double {
        return binaryExp(x,n.toLong())
    }

    private fun binaryExp(x: Double, n: Long): Double {
        // Base case, to stop recursive calls.
        if (n == 0L) {
            return 1.0
        }

        // Handle case where, n < 0.
        if (n < 0) {
            return 1.0 / binaryExp(x, -1 * n)
        }

        // Perform Binary Exponentiation.
        // If 'n' is odd we perform Binary Exponentiation on 'n - 1' and multiply result with 'x'.
        return if (n % 2 == 1L) {
            x * binaryExp(x * x, (n - 1) / 2)
        } else {
            binaryExp(x * x, n / 2)
        }
    }
}

object Pow3 {
    fun myPow(x: Double, n: Int): Double {
        if(n == 0) return 1.0
        if(x == 0.0) return 0.0
        return if(n<0) 1.0 / helper(x,Math.abs(n).toLong()) else helper(x,n.toLong())
    }

    fun helper(x: Double, n: Long): Double {
        var newX = x
        var newN = n
        var result = 1.0
        while(newN != 0L) {
            if(newN % 2L == 1L) {
                result = result * newX
            }
            newX = newX * newX
            newN = newN/2
        }
        return result
    }
    /*
     2^10
     (2*2)^5
     4*(4*4) ^2
     4*(16*16) ^ 1
     */

    fun myPowIterative(x: Double, n: Int): Double {
        if(n == 0) return 1.0
        var result = 1.0
        var num = if(n < 0) 1/x else x
        var div = if(n < 0) -1.0*n.toDouble() else n.toDouble()

        while(div >= 1.0) {
            if(div == 1.0) {
                result = result * num
            }else if(div % 2.0 == 0.0) {
                num = num*num
            } else {
                result = result * num
                num = num * num
                div--
            }
            div = div / 2.0
        }

        return result
    }
}
fun main() {
    print(Pow3.myPowIterative(2.0,10))
}