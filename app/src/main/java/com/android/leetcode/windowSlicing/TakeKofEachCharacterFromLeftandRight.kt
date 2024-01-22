package com.android.leetcode.windowSlicing

object TakeKofEachCharacterFromLeftandRight {
    //Initialization of Count Map:
    //The first loop, where s.groupingBy { it }.eachCount() is used to initialize the count map, takes O(N) time, where N is the length of the string s.
    //Second Loop (Sliding Window):
    //The second loop iterates through each character of the string exactly once.
    //Inside the loop, there is a while loop that slides the window, and each element is visited at most twice (once as the right end of the window and once as the left end of the window).
    //Therefore, the second loop's time complexity is O(N).
    //The overall time complexity is dominated by the O(N) initialization of the count map, so the total time complexity is O(N).
    //
    //In summary:
    //
    //Time Complexity: O(N)
    //Space Complexity: O(1) (since the count map has a fixed size of 3 characters)
    fun takeCharacters(s: String, k: Int): Int {
        var count = s.groupingBy {it}
            .eachCount()
            .toMutableMap()

        for(i in listOf('a', 'b', 'c')) {
            if(count.getOrDefault(i,0) < k) return -1
        }

        var j = 0
        var maxWindow = 0

        for(i in 0..s.length-1) {
            count[s[i]] = count.getValue(s[i]) - 1
            while (count.getValue(s[i]) < k) {
                count[s[j]] = count.getValue(s[j]) + 1
                j++
            }
            maxWindow = maxOf(maxWindow, i - j + 1)
        }

        return s.length-maxWindow
    }
}

fun main() {
    print(TakeKofEachCharacterFromLeftandRight.takeCharacters("aabaaaacaabc", 1))
}