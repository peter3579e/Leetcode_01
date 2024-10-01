package com.android.leetcode.graph.dsu

import java.util.TreeMap
import kotlin.math.max

object TheEarliestMomentWhenEveryoneBecomeFriends {
        /*
        logs = [[20190101,0,1],
        [20190104,3,4],[20190107,2,3],[20190211,1,5],
        [20190224,2,4],[20190301,0,3],[20190312,1,2],[20190322,4,5]]

        0 1
        1 0
        2 3
        3 4,2
        4 3

        1/1 0 -> 1
        1/4 3 -> 4
        1/7 2 -> 3
        2/11 1 -> 5
        2/24 2 -> 4
        3/1  0 -> 3
        3/12 1 -> 2
        3/22 4 -> 5

        0,0,2,2,3,0
         */
    private lateinit var dsu: DSU

    fun earliestAcq(logs: Array<IntArray>, n: Int): Int {
        dsu = DSU(n)
        logs.sortBy {
            it[0]
        }
        var group = n
        logs.forEach {
            val timeStamp = it[0]
            val a = it[1]
            val b = it[2]
            if(dsu.union(a,b)) {
                group--
            }
            if (group == 1) {
                return timeStamp
            }
        }
        return -1
    }

    class DSU(val N: Int) {
        private var arr = IntArray(N) { it }

        fun findRoot(point: Int): Int = if(point == arr[point]) point else findRoot(arr[point])

        fun union(x: Int, y: Int): Boolean {
            val rootX = findRoot(x)
            val rootY = findRoot(y)
            if(rootX != rootY) {
                arr[rootY] = rootX
            }
            return rootX != rootY
        }
    }
}
/*

******
        *****
*******

   *******
             *****
       *****
               ******
          ******
 */

fun main() {
    print(TheEarliestMomentWhenEveryoneBecomeFriends.earliestAcq(arrayOf(intArrayOf(20190101,0,1), intArrayOf(20190104,3,4), intArrayOf(20190107,2,3), intArrayOf(20190211,1,5), intArrayOf(20190224,2,4), intArrayOf(20190301,0,3), intArrayOf(20190312,1,2), intArrayOf(20190322,4,5)),6))
}