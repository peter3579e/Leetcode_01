package com.android.leetcode.dp

import java.util.Arrays



object TossStrangeCoins {
        private fun findProbability(
            index: Int,
            n: Int,
            memo: Array<DoubleArray>,
            prob: DoubleArray,
            target: Int
        ): Double {
            // Return 0 if the target is less than zero because we have more heads
            // than we need.
            if (target < 0) {
                return 0.0
            }
            // After tossing all of the coins, if we get the required number of heads,
            // return 1 to count this case, otherwise return 0.
            if (index == n) {
                return if (target == 0) 1.0 else 0.0
            }

            if (memo[index][target] != -1.0) {
                return memo[index][target]
            }

            memo[index][target] =
                findProbability(index + 1, n, memo, prob, target - 1) * prob[index] +
                        findProbability(index + 1, n, memo, prob, target) * (1 - prob[index])

            return memo[index][target]
        }

        fun probabilityOfHeads(prob: DoubleArray, target: Int): Double {
            val n = prob.size
            val memo = Array(n) { DoubleArray(target + 1) }
            for (row in memo) {
                Arrays.fill(row, -1.0)
            }

            return findProbability(0, n, memo, prob, target)
        }


}

fun main() {
    TossStrangeCoins.probabilityOfHeads(doubleArrayOf(0.5,0.5,0.5,0.5,0.5),0)
}