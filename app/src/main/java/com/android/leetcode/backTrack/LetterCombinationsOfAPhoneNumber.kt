package com.android.leetcode.backTrack

class Solution {
    private val combinations: MutableList<String> = mutableListOf()
    private val letters: Map<Char, String> = mapOf(
        '2' to "abc", '3' to "def", '4' to "ghi", '5' to "jkl",
        '6' to "mno", '7' to "pqrs", '8' to "tuv", '9' to "wxyz"
    )
    private lateinit var phoneDigits: String

    fun letterCombinations(digits: String): List<String> {
        // If the input is empty, immediately return an empty answer array
        if (digits.isEmpty()) {
            return combinations
        }

        // Initiate backtracking with an empty path and starting index of 0
        phoneDigits = digits
        backtrack(0, StringBuilder())
        return combinations
    }

    private fun getLetters(index: Int): String {
        return letters[phoneDigits[index]] ?: ""
    }

    private fun backtrack(index: Int, tempLetter: StringBuilder) {
        // If the path is the same length as digits, we have a complete combination
        if (tempLetter.length == phoneDigits.length) {
            combinations.add(tempLetter.toString())
            return // Backtrack
        }

        // Get the letters that the current digit maps to, and loop through them
        val possibleLetters = getLetters(index)
        for (letter in possibleLetters) {
            // Add the letter to our current path
            tempLetter.append(letter)
            // Move on to the next digit
            backtrack(index + 1, tempLetter)
            // Backtrack by removing the letter before moving onto the next
            tempLetter.deleteCharAt(tempLetter.length - 1)
        }
    }
}

/*
* Time complexity: O(4N⋅N)O(4^N \cdot N)O(4N⋅N), where NNN is the length of digits. Note that 444 in this expression is referring to the maximum value length in the hash map, and not to the length of the input.

The worst-case is where the input consists of only 7s and 9s. In that case, we have to explore 4 additional paths for every extra digit. Then, for each combination, it costs up to NNN to build the combination. This problem can be generalized to a scenario where numbers correspond with up to MMM digits, in which case the time complexity would be O(MN⋅N)O(M^N \cdot N)O(M
N⋅N). For the problem constraints, we're given, M=4M = 4M=4, because of digits 7 and 9 having 4 letters each.

Space complexity: O(N)O(N)O(N), where NNN is the length of digits.

Not counting space used for the output, the extra space we use relative to input size is the space occupied by the recursion call stack. It will only go as deep as the number of digits in the input since whenever we reach that depth, we backtrack.

As the hash map does not grow as the inputs grows, it occupies O(1)O(1)O(1) space
* */

fun main() {
    val solution = Solution()
    val digits = "23"
    val result = pratice.letterCombinations(digits)
    println(result)
}

object pratice {

    private val combinations: MutableList<String> = mutableListOf()
    private val letters: Map<Char, String> = mapOf(
        '2' to "abc", '3' to "def", '4' to "ghi", '5' to "jkl",
        '6' to "mno", '7' to "pqrs", '8' to "tuv", '9' to "wxyz"
    )
    fun letterCombinations(digits: String): List<String> {
        if (digits.isEmpty()) {
            return combinations
        }
        backTrack(0, StringBuilder(), digits)
        return combinations
    }

    fun backTrack(phoneIndex: Int, tempString: StringBuilder, phoneDigits: String) {
        if (tempString.length == phoneDigits.length) {
            combinations.add(tempString.toString())
            return
        }

        var letter = letters[phoneDigits[phoneIndex]]

        for (i in letter!!) {
            tempString.append(i)
            backTrack(phoneIndex+1, tempString, phoneDigits)
            tempString.deleteCharAt(tempString.length-1)
        }
    }
}