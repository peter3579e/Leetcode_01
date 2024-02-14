package com.android.leetcode.array

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

    fun maximumSwap3(num: Int): Int {
        val A = Integer.toString(num).toCharArray()
        val last = IntArray(10)
        for (i in A.indices) {
            last[A[i].code - '0'.code] = i
        }
        for (i in A.indices) {
            for (d in 9 downTo A[i].code - '0'.code + 1) {
                if (last[d] > i) {
                    val tmp = A[i]
                    A[i] = A[last[d]]
                    A[last[d]] = tmp
                    return String(A).toInt()
                }
            }
        }
        return num
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
    var ans = ArrayDeque<String>()
    print(MaximumSwap.maximumSwap3(98368))
}