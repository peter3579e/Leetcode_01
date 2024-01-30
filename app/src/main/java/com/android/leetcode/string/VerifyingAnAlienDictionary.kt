package com.android.leetcode.string

object VerifyingAnAlienDictionary {
    fun isAlienSorted(words: Array<String>, order: String): Boolean {

        var map = hashMapOf<Char,Int>()

        for(i in 0..order.length-1) {
            map[order[i]] = i
        }

        for(i in 0..words.size-2) {
            var word1 = words[i]
            var word2 = words[i+1]
            for(i in 0..word1.length-1) {
                if(i > word2.length-1) return false
                if(word1[i] != word2[i]) {
                    if(map[word1[i]]!! > map[word2[i]]!!) return false
                    break
                }
            }
        }

        return true
    }
}

fun main() {
    print(VerifyingAnAlienDictionary.isAlienSorted(arrayOf("apple","app"), "abcdefghijklmnopqrstuvwxyz"))
}