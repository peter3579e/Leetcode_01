package com.android.leetcode.meta

import java.util.Stack

/*
* only + and -
* Input: num = "123", target = 6
Output: ["1+2+3"]
Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.

* append
* +
* -
*
* 123
* ^
*
* 1 - append - 12 - append -> 123
*   - +        1+2  - + -> 1+2+3
*   - -        1-2  - - -> 1-2-3
   123
   ^
   1
   +
   -

   backTrack(0,"")

* */
object AddOpreatore {
    fun addOperators(num: String, target: Int): List<String> {
        val ans = mutableListOf<String>()

        fun calculation(s: StringBuilder): Int {
            var operator = '+'
            var number = 0
            var answer = 0
            for (char in s) {
                if (char.isDigit()) {
                    number = number * 10 + char.digitToInt()
                } else {
                    when {
                        operator == '+' -> {
                            answer = answer + number
                        }

                        operator == '-' -> {
                            answer = answer - number
                        }
                    }
                    number = 0
                    operator = char
                }
            }
            when {
                operator == '+' -> {
                    answer = answer + number
                }

                operator == '-' -> {
                    answer = answer - number
                }
            }
            return answer
        }

        fun backTrack(index: Int, temp: StringBuilder) {
            if (index == num.length - 1) {
                if (calculation(temp) == target) {
                    ans.add(temp.toString())
                }
                return
            }

            backTrack(index + 1, temp.append(num[index + 1]))
            temp.deleteCharAt(temp.lastIndex)
            backTrack(index + 1, temp.append('+' + num[index + 1].toString()))
            temp.deleteRange(temp.lastIndex - 1, temp.lastIndex + 1)
            backTrack(index + 1, temp.append('-' + num[index + 1].toString()))
            temp.deleteRange(temp.lastIndex - 1, temp.lastIndex + 1)
        }

        backTrack(0, StringBuilder(num[0].toString()))
        return ans
    }

    fun addOperators2(num: String, target: Int): List<String> {
        val ans = mutableListOf<String>()

        fun backTrack(index: Int, temp: String, result: Int) {
            if (index == num.length) {
                if (result == target) {
                    ans.add(temp)
                }
                return
            }

            for (i in index until num.length) {
                val s = num.substring(index,i+1)
                val value = s.toInt()

                if (temp.isEmpty()) {
                    backTrack(i+1, s, result + value)
                } else {
                    backTrack(i+1, temp + '+' + s, result + value)
                    backTrack(i+1, temp + '-' + s, result - value)
                }
            }
        }
        backTrack(0, "", 0)
        return ans
    }
}

/*
1+2+3
1+2-3
1-2+3
1-2-3
1+23
1-23
12

321
312

 */

fun main() {
    print(AddOpreatore.addOperators2("123",6))
}
/*
1 2 3
12 3
123
 */