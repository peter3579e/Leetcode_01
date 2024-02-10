package com.android.leetcode.array

import java.util.PriorityQueue

object MeetingRoomsII {

    // time complexity is O(N*K)
    // Space complexity is O(K)
    // fail
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

    fun minMeetingRooms2(intervals: Array<IntArray>): Int {

        if(intervals.size == 1) return 1
        val sorted = intervals.sortedBy { it.first() }
        val meetingRoom = mutableListOf<Int>()
        meetingRoom.add(sorted[0][1])
        for (i in 1..sorted.size-1) {
            var canFit = false
            for (j in 0..meetingRoom.size-1) {
                if (sorted[i][0] > meetingRoom[j] || sorted[i][1] > meetingRoom[j]) {
                    canFit = true
                    meetingRoom[j] = sorted[i][1]
                    break
                }
            }
            if(!canFit) meetingRoom.add(sorted[i][1])
        }

        return meetingRoom.size
    }


    fun minMeetingRoomsOptimal(intervals: Array<IntArray>): Int {

        if(intervals.size == 1) return 1
        val sorted = intervals.sortedBy { it.first() }
        val queue = PriorityQueue<Int>()
        queue.offer(sorted[0][1])

        for(i in 1..intervals.size-1) {
            if(sorted[i][0] < queue.peek()) {
                queue.offer(sorted[i][1])
            }else {
                queue.poll()
                queue.offer(sorted[i][1])
            }
        }


        return queue.size
    }

    fun minMeetingRooms4(intervals: Array<IntArray>): Int {

        if (intervals.size == 1) return 1

        var start = mutableListOf<Int>()
        var end = mutableListOf<Int>()

        for (i in intervals) {
            start.add(i[0])
            end.add(i[1])
        }

        start.sort()
        end.sort()

        var room = 0
        var j = 0

        for (i in 0..start.size - 1) {
            if (start[i] < end[j]) {
                room++
                continue
            }
            while (start[i] >= end[j] && j < end.size) {
                j++
            }
            room++
        }


        return room
    }


}

fun main() {
    print(MeetingRoomsII.minMeetingRoomsOptimal(arrayOf(intArrayOf(0,30), intArrayOf(5,10), intArrayOf(15,20))))
}