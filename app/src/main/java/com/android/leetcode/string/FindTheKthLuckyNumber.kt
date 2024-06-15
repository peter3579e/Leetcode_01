package com.android.leetcode.string

object FindTheKthLuckyNumber {
    fun kthLuckyNumber(k: Int): String {
        // Increment k to account for 1-based indexing
        var k = k
        k = k + 1

        // For each digit in the binary representation of k except the most significant
        // Prepend 4 to the result if the digit is 0 and 7 otherwise
        val kthLuckyNumBuilder = StringBuilder()
        while (k > 1) {
            kthLuckyNumBuilder.insert(0, (if ((k and 1) == 1) "7" else "4"))
            k = k / 2
        }
        return kthLuckyNumBuilder.toString()
    }
}

fun main() {
    println(0%3)
//    FindTheKthLuckyNumber.kthLuckyNumber(10)
}