package com.android.leetcode.dsu

class UnionFind(val N:Int) {
    var root = IntArray(N)

    init {
        UnionFind(N)
    }
    private fun UnionFind(size: Int) {
        root = IntArray(size)
        for (i in 0 until size) {
            root[i] = i
        }
    }

    fun find(x: Int): Int {
        var x = x
        while (x != root[x]) {
            x = root[x]
        }
        return x
    }

    fun union(x: Int, y: Int) {
        val rootX = find(x)
        val rootY = find(y)
        if (rootX != rootY) {
            root[rootY] = rootX
        }
    }

    fun connected(x: Int, y: Int): Boolean {
        return find(x) == find(y)
    }
}


object App {
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val uf = UnionFind(10)
        // 1-2-5-6-7 3-8-9 4
        uf.union(1, 2)
        uf.union(2, 5)
        uf.union(5, 6)
        uf.union(6, 7)
        uf.union(3, 8)
        uf.union(8, 9)
        System.out.println(uf.connected(1, 5)) // true
        System.out.println(uf.connected(5, 7)) // true
        System.out.println(uf.connected(4, 9)) // false
        // 1-2-5-6-7 3-8-9-4
        uf.union(9, 4)
        System.out.println(uf.connected(4, 9)) // true
    }
}