package com.android.leetcode.string

object GroupShiftedStrings {
    fun groupStrings(strings: Array<String>): List<List<String>> {
        val patternToStrings = hashMapOf<MutableList<Int>,MutableList<String>>()
        for (str in strings) {
            if(str.length == 1) {
                patternToStrings[mutableListOf(str.length)] = patternToStrings.getOrDefault(mutableListOf(str.length),mutableListOf()).apply {
                    this.add(str)
                }
                continue
            }
            val temp = mutableListOf<Int>()
            for(i in 0 until str.length-1) {
                val dif = (str[i + 1] - str[i] + 26) % 26 + 'a'.toInt()
                temp.add(dif)
            }
            patternToStrings[temp] = patternToStrings.getOrDefault(temp,mutableListOf()).apply {
                this.add(str)
            }
        }

        val ans = mutableListOf<MutableList<String>>()

        for((key,value) in patternToStrings) {
            ans.add(value)
        }

        return ans
    }
}

fun main() {
    var list = listOf(12,10)
    print(list.subList(0,2))
//    GroupShiftedStrings.groupStrings(arrayOf("abc","bcd","acef","xyz","az","ba","a","z"))
}