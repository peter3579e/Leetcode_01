package com.android.leetcode.windowSlicing

object WindowSliding {
    fun withOutWindowSliding(array: IntArray, k: Int): IntArray {
        var maxSum = 0
        var ans = listOf<Int>()


        for (i in 0..array.size-k) {
            var temp = mutableListOf<Int>()
            var cur = array[i]
            temp.add(cur)
            for(j in 1..k-1) {
                temp.add(array[j+i])
                cur += array[j+i]
            }
            if (cur > maxSum) {
                maxSum = cur
                ans = temp
            }
        }

        return ans.toIntArray()
    }

    fun windowSliding(array: IntArray, k: Int): Int{

        var maxSum = 0

        for (i in 0..k-1) maxSum += array[i]

        var windowSum = 0

        for (j in k..array.size-1) {
             windowSum = maxSum - array[j-k] + array[j]
            maxSum = maxOf(maxSum, windowSum)
        }

        return maxSum
    }

    fun windowSlidingDynamic(s: String): Int{

        var charArray = IntArray(26)

        var windowSize = 0
        var start = 0

        for (end in 0..s.length-1) {
            var cur = s[end]
            charArray[cur - 'a'] = charArray[cur - 'a'] + 1

            while (charArray[cur - 'a'] > 1) {
                start ++
                charArray[cur - 'a'] = charArray[cur - 'a'] - 1
            }

            windowSize = maxOf(windowSize, end-start + 1)
        }

        return windowSize
    }
}

fun main() {
    print(WindowSliding.windowSlidingDynamic("abcabcbb"))
}