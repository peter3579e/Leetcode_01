package com.android.leetcode.graph

import java.util.Collections
import java.util.LinkedList


object DiagonalTraverse {
    fun findDiagonalOrder(mat: Array<IntArray>): IntArray {
        /*
        2 5
        8 4
        0 -1
        */

        val ans = mutableListOf<Int>()

        val queue = LinkedList<Pair<Int,Int>>()
        var reverse = false

        queue.add(Pair(0,0))
        ans.add(mat[0][0])
        mat[0][0] = -99768

        // arr -> 1
        // go up
        // go down right to down

        while (queue.isNotEmpty()) {
            val temp = mutableListOf<Int>()
            repeat (queue.size) {
                val curPoint = queue.poll()!!
                val row = curPoint.first
                val col = curPoint.second
                val right = Pair(row,col+1)
                val down = Pair(row+1,col)
                for(point in listOf(right,down)) {
                    val newRow = point.first
                    val newCol = point.second
                    if(newRow in 0 until mat.size && newCol in 0 until mat[0].size && mat[newRow][newCol] != -99768) {
                        queue.offer(Pair(newRow,newCol))
                        temp.add(mat[newRow][newCol])
                        mat[newRow][newCol] =-99768
                    }
                }
            }
            if(!reverse) ans.addAll(temp) else ans.addAll(temp.reversed())
            reverse = !reverse
        }

        return ans.toIntArray()
    }

    fun findDiagonalOrder2(matrix: Array<IntArray>?): IntArray {

        // Check for empty matrices
        if (matrix == null || matrix.size == 0) {
            return IntArray(0)
        }

        // Variables to track the size of the matrix
        val N = matrix.size
        val M = matrix[0].size

        // The two arrays as explained in the algorithm
        val result = IntArray(N * M)
        var k = 0

        // We have to go over all the elements in the first
        // row and the last column to cover all possible diagonals
        for (d in 0 until N + M - 1) {

            // Clear the intermediate array every time we start
            // to process another diagonal
            val intermediate = ArrayList<Int>()

            // We need to figure out the "head" of this diagonal
            // The elements in the first row and the last column
            // are the respective heads.
            var r = if (d < M) 0 else d - M + 1
            var c = if (d < M) d else M - 1

            // Iterate until one of the indices goes out of scope
            // Take note of the index math to go down the diagonal
            while (r < N && c > -1) {
                intermediate.add(matrix[r][c])
                r++
                c--
            }

            // Reverse even numbered diagonals. The
            // article says we have to reverse odd
            // numbered articles but here, the numbering
            // is starting from 0 :P
            if (d % 2 == 0) {
                Collections.reverse(intermediate)
            }
            for (i in intermediate.indices) {
                result[k++] = intermediate[i]
            }
        }
        return result
    }

    fun findDiagonalOrderOptimal(mat: Array<IntArray>): IntArray {
        var size = mat.size * mat[0].size

        var row = 0
        var col = 0
        var index = 0
        val ans = IntArray(size) { 0 }
        var goingUp = true


        while(index < size ) {
            if(goingUp) {
                while(row >= 0 && col < mat[0].size) {
                    ans[index] = mat[row][col]
                    index++
                    row--
                    col++
                }
                if(col == mat[0].size ) {
                    row = row + 2
                    col--
                } else  {
                    row++
                }
                goingUp = !goingUp
            }else {
                while(row < mat.size && col >= 0) {
                    ans[index] = mat[row][col]
                    index++
                    row++
                    col--
                }
                 if(row == mat.size) {
                    col = col + 2
                    row--
                } else {
                    col++
                }
                goingUp = !goingUp
            }
        }

        return ans
    }
    /*
        1   2    3
        4   5    6
        7   8    9
     */

    fun findDiagonalMeta(mat: Array<IntArray>): IntArray {
        val rows = mat.size
        val cols = mat[0].size
        val ans = IntArray(rows*cols) { 0 }
        var index = 0

        for (i in 0 until cols+rows-1) {

            var row = if (i < cols) 0 else i + 1 - cols
            var col = if (i < cols) i else cols-1

            while (row in 0 until rows && col in 0 until cols) {
                ans[index] = mat[row][col]
                index++
                row++
                col--
            }

        }

        return ans
    }

    /*
matrix.size + matrix[0].size - 1
row  = if(i > matrix[0].size) i - matrix[0].size else 0
col = if(i > matrix[0].size) matrix[0].size else i-1
 */
    fun diagonalTraverse(matrix: Array<IntArray>): List<Int> {

        val col = matrix[0].size
        val row = matrix.size
        val ans = mutableListOf<Int>()

        for (i in 1 until col+row) {

            var r = if (i > col) i - col else 0
            var c = if (i > col) col - 1 else i - 1

            while (r in 0 until row && c in 0 until col) {
                ans.add(matrix[r][c])
                r = r + 1
                c = c - 1
            }
        }

        return ans
    }
    /*
    1 2 3
    4 5 6
    7 8 9
     */
}


fun main() {
    print(DiagonalTraverse.diagonalTraverse(arrayOf(intArrayOf(1,2,3,2), intArrayOf(4,5,6,3))).toList())
    Math.random()
}