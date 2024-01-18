package com.android.leetcode.binarySearch

object FindAPeakElementII {

    var ans = intArrayOf()

    fun findPeakGrid(mat: Array<IntArray>): IntArray {
        dfs(0,0, mat)
        return ans
    }

    fun dfs(x: Int, y: Int, mat: Array<IntArray>) {

        var cur = mat[x][y]
        if(isVaild(x-1, y, mat) && mat[x-1][y] > cur) dfs(x-1, y, mat)
        else if (isVaild(x, y+1, mat)  && mat[x][y+1] > cur) dfs(x, y+1, mat)
        else if (isVaild(x, y-1, mat) && mat[x][y-1] > cur) dfs(x, y-1, mat)
        else if (isVaild(x+1, y, mat) && mat[x+1][y] > cur) dfs(x+1, y, mat)
        else ans = intArrayOf(x,y)
    }

    fun isVaild(x:Int, y:Int, mat: Array<IntArray>) = x >= 0 && y>= 0 && x < mat.size && y < mat[0].size


    fun findPeakGrid2(mat: Array<IntArray>): IntArray {
        var x = 0
        var y = 0
        while (true) {
            val cur = mat[x][y]
            if(isVaild(x-1, y, mat) && mat[x-1][y] > cur){
                x = x - 1
                continue
            }
            else if (isVaild(x, y+1, mat)  && mat[x][y+1] > cur) {
                y = y+1
                continue
            }
            else if (isVaild(x, y-1, mat) && mat[x][y-1] > cur) {
                y = y-1
                continue
            }
            else if (isVaild(x+1, y, mat) && mat[x+1][y] > cur) {
                x = x + 1
            }
            return intArrayOf(x,y)
        }
    }

    fun findPeakGrid3(mat: Array<IntArray>): IntArray {
        var low = 0
        var hi = mat.size - 1

        while(low <= hi) {
            var r = (low+hi) / 2
            var maxNumber = -1
            var c = 0
            for(i in 0..mat[r].size-1) {
                if(mat[r][i] > maxNumber) {
                    maxNumber = i
                    c = i
                }
            }

            if( r > 0 && mat[r-1][c] > maxNumber) {
                low = r - 1
                continue
            }

            if(r < mat.size - 1 && mat[r+1][c] > maxNumber) {
                hi = r + 1
            }
            return intArrayOf(r,c)
        }
        return intArrayOf()
    }
}

fun main() {
    print(FindAPeakElementII.findPeakGrid2(arrayOf(intArrayOf(1,4), intArrayOf(3,2))).toList())
}