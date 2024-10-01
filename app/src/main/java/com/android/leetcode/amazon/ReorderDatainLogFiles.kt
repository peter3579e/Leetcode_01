package com.android.leetcode.amazon

fun reorderLogFiles(logs: Array<String>): Array<String> {
    val letters = mutableListOf<String>()
    val digits = mutableListOf<String>()
    for(log in logs) {
        val charArray = log.split(" ")
        if (charArray[1][0].isDigit()) {
            digits.add(log)
        } else {
            letters.add(log)
        }
    }
    letters.sortWith(compareBy({
        val charArray = it.split(" ")
        val content = charArray.subList(1, charArray.size).joinToString(" ")
        content
    }, {
        val charArray = it.split(" ")
        val id = charArray[0]
        id
    }))
    return (letters + digits).toTypedArray()
}

fun main() {
    print(reorderLogFiles(arrayOf("dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero")))
}