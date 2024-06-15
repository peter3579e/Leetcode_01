package com.android.leetcode.graph

class TicTacToe(n: Int) {
    val n = n
    val p1RowMap = hashMapOf<Int,Int>()
    val p1ColMap = hashMapOf<Int,Int>()
    val p2RowMap = hashMapOf<Int,Int>()
    val p2ColMap = hashMapOf<Int,Int>()
    val p1Arr = mutableSetOf<Pair<Int,Int>>()
    val p2Arr = mutableSetOf<Pair<Int,Int>>()

    fun move(row: Int, col: Int, player: Int): Int {

        if(player == 1) {
            p1RowMap[row] = p1RowMap.getOrDefault(row,0) + 1
            p1ColMap[col] = p1ColMap.getOrDefault(col,0) + 1
            p1Arr.add(Pair(row,col))
            if(p1RowMap[row]!! == n || p1ColMap[col]!! == n) return 1
            if(p1Arr.size >= n && checkDiagonal(p1Arr)) return 1
        } else {
            p2RowMap[row] = p2RowMap.getOrDefault(row,0) + 1
            p2ColMap[col] = p2ColMap.getOrDefault(col,0) + 1
            p2Arr.add(Pair(row,col))
            if(p2RowMap[row]!! == n || p2ColMap[col]!! == n) return 2
            if(p2Arr.size >= n && checkDiagonal(p2Arr)) return 2
        }

        return 0
    }

    fun checkDiagonal(arr: MutableSet<Pair<Int,Int>>): Boolean {
        var count = 0
        for(i in 0 until n) {
            val point = Pair(i,i)
            if(arr.contains(point)) count++
        }
        if(count == n) return true

        var col = n
        count = 0

        for(i in 0 until n) {
            val point = Pair(i,--col)
            if(arr.contains(point)) count++
        }
        return if(count == n) true else false
    }

    fun kthLuckyNumber(k: Int): String {
        // Increment k to account for 1-based indexing
        var k = k
        k = k + 1

        // For each digit in the binary representation of k except the most significant
        // Prepend 4 to the result if the digit is 0 and 7 otherwise
        val kthLuckyNumBuilder = StringBuilder()
        while (k > 1) {
            kthLuckyNumBuilder.insert(0, (if ((k and 1) == 1) "7" else "4"))
            k = k shr 1
        }
        return kthLuckyNumBuilder.toString()
    }
}

fun main() {
    val arr = intArrayOf()
    arr.toTypedArray().toString()
    //[2,0,1],[0,0,2],[0,1,1],[0,2,2],[2,1,1],[1,1,2],[1,2,1],[2,2,2]]
    val c = TicTacToe(3)
    c.move(2,0,1)
    c.move(0,0,2)
    c.move(0,1,1)
    c.move(0,2,2)
    c.move(2,1,1)
    c.move(1,1,2)
    c.move(1,2,1)
    c.move(2,2,2)
}