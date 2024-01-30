package com.android.leetcode.array

object MeetingRoomsII {

    // time complexity is O(N*K)
    // Space complexity is O(K)
    fun minMeetingRooms(intervals: Array<IntArray>): Int {

        if(intervals.size == 1) return 1
        var tempList = mutableListOf<IntArray>()
        tempList.add(intervals[0])

        for (i in 1..intervals.size-1) {
            var cur1 = intervals[i]
            var boolean = false
            for(j in 0..tempList.size-1) {
                var cur2 = tempList[j]
                var lo = maxOf(cur1[0],cur2[0])
                var hi = minOf(cur1[1],cur2[1])
                if(lo < hi) {
                    boolean = true
                } else {
                    boolean = false
                    break
                }
            }
            if(boolean) tempList.add(cur1)
        }

        return tempList.size
    }

}

fun main() {
    print(MeetingRoomsII.minMeetingRooms(arrayOf(intArrayOf(7,10), intArrayOf(2,4))))
}