package com.android.leetcode.array

object LongestConsecutiveSequence {

    fun mySolution(nums: IntArray): Int {
        val set = mutableSetOf<Int>()
        val seen = mutableSetOf<Int>()
        // convert to mutableSet
        for(num in nums) {
            set.add(num)
        }
        /*
        l 3
        r 5
         */

        fun checkWindow(num:Int): Int {
            var left = num
            var right = num
            while(set.contains(left) || set.contains(right)) {
                if(set.contains(left)) {
                    seen.add(left)
                    left--
                }
                if(set.contains(right)) {
                    seen.add(right)
                    right++
                }
            }
            return right-left-1
        }
        var max = 0

        for(num in nums) {
            if(seen.contains(num)) continue
            seen.add(num)
            max = maxOf(max,checkWindow(num))
        }

        return max
    }

    fun longestConsecutive(nums: IntArray): Int {
        val numSet = HashSet<Int>()
        nums.forEach { num -> numSet.add(num) }

        var longestStreak = 0

        numSet.forEach { num ->
            if (!numSet.contains(num - 1)) {
                var currentNum = num
                var currentStreak = 1

                while (numSet.contains(currentNum + 1)) {
                    currentNum += 1
                    currentStreak += 1
                }

                longestStreak = longestStreak.coerceAtLeast(currentStreak)
            }
        }

        return longestStreak
    }
}

fun main() {
    LongestConsecutiveSequence.longestConsecutive(intArrayOf(100,4,200,1,3,2))
}