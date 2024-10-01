package com.android.leetcode.amazon

fun reorganizeString(s: String): String {
    val sMap = s.groupingBy { it }.eachCount().toMutableMap()

    var maxCount = 0
    var maxChar = ' '

    for((key,value) in sMap) {
        if(value > maxCount) {
            maxCount = value
            maxChar = key
        }
    }

    if(maxCount > (s.length+1)/2) return ""

    val ans = CharArray(s.length) { ' ' }

    while(sMap.isNotEmpty()) {
        var index = 0
        while(sMap[maxChar] != null && sMap[maxChar] != 0) {
            ans[index] = maxChar
            index = index + 2
            sMap[maxChar] = sMap[maxChar]!! - 1
        }

        sMap.remove(maxChar)
        index = 1
        while(sMap.isNotEmpty()) {
            val key = sMap.keys.first()
            ans[index] = key
            sMap[key] = sMap[key]!! - 1
            if(sMap[key] != null && sMap[key] == 0) sMap.remove(key)
            index = index + 2
        }
    }

    return ans.concatToString()
}