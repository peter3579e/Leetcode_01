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

    fun isAlienSorted2(words: Array<String>, order: String): Boolean {
        if(words.size == 1) return true

        var map = hashMapOf<Char,Int>()

        for(i in 0 until order.length) {
            map[order[i]] = i
        }

        for(i in 0 until words.size-1) {
            var word1 = words[i]
            var word2 = words[i+1]
            for(j in 0 until word1.length) {
                if (j > word2.length-1) return false
                if(word1[j] != word2[j]) {
                    if(map[word1[j]]!! > map[word2[j]]!!) return false
                    break
                }
            }
        }

        return true
    }
}

fun main() {
    print(VerifyingAnAlienDictionary.isAlienSorted2(arrayOf("kuvp","q"), "ngxlkthsjuoqcpavbfdermiywz"))
}