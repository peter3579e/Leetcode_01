package com.android.leetcode.array

import androidx.core.text.isDigitsOnly
import kotlin.math.pow

object IntegerToEnglishWord {
    private val to19 = arrayOf("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen")
    private val tens = arrayOf("Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety")
    private val ks = arrayOf("Thousand", "Million", "Billion")
    var list = mutableListOf<String>()
    //123123
    private fun words(num: Int, index:Int): List<String> {
        if (num <= 0) return listOf()
        var divident = 1000.0.pow(index)
        var num1 = num/divident
        if (num >= divident) {
            lessthan1000(num1.toInt())
            if (index > 0) list.add(ks[index-1])
        }
        words(num.rem(divident).toInt(), index - 1)
        return list
    }

    fun lessthan1000(num: Int) {
        when{
            num == 0 -> list.add("")
            num < 20 -> list.add(to19[num - 1])
            num < 100 -> {
                list.add(tens[(num / 10) - 2])
                lessthan1000(num.rem(10))
            }
            num < 1000 -> {
                list.add(to19[num/100 - 1] + " Hundred")
                lessthan1000(num.rem(100))
            }
        }
    }

    fun numberToWords(num: Int): String {
        return if (num == 0)
            "Zero"
        else words(num, ks.size)
            .filter { it.isNotBlank() }
            .joinToString(" ")
    }
}

fun main() {
    var string = "abc12"
//    print(IntegerToEnglishWord.numberToWords(20))
}