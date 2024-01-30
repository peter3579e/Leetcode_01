package com.android.leetcode.general

object BulbSwitcher {

    // time complexity is O(n*k)
    // Space is O(1)
    fun bulbSwitch(n: Int): Int {
        if (n == 0 || n==1) return n
        var count = 1

        for(i in 2..n) {
            if(getDivisor(i,i) % 2 != 0) {
                count ++
            }
        }

        return count
    }

    private fun getDivisor(bulb:Int, round: Int): Int {
        var count = 0
        for(i in 1..round) {
            if(bulb % i == 0) {
                count ++
            }
        }
        return count
    }
}

fun main() {
    print(BulbSwitcher.bulbSwitch(9))
}