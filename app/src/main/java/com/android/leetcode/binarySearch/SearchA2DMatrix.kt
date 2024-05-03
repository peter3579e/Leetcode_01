package com.android.leetcode.binarySearch

object SearchA2DMatrix {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {

        // find col

        /*
        left 0
        right 1
        mid 0
        num 1
         */
        fun Int.isVaildRow(): Boolean = this in 0..matrix.size-1
        fun Int.isVaildCol(): Boolean = this in 0..matrix[0].size-1
        var left = 0
        var right = matrix.size-1

        while(left <= right) {
            val mid = left + (right - left) / 2
            val num = matrix[mid][0]

            if (num == target) return true
            else if(num > target) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }

        // find row
        /*
        row 0
        left 0
        right 3
        mid 1
        num
         */
        if(!right.isVaildRow()) return false
        val row = right
        left = 0
        right = matrix[0].size-1

        while(left <= right) {
            val mid = left + (right - left) / 2
            val num = matrix[row][mid]
            if(num == target) return true
            else if(num > target) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }

        return false
    }
}

fun main() {
    //[[1,3,5,7],[10,11,16,20],[23,30,34,60]]
    SearchA2DMatrix.searchMatrix(arrayOf(intArrayOf(1)), 0)
}