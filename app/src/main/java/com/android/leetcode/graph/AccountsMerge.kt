package com.android.leetcode.graph

import java.util.Stack


object AccountsMerge {
    val visited = mutableSetOf<String>()
    val adjacent = mutableMapOf<String, MutableList<String>>()

    fun accountsMerge(accounts: List<List<String>>): List<List<String>> {
        accounts.forEach { list ->
            val firstEmail = list[1]
            for (i in 2 until list.size) {
                val theEmail = list[i]
                adjacent[firstEmail] = adjacent.getOrDefault(firstEmail, mutableListOf<String>()).also {
                    it.add(theEmail)
                }
                adjacent[theEmail] = adjacent.getOrDefault(theEmail, mutableListOf<String>()).also {
                    it.add(firstEmail)
                }
            }
        }

        val mergedAccounts = mutableListOf<List<String>>()

        accounts.forEach { account ->
            val name = account[0]
            val firstEmail = account[1]

            if (!visited.contains(firstEmail)) {
                val mergedAccount = mutableListOf<String>()
                mergedAccount.add(name)
                dfs(mergedAccount, firstEmail)
                mergedAccount.subList(1, mergedAccount.size).sort()
                mergedAccounts.add(mergedAccount)
            }
        }

        return mergedAccounts
    }

    fun dfs(mergedAccount: MutableList<String>, email: String) {
        visited.add(email)
        mergedAccount.add(email)
        if (!adjacent.containsKey(email)) return
        adjacent[email]?.forEach { neighbor ->
            if (!visited.contains(neighbor)) {
                dfs(mergedAccount, neighbor)
            }
        }
    }
}

object Solution2 {
    fun accountsMerge(accounts: List<List<String>>): List<List<String>> {
        val n = accounts.size
        val ds = UnionFind(n)
        val mapMailNode = HashMap<String, Int>()
        for (i in 0 until n) {
            for (j in 1 until accounts[i].size) {
                val mail = accounts[i][j]
                if (!mapMailNode.containsKey(mail)) {
                    mapMailNode[mail] = i
                } else {
                    ds.union(i, mapMailNode[mail]!!)
                }
            }
        }
        val mergedMail = hashMapOf<Int, MutableList<String>>()

        for ((key, value) in mapMailNode) {
            val mail = key
            val root = ds.find(value)
            mergedMail[root] = mergedMail.getOrDefault(root, mutableListOf()).apply {
                this.add(mail)
            }
        }
        val ans = mutableListOf<MutableList<String>>()
        for (i in 0 until n) {
            if (mergedMail[i] == null) continue
            mergedMail[i]!!.sort()
            val temp = mutableListOf<String>()
            temp.add(accounts[i][0])
            temp.addAll(mergedMail[i]!!)
            ans.add(temp)
        }
        return ans
    }

    class UnionFind(val N: Int) {
        private val root = IntArray(N) { it }

        fun find(x: Int): Int = if (x == root[x]) x else find(root[x])

        fun union(x: Int, y: Int) {
            val rootX = find(x)
            val rootY = find(y)
            if (rootX != rootY) {
                root[rootY] = rootX
            }
        }
    }
}

fun main() {
    var stack = Stack<Int>()
    var ans = Array<IntArray>(stack.size) { intArrayOf()}
    AccountsMerge.accountsMerge(listOf(listOf("John","johnsmith@mail.com","john_newyork@mail.com"),
        listOf("John","johnsmith@mail.com","john00@mail.com"), listOf("Mary","mary@mail.com"), listOf("John","johnnybravo@mail.com")))
}