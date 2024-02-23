package com.android.leetcode.graph

import java.util.LinkedList
import java.util.Queue
import kotlin.math.min


object AlienDictionary {

    private val reverseAdjList: MutableMap<Char, MutableList<Char>> = HashMap()
    private val seen: MutableMap<Char, Boolean> = HashMap()
    private val output = StringBuilder()

    fun alienOrder(words: Array<String>): String {

        // Step 0: Put all unique letters into reverseAdjList as keys.
        for (word in words) {
            for (c in word.toCharArray()) {
                reverseAdjList.putIfAbsent(c, ArrayList())
            }
        }

        // Step 1: Find all edges and add reverse edges to reverseAdjList.
        for (i in 0 until words.size - 1) {
            val word1 = words[i]
            val word2 = words[i + 1]
            // Check that word2 is not a prefix of word1.
            if (word1.length > word2.length && word1.startsWith(word2)) {
                return ""
            }
            // Find the first non match and insert the corresponding relation.
            for (j in 0 until min(word1.length, word2.length)) {
                if (word1[j] != word2[j]) {
                    reverseAdjList[word2[j]]!!.add(word1[j])
                    break
                }
            }
        }

        // Step 2: DFS to build up the output list.
        reverseAdjList.forEach { (key, value) ->
            val result = dfs(key)
            if (!result) return ""
        }

        return output.toString()
    }

    // Return true iff no cycles detected.
    private fun dfs(c: Char): Boolean {
        if (seen.containsKey(c)) {
            return seen[c]!! // If this node was grey (false), a cycle was detected.
        }
        seen[c] = false
        for (next in reverseAdjList[c]!!) {
            val result = dfs(next)
            if (!result) return false
        }
        seen[c] = true
        output.append(c)
        return true
    }
}

object Solution3 {
    /*
    if prefix is same the shorter need to be at the front
      ape apple
    if there is cycle in the grap return "" -> loop det
    post order dfc -> do the leaf node first and reverse it at the end
    vist path
    */
    private val reverseAdjList: MutableMap<Char, MutableList<Char>> = HashMap()
    private val seen: MutableMap<Char, Boolean> = HashMap()
    private val output = StringBuilder()

    fun alienOrder(words: Array<String>): String {

        // Step 0: Put all unique letters into reverseAdjList as keys.
        for (word in words) {
            for (c in word) {
                reverseAdjList.putIfAbsent(c, mutableListOf<Char>())
            }
        }

        for(i in 0 until words.size-1) {
            var word1 = words[i]
            var word2 = words[i+1]
            if(word1.length>word2.length && word1.startsWith(word2)) return ""
            for(j in 0 until minOf(word1.length,word2.length)) {
                var char1 = word1[j]
                var char2 = word2[j]
                if(char1 != char2) {
                    reverseAdjList[char2]!!.add(char1)
                    break
                }
            }
        }
        // dfs
//        reverseAdjList.forEach {(key,value) ->
//            var result = dfs(key)
//            if(!result) return ""
//        }

        // bfs
        var queue = LinkedList<Char>()
        reverseAdjList.forEach { (key, value) ->
            if (!seen.containsKey(key)) queue.offer(key)
            while (queue.isNotEmpty()) {
                var key = queue.poll()
//                if (seen.containsKey(key) && seen[key] == false)
                output.append(key)
                seen[key] = false
                if (reverseAdjList[key]!!.isNotEmpty()) {
                    for (i in reverseAdjList[key!!]!!) {
                        queue.offer(i)
                    }
                }
                seen[key] = true
            }
        }

        return output.reverse().toString()
    }
    /*
    f = t
    t = r
    r = e
    e = w
     */

    fun dfs(key:Char):Boolean {
        if(seen.contains(key)) {
            return seen[key]!!
        }
        for(char in reverseAdjList[key]!!) {
            seen[key] = false
            var result = dfs(char)
            if(!result) return false
        }
        seen[key] = true
        output.append(key)
        return true
    }

    fun alienOrderBFS(words: Array<String>): String {

        // Step 0: Create data structures and find all unique letters.
        val adjList: MutableMap<Char, MutableList<Char>> = HashMap()
        val counts: MutableMap<Char, Int> = HashMap()
        for (word in words) {
            for (c in word.toCharArray()) {
                counts[c] = 0
                adjList[c] = ArrayList()
            }
        }

        // Step 1: Find all edges.
        for (i in 0 until words.size - 1) {
            val word1 = words[i]
            val word2 = words[i + 1]
            // Check that word2 is not a prefix of word1.
            if (word1.length > word2.length && word1.startsWith(word2)) {
                return ""
            }
            // Find the first non match and insert the corresponding relation.
            for (j in 0 until min(word1.length.toDouble(), word2.length.toDouble()).toInt()) {
                if (word1[j] != word2[j]) {
                    adjList[word1[j]]!!.add(word2[j])
                    counts[word2[j]] = counts[word2[j]]!! + 1
                    break
                }
            }
        }

        // Step 2: Breadth-first search.
        val sb = java.lang.StringBuilder()
        val queue: Queue<Char> = LinkedList()
        for (c in counts.keys) {
            if (counts[c] == 0) {
                queue.add(c)
            }
        }
        while (!queue.isEmpty()) {
            val c = queue.remove()
            sb.append(c)
            for (next in adjList[c]!!) {
                counts[next] = counts[next]!! - 1
                if (counts[next] == 0) {
                    queue.add(next)
                }
            }
        }
        return if (sb.length < counts.size) {
            ""
        } else sb.toString()
    }
}

object Solution5 {

    fun alienOrder(words: Array<String>): String {
        val reverseAdjList: MutableMap<Char, MutableList<Char>> = HashMap()
        val count: MutableMap<Char, Int> = HashMap()
        val output = StringBuilder()

        // Step 0: Put all unique letters into reverseAdjList as keys.
        for (word in words) {
            for (c in word) {
                reverseAdjList.putIfAbsent(c, mutableListOf<Char>())
                count.putIfAbsent(c,0)
            }
        }

        for(i in 0 until words.size-1) {
            var word1 = words[i]
            var word2 = words[i+1]
            if(word1.length>word2.length && word1.startsWith(word2)) return ""
            for(j in 0 until minOf(word1.length,word2.length)) {
                var char1 = word1[j]
                var char2 = word2[j]
                if(char1 != char2) {
                    reverseAdjList[char1]!!.add(char2)
                    count[char2] = count.getOrDefault(char2,0) + 1
                    break
                }
            }
        }

        // bfs
        var queue = LinkedList<Char>()
        count.forEach { (key, value) ->
            if (value == 0) queue.offer(key)
        }

        while (queue.isNotEmpty()) {
            var char = queue.poll()
            output.append(char)
            for (i in reverseAdjList[char]!!) {
                count[i] = count[i]!! - 1
                if (count[i] == 0) {
                    queue.offer(i)
                }
            }
        }

        return if (count.size > output.length) return "" else output.toString()
    }
}

fun main() {
    print(Solution5.alienOrder(arrayOf("wrt","wrf","er","ett","rftt")))
}