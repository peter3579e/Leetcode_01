package com.android.leetcode.meta

object MinimumSticker {
    /**
     * Problem Description:
     * Given a string sticker that represents the set of characters available on a single sticker and a string word that represents the target word to spell out, return the minimum number of stickers that you need to spell out word. Each sticker can be used more than once, and you have an unlimited supply of stickers.
     * Meta version of https://leetcode.com/problems/stickers-to-spell-word/description/
     *
     * If the word cannot be spelled out using the letters on the sticker, return -1.
     *
     * Note:
     *
     * The sticker and word consist of lowercase English letters only.
     * The lengths of the sticker and word strings are both in the range [1, 1000].
     * Function Signature:
     *
     * public int minStickers(String sticker, String word) {
     * }
     * Example 1:
     * Input: sticker = "ban", word = "banana"
     * Output: 3
     * Explanation: We can use 3 stickers "bana" to spell out the word "banana". Each sticker provides one "b", one "a", and one "n". Three stickers provide all the letters needed to spell out "banana".
     *
     * Example 2:
     * Input: sticker = "abc", word = "def"
     * Output: -1
     * Explanation: The sticker does not contain any of the letters needed to spell out "def". Therefore, it is impossible to spell out the word.
     *
     * Constraints:
     * 1 <= sticker.length, word.length <= 1000
     * sticker and word consist of lowercase English letters.
     *
     * Solution Submission:
     * Below is the Java solution based on the earlier discussed logic. Ensure your code is well-commented, clean, and follows good coding practices before submission.
     */

    /*
    sticker = "ban", word = "banana"
    stcketset -> "b" "a" "n"

    */
    fun findMinimumNumberOfSticker(sticker: String, word: String): Int {
        if (sticker.isEmpty() || word.isEmpty()) return -1
        val stickerMap = sticker.groupingBy { it }.eachCount().toMutableMap()
        val wordMap = word.groupingBy { it }.eachCount().toMutableMap()

        var ans = Int.MIN_VALUE
        for ((key, value) in wordMap) {
            if (!stickerMap.contains(key)) return -1
            val div = Math.ceil(value.toDouble() / stickerMap[key]!!.toDouble())
            ans = maxOf(ans, div.toInt())
        }
        return ans
    }
}

fun main() {

    /*
    print(Stickers("ban", "banana")) => 3
    print(Stickers("abc", "def")) => -1
    print(Stickers("banaan", "banana")) => 1
     */
    print(MinimumSticker.findMinimumNumberOfSticker("banaan", "banana"))
}