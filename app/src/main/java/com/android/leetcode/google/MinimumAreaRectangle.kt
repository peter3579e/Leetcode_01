package com.android.leetcode.google

class MinimumAreaRectangle {

    fun minAreaRect(points: Array<IntArray>): Int {

        val listPoint = mutableSetOf<Pair<Int,Int>>()

        for(point in points) {
            val x = point[0]
            val y = point[1]
            listPoint.add(Pair(x,y))
        }
        var minArea = Int.MAX_VALUE

        for(i in 0 until points.size) {
            for(j in i+1 until points.size) {
                val first = points[i]
                val second = points[j]
                if(first[0] != second[0] && first[1] != second[1]) {
                    if(listPoint.contains(Pair(first[0],second[1])) && listPoint.contains(Pair(second[0],first[1]))) {
                        minArea = minOf(minArea,Math.abs(second[0] - first[0]) * Math.abs(second[1] - first[1]))
                    }
                }
            }
        }

        return if (minArea == Int.MAX_VALUE) 0 else minArea
    }
}