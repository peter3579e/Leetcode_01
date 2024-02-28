package com.android.leetcode.graph.dsu

class UnionFind(val N:Int) {
    private val root = IntArray(N) { it }

    fun find(point: Int): Int = if (point == root[point]) point else find(root[point])

    fun union(parent: Int, children: Int) {
        val rootX = find(parent)
        val rootY = find(children)
        if (rootX != rootY) {
            root[rootY] = rootX
        }
    }

    fun connected(x: Int, y: Int): Boolean {
        return find(x) == find(y)
    }
}

class UnionFind2(val N: Int) {
    private var root = IntArray(N) { it }

    fun find(point: Int): Int = if (point == root[point]) point else find(root[point])

    fun union(parent: Int, children: Int) {
        val rootPar = find(parent)
        val rootChild = find(children)
        if (rootPar != rootChild) {
            root[rootChild] = rootPar
        }
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
        System.out.println(uf.connected(7, 9)) // false
        // 1-2-5-6-7 3-8-9-4
        uf.union(9, 7)
        System.out.println(uf.connected(7, 9)) // true
    }
}