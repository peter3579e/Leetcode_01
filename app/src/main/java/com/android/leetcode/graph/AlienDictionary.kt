package com.android.leetcode.graph

import java.util.LinkedList

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

        for (i in 0 until words.size - 1) {
            var word1 = words[i]
            var word2 = words[i + 1]
            if (word1.length > word2.length && word1.startsWith(word2)) return ""
            for (j in 0 until minOf(word1.length, word2.length)) {
                var char1 = word1[j]
                var char2 = word2[j]
                if (char1 != char2) {
                    reverseAdjList[char2]!!.add(char1)
                    break
                }
            }
        }
        // dfs
        reverseAdjList.forEach { (key, value) ->
            var result = dfs(key)
            if (!result) return ""
        }

        // bfs
        var queue = LinkedList<Char>()
        for ((key, value) in reverseAdjList) {
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
}

object Solution5 {

    fun alienOrder(words: Array<String>): String {
        val adj: MutableMap<Char, MutableList<Char>> = HashMap()
        val count: MutableMap<Char, Int> = HashMap()
        val output = StringBuilder()

        // Step 0: Put all unique letters into reverseAdjList as keys.
        for (word in words) {
            for (c in word) {
                adj.putIfAbsent(c, mutableListOf<Char>())
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
                    adj[char1]!!.add(char2)
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
            for (i in adj[char]!!) {
                count[i] = count[i]!! - 1
                if (count[i] == 0) {
                    queue.offer(i)
                }
            }
        }

        return if (count.size > output.length) return "" else output.toString()
    }
}

object Solution6 {
    var adj = hashMapOf<Char, MutableList<Char>>()
    var ans = StringBuilder()
    var seen = hashMapOf<Char, Boolean>()

    fun alienOrder(words: Array<String>): String {

        for(word in words) {
            for(c in word) {
                adj.putIfAbsent(c, mutableListOf())
            }
        }

        for(i in 0 until words.size-1) {
            var word1 = words[i]
            var word2 = words[i+1]
            if(word1.length > word2.length && word1.startsWith(word2)) return ""
            for(j in 0 until minOf(word1.length,word2.length)) {
                var char1 = word1[j]
                var char2 = word2[j]
                if(char1 != char2) {
                    adj[char2] = adj.getOrDefault(char2, mutableListOf()).apply {
                        this.add(char1)
                    }
                    break
                }
            }
        }

        for((key,value) in adj) {
            if(!dfs(key)) return ""
        }

        return ans.toString()
    }

    private fun dfs(node: Char): Boolean {
        if(seen[node] != null) return seen[node]!!
        seen[node] = false
        for(n in adj[node]!!) {
            if(!dfs(n)) return false
        }
        ans.append(node)
        seen[node] = true
        return true
    }
}

object Solution8 {
    fun alienOrder(words: Array<String>): String {
        var map = hashMapOf<Char,MutableList<Char>>()
        var visited = hashMapOf<Char,Boolean>()

        for (word in words) {
            for (char in word) {
                map.putIfAbsent(char, mutableListOf())
            }
        }

        for(i in 0 until words.size-1) {
            var word1 = words[i]
            var word2 = words[i+1]
            if(word1.length > word2.length) {
                if(word1.startsWith(word2)) return ""
            }
            for(j in 0 until minOf(word1.length,word2.length)) {
                var char1 = word1[j]
                var char2 = word2[j]
                if(char1 != char2) {
                    map[char2] = map.getOrDefault(char2, mutableListOf()).apply {
                        this.add(char1)
                    }
                    break
                }
            }
        }
        var ans = StringBuilder()
        fun dfs(char: Char): Boolean {
            if(visited.contains(char)) {
                return visited[char]!! == false
            }
            visited[char] = false
            for(c in map[char] ?: listOf()) {
                if(dfs(c)) return true
            }
            ans.append(char)
            visited[char] = true
            return false
        }

        for((key,value) in map) {
            if(dfs(key)) return ""
        }

        return ans.toString()
    }
}

fun main() {
    var map = hashMapOf<Int,Int>()
    map.remove(1)
    print(Solution6.alienOrder(arrayOf("z","z")))
}