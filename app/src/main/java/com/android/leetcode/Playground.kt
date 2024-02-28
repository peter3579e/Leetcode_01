package com.android.leetcode

class Playground {
}

enum class reuslt(val list: List<Int>) {
    RESULT(list = listOf(1,2,3)),
    NO_RESULT(listOf(321))
}

sealed class Result2(open val content: List<Int>) {
    class Result(override val content: List<Int>): Result2(content)
    data object NoResult: Result2(content = emptyList())
}

fun main() {
    var string = StringBuilder()
}