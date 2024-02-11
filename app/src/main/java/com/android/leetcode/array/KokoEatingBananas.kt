package com.android.leetcode.array

import kotlin.math.ceil

object KokoEatingBananas {
    /*
1. The h at least has to be equal to the size of the piles
2. if h == piles return the largest number in the array
3. if sorted [3,6,7,11]
4, 1,2,2,3
 */
    fun minEatingSpeed(piles: IntArray, h: Int): Int {
        if(piles.size == h) return piles.max()
        var sort = piles.sorted()
        var start = 0 //3
        var end = sort.last() // 7

        while(start < end) {
            var mid = (start + end) / 2
            if(canMakeIt(mid,sort,h)) {
                end = mid
            }else {
                start = mid + 1
            }
        }

        return end
    }

    fun canMakeIt(speed: Int, piles: List<Int>, h: Int): Boolean {
        var sum = 0.0
        for(i in piles) {
            sum += ceil(i *1.0 / speed)
        }
        return sum <= h
    }
}

fun main() {
    println(KokoEatingBananas.minEatingSpeed(intArrayOf(805306368,805306368,805306368), 1000000000))
}