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

fun main() {
    val solution = Solution()
    val digits = "23"
    val result = solution.letterCombinations(digits)
    println(result)
}