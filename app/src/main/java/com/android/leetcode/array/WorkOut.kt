package com.android.leetcode.array

import kotlin.math.max

object WorkOut {

    val streak2 = arrayOf(100)                          // 1 0
    val streak3 = arrayOf(9, 12, 13, 203, 405, 406)              // 2 1
    val streak4 = arrayOf(203, 14, 405, 9)              // 2 1
    val streak5 = arrayOf(9, 14, 365, 366, 375)         // 2 1
    val streak6 = arrayOf(2, 22, 29, 37, 43, 366, 391)  // 4 3

    // The member takes a workout on a specific day (X)
    // If a member takes multiple workouts within a week of the year, that's a streak
    // Calculate given N array, how many streaks the


    fun findTheStreak(days: List<Int>): Int {
        var sorted = days.sorted()
        var max = 0
        var ans = 0
        var start = 0
        var end = 1

        while (end < days.size) {
            if (sorted[start]/7 == sorted[end]/7) {
                ans ++
                end++
                max = maxOf(max, ans)
            }else {
                ans = 0
                start = end
                end ++
            }
        }


        return max
    }
}

fun main () {
    println(WorkOut.findTheStreak(WorkOut.streak3.toList()))
}