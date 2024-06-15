package com.android.leetcode.graph

object Minesweeper {
    fun updateBoard(board: Array<CharArray>, click: IntArray): Array<CharArray> {
        val dir = listOf(Pair(0,1),Pair(1,0),Pair(0,-1),Pair(-1,0),Pair(-1,1),Pair(1,1),Pair(1,-1),Pair(-1,-1))
        val visited = mutableSetOf<Pair<Int,Int>>()
        // find the posistion of mine
        fun isVaild(x: Int, y: Int): Boolean = x in 0 until board.size && y in 0 until board[0].size

        fun dfs(point: Pair<Int,Int>) {
            val row = point.first
            val col = point.second
            var newX = 0
            var newY = 0
            var count = 0
            visited.add(point)

            for(i in 0 until 8) {
                newX = row + dir[i].first
                newY = col + dir[i].second
                if(isVaild(newX,newY) && (board[newX][newY] == 'M' || board[newX][newY] == 'X')) {
                    count++
                }
            }

            board[row][col] = if(count == 0) 'B' else count.digitToChar()

            if(count > 0) return

            for(i in 0 until 4) {
                newX = row + dir[i].first
                newY = col + dir[i].second
                if(!isVaild(newX,newY) || visited.contains(Pair(newX,newY))) continue
                dfs(Pair(newX,newY))
            }
        }

        if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X'
            return board
        }

        dfs(Pair(click[0],click[1]))

        return board
    }
}

fun main() {
    Minesweeper.updateBoard(arrayOf(charArrayOf('E','E','E','E','E'), charArrayOf('E','E','M','E','E'), charArrayOf('E','E','E','E','E'), charArrayOf('E','E','E','E','E')), intArrayOf(3,0)).forEach {
        println(it.toList())
    }
}
/*
["E","E","E","E","E","E","E","E"],
["E","E","E","E","E","E","E","M"],
["E","E","M","E","E","E","E","E"],
["M","E","E","E","E","E","E","E"],
["E","E","E","E","E","E","E","E"],
["E","E","E","E","E","E","E","E"],
["E","E","E","E","E","E","E","E"],
["E","E","M","M","E","E","E","E"]]

["B","B","B","B","B","B","1","E"],
["B","1","1","1","B","B","1","M"],
["1","E","M","1","B","B","1","1"],
["M","E","1","1","B","B","B","B"],
["1","1","B","B","B","B","B","B"],
["B","B","B","B","B","B","B","B"],
["B","1","2","2","1","B","B","B"],
["B","1","M","M","1","B","B","B"]

["B","B","B","B","B","B","1","E"],
["B","1","1","1","B","B","1","M"],
["1","2","M","1","B","B","1","1"],
["M","2","1","1","B","B","B","B"],
["1","1","B","B","B","B","B","B"],
["B","B","B","B","B","B","B","B"],
["B","1","2","2","1","B","B","B"],
["B","1","M","M","1","B","B","B"]]
 */