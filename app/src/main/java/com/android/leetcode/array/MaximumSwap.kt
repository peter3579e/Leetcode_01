package com.android.leetcode.array

import java.util.Deque
import java.util.Queue

object MaximumSwap {
    fun maximumSwap(num: Int): Int {
        var newNum = num
        var arr = ArrayDeque<Int>()

        while (newNum != 0) {
            arr.addFirst(newNum % 10)
            newNum /= 10
        }

        var max = arr.lastIndex
        var swap1 = 0
        var swap2 = 0

        for (i in arr.lastIndex-1 downTo 0) {
            if (arr[max] == arr[i]) continue

            if (arr[max] < arr[i]) {
                max = i
            }else {
                swap1 = max
                swap2 = i
            }
        }

        var temp = arr[swap2]
        arr[swap2] = arr[swap1]
        arr[swap1] = temp

        var ans = 0

        while (arr.isNotEmpty()) {
            ans = ans*10 + arr.removeFirst()
        }

        return ans
    }

    fun maximumSwap2(num: Int): Int {
        val digits = num.toString().toCharArray()
        var maxIndex = digits.lastIndex
        var swap1 = 0
        var swap2 = 0
        for(i in digits.lastIndex - 1 downTo 0) {
            if(digits[maxIndex] == digits[i]) continue

            if(digits[maxIndex] < digits[i]) {
                // update maxIndex if new max found
                maxIndex = i
            } else {
                // keep updating swap index, lower index the better
                swap1 = maxIndex
                swap2 = i
            }
        }

        digits.swap(swap1, swap2)

        return String(digits).toInt()
    }

    private fun CharArray.swap(i: Int, j: Int) {
        if(i == j) return
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }
}

fun main() {
    print(MaximumSwap.maximumSwap(98368))
}