package com.android.leetcode.google

import java.util.LinkedList

object DeleteNodesAndReturnForest {

    class TreeNode(var `val`: Int) {
            var left: TreeNode? = null
            var right: TreeNode? = null
    }

    fun delNodes(root: TreeNode?, to_delete: IntArray): List<TreeNode?> {
        if(root == null) return emptyList()
        val adj = hashMapOf<TreeNode,MutableList<TreeNode>>()

        val set = mutableSetOf<Int>()

        to_delete.forEach {
            set.add(it)
        }


        val queue = LinkedList<TreeNode>()
        queue.offer(root)
        var ans = mutableListOf<TreeNode?>()

        while(queue.isNotEmpty()) {
            val cur = queue.poll()!!
            cur.left?.let {
                adj[cur] = adj.getOrDefault(cur,mutableListOf()).apply{
                    this.add(it)
                }
                queue.offer(it)
            }
            cur.right?.let {
                queue.offer(it)
                adj[cur] = adj.getOrDefault(cur,mutableListOf()).apply{
                    this.add(it)
                }
            }
        }

        /*
            adj
            1 2,3
            2, 4,5
            3, 6,7

            1,2,4
         */

        val visited = mutableSetOf<TreeNode>()

        fun dfs(root:TreeNode?) {
            if(root == null || visited.contains(root)) return
            visited.add(root)

            for(node in adj[root] ?: listOf()) {
                if(!set.contains(node.`val`)) dfs(node)
            }
        }

        for((key,value) in adj) {
            if(!visited.contains(key) && !set.contains(key.`val`))  {
                dfs(key)
                ans.add(key)
            } else if(set.contains(key.`val`)) {
                key.left?.let {
                    dfs(it)
                    ans.add(it)
                }
                key.right?.let {
                    dfs(it)
                    ans.add(it)
                }
            }
        }

        return ans

    }

    fun delNodes2(root: TreeNode?, to_delete: IntArray): List<TreeNode?> {
        if(root == null) return emptyList()

        val ans = mutableListOf<TreeNode?>()

        val set = mutableSetOf<Int>()

        to_delete.forEach {
            set.add(it)
        }


        fun dfs(root:TreeNode?) {
            if(root == null) return

            if(set.contains(root.`val`)) {
                root.left?.let {
                    if(!set.contains(it.`val`)) ans.add(it)
                }
                root.right?.let{
                    if(!set.contains(it.`val`)) ans.add(it)
                }
            }

            root.left?.let {
                dfs(it)
                if(set.contains(it.`val`)) {
                    root.left = null
                }
            }
            root.right?.let {
                dfs(it)
                if(set.contains(it.`val`)) {
                    root.right = null
                }
            }
        }

        if(set.contains(root.`val`)) {
            root.left?.let {
                if(!set.contains(it.`val`)) ans.add(it)
                dfs(it)
            }
            root.right?.let{
                if(!set.contains(it.`val`)) ans.add(it)
                dfs(it)
            }
        } else {
            ans.add(root)
            dfs(root)
        }

        return ans

    }

    fun delNodesOptimal(root: TreeNode?, to_delete: IntArray): List<TreeNode?> {

        val ans = mutableListOf<TreeNode>()
        val set = to_delete.toSet()

        fun dfs(root: TreeNode?, parent: TreeNode?, isLeft: Boolean) {
            if(root == null) return

            dfs(root.left,root,true)
            dfs(root.right,root,false)

            if(set.contains(root.`val`)) {
                root.left?.let {
                    ans.add(it)
                }
                root.right?.let{
                    ans.add(it)
                }
                parent?.let {
                    if(isLeft) it.left = null else it.right = null
                }
            }
        }

        dfs(root,null,false)

        return ans
    }
}

fun main() {
    with(DeleteNodesAndReturnForest) {
        val one = DeleteNodesAndReturnForest.TreeNode(1)
        val two = DeleteNodesAndReturnForest.TreeNode(2)
        val three = DeleteNodesAndReturnForest.TreeNode(3)
        val four = DeleteNodesAndReturnForest.TreeNode(4)
        val five =DeleteNodesAndReturnForest.TreeNode(5)
        val six = DeleteNodesAndReturnForest.TreeNode(6)
        val seven = DeleteNodesAndReturnForest.TreeNode(7)
        one.left = two
        one.right = three
        two.left = four
        two.right = five
        three.left = six
        three.right = seven
        delNodes2(one, intArrayOf(3,5))
    }
}