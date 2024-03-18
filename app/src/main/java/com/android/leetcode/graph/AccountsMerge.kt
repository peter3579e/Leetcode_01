package com.android.leetcode.graph

import java.util.LinkedList
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

object Solution7 {
    private val adj = hashMapOf<String, MutableList<String>>()
    private val visited = mutableSetOf<String>()

    fun accountsMerge(accounts: List<List<String>>): List<List<String>> {

        for(i in 0 until accounts.size) {
            var firstEmail = accounts[i][1]
            for(j in 2 until accounts[i].size) {
                var secondEmail = accounts[i][j]

                adj[firstEmail] = adj.getOrDefault(firstEmail, mutableListOf()).apply {
                    this.add(secondEmail)
                }

                adj[secondEmail] = adj.getOrDefault(secondEmail, mutableListOf()).apply {
                    this.add(firstEmail)
                }
            }
        }

        /*
        johnsmith@mail.com = john_newyork@mail.com, john00@mail.com
        john_newyork@mail.com = johnsmith@mail.com
        john00@mail.com = johnsmith@mail.com
         */
        var ans = mutableListOf<List<String>>()
        for(account in accounts) {
            var temp = mutableListOf<String>()
            temp.add(account[0])
            var email = account[1]
            dfs(email, temp)
            if(temp.size == 1) continue
            temp.subList(1,temp.size).sort()
            ans.add(temp.toList())
        }

        return ans
    }

    fun dfs(email: String, temp: MutableList<String>) {
        if(visited.contains(email)) return
        temp.add(email)
        visited.add(email)

        if(adj.contains(email)) {
            for(e in adj[email]!!) {
                dfs(e, temp)
            }
        }
    }
}

object SolutionBFS {
    fun accountsMerge(accounts: List<List<String>>): List<List<String>> {

        val adj = hashMapOf<String, MutableList<String>>()
        val visited = mutableSetOf<String>()

        for(i in 0 until accounts.size) {
            var firstEmail = accounts[i][1]
            for(j in 2 until accounts[i].size) {
                var secondEmail = accounts[i][j]

                adj[firstEmail] = adj.getOrDefault(firstEmail, mutableListOf()).apply {
                    this.add(secondEmail)
                }

                adj[secondEmail] = adj.getOrDefault(secondEmail, mutableListOf()).apply {
                    this.add(firstEmail)
                }
            }
        }

        /*
        johnsmith@mail.com = john_newyork@mail.com, john00@mail.com
        john_newyork@mail.com = johnsmith@mail.com
        john00@mail.com = johnsmith@mail.com
         */
        var ans = mutableListOf<List<String>>()
        var queue = LinkedList<String>()

        for(account in accounts) {
            var temp = mutableListOf<String>()
            temp.add(account[0])
            var firstEmail = account[1]
            if(visited.contains(firstEmail)) continue
            queue.offer(firstEmail)
            while(queue.isNotEmpty()) {
                var cur = queue.poll()!!
                temp.add(cur)
                visited.add(cur)
                if(adj.contains(cur)) {
                    for(e in adj[cur]!!) {
                        if(!visited.contains(e)) {
                            queue.offer(e)
                            visited.add(e)
                        }
                    }
                }
            }
            temp.subList(1,temp.size).sort()
            ans.add(temp.toList())
        }

        return ans
    }
}

object SolutionDFS2 {
    fun accountsMerge(accounts: List<List<String>>): List<List<String>> {

        val emailToEmails = hashMapOf<String,MutableList<String>>()

        for (account in accounts) {
            val firstEmail = account[1]
            for (i in 2 until account.size) {
                val secondEmail = account[i]
                emailToEmails[firstEmail] = emailToEmails.getOrDefault(firstEmail,mutableListOf()).apply {
                    this.add(secondEmail)
                }
                emailToEmails[secondEmail] = emailToEmails.getOrDefault(secondEmail,mutableListOf()).apply {
                    this.add(firstEmail)
                }
            }
        }
        /*

         john1 = john2, john3
            john2 = john1
            john3 = john1

         */
        val visited = mutableSetOf<String>()
        val ans = mutableListOf<List<String>>()
        var temp = mutableListOf<String>()

        fun dfs(email: String) {
            if(visited.contains(email)) return
            temp.add(email)
            visited.add(email)

            for(em in emailToEmails[email] ?: listOf()) {
                if(!visited.contains(em)) {
                    dfs(em)
                }
            }
        }

        for(account in accounts) {
            val firstEmail = account[1]
            if(visited.contains(firstEmail)) continue
            visited.add(firstEmail)
            temp.add(account[0])
            dfs(firstEmail)
            temp.subList(1,temp.size).sort()
            ans.add(temp)
            temp = mutableListOf()
        }

        return ans
    }
}

fun main() {
    SolutionDFS2.accountsMerge(listOf(listOf("Alex","Alex5@m.co","Alex4@m.co","Alex0@m.co"),
        listOf("Ethan","Ethan3@m.co","Ethan3@m.co","Ethan0@m.co"), listOf("Kevin","Kevin4@m.co","Kevin2@m.co","Kevin2@m.co"), listOf("Gabe","Gabe0@m.co","Gabe3@m.co","Gabe2@m.co"), listOf("Gabe","Gabe3@m.co","Gabe4@m.co","Gabe2@m.co")))
}