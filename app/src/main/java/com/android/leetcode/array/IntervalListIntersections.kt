package com.android.leetcode.array

//986
//Time Complexity: O(M + N), where M,NM, NM,N are the lengths of A and B respectively.
//
//Space Complexity: O(M + N), the maximum size of the answer.
object IntervalListIntersections {
    fun intervalIntersection(firstList: Array<IntArray>, secondList: Array<IntArray>): Array<IntArray> {
        val ans = mutableListOf<IntArray>()
        var i = 0
        var j = 0

        while(i < firstList.size && j < secondList.size) {

            var a = firstList[i]
            var b = secondList[j]

            var lo = maxOf(a[0],b[0])
            var hi = minOf(a[1],b[1])

            if(lo < hi) {
                ans.add(intArrayOf(lo,hi))
            }

            if(a[1] < b[1]) {
                i++
            }else {
                j++
            }
        }
        return ans.toTypedArray()
    }
}