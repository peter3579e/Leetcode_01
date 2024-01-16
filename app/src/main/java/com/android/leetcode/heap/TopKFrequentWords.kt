package com.android.leetcode.heap

import java.util.Collections
import java.util.PriorityQueue


object TopKFrequentWords {

    fun topKFrequent(words: Array<String>, k: Int): List<String> {

        var map = hashMapOf<String,Int>()

        //time  O(N)
        //Space O(N)
        for(i in words) {
            map[i] = map.getOrDefault(i,0)+1
        }

        val heap = PriorityQueue {
                n1: String, n2: String ->
            if (map[n1] == map[n2]) {
                n1.compareTo(n2)
            } else
                map[n2]!! - map[n1]!!
        }

        map.forEach { key, value ->
            heap.offer(key)
        }

        var ans = mutableListOf<String>()
        var count = 0

        while (heap.isNotEmpty() && count < k){
            ans.add(heap.poll())
            count++
        }

        return ans
    }

    fun topKFrequent2(words: Array<String>, k: Int): List<String?>? {
        val cnt: MutableMap<String, Int> = HashMap()
        //time  O(N)
        //Space O(N)
        for (word in words) {
            cnt[word] = cnt.getOrDefault(word, 0) + 1
        }
        //time O(1)
        val h = PriorityQueue { w1: String, w2: String ->
            if (cnt[w1] == cnt[w2]
            ) w2.compareTo(w1) else cnt[w1]!! - cnt[w2]!!
        }
        //time O(N)+O(logN)
        //space O(k)
        for (word in cnt.keys) {
            h.offer(word)
            if (h.size > k) {
                h.poll()
            }
        }
        val res: MutableList<String?> = ArrayList()
        // time O(k) + O(logK)
        while (!h.isEmpty()) {
            res.add(h.poll())
        }
        // time O(KlogK)
        res.reverse()
        return res
    }
}

fun main () {
    print(TopKFrequentWords.topKFrequent(arrayOf("aaa","aa","a"),3))
}