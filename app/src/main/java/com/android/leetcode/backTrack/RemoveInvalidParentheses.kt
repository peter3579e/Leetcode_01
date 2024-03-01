package com.android.leetcode.backTrack

object RemoveInvalidParentheses {

    private var ans = mutableSetOf<String>()
    private var max = -1
    fun removeInvalidParentheses(s: String): List<String> {
        if(s.isEmpty()) return listOf("")

        backTrack(s, StringBuilder(), 0, 0, 0)

        return ans.toList()
    }

    private fun backTrack(s: String, temp: StringBuilder, index: Int, leftCnt: Int, rightCnt: Int) {
        if(index > s.length-1) {
            if(leftCnt == rightCnt) {
                if(temp.length > max) {
                    ans = mutableSetOf<String>()
                    ans.add(temp.toString())
                    max = temp.length
                }else if(temp.length == max) {
                    ans.add(temp.toString())
                }
            }
            return
        }

        var cur = s[index]
        if(cur == '(') {
            temp.append(cur)
            backTrack(s, temp, index+1, leftCnt+1, rightCnt)
            temp.deleteAt(temp.length-1)
            backTrack(s, temp, index+1, leftCnt, rightCnt)
        }else if(cur == ')') {
            backTrack(s, temp, index+1, leftCnt, rightCnt)
            if(leftCnt > rightCnt) {
                temp.append(cur)
                backTrack(s, temp, index+1, leftCnt, rightCnt+1)
                temp.deleteAt(temp.length-1)
            }
        }else {
            temp.append(cur)
            backTrack(s, temp, index+1, leftCnt, rightCnt)
            temp.deleteAt(temp.length-1)
        }
    }
}

fun main() {
    print(RemoveInvalidParentheses.removeInvalidParentheses("()())()"))
}