package com.android.leetcode.graph

import java.util.LinkedList


object CloneGraph {

    internal class Solution {
        class Node(var `val`: Int) {
            var neighbors: ArrayList<Node?> = ArrayList<Node?>()
        }
        fun cloneGraph(node: Node?): Node? {
            if (node == null) {
                return node
            }

            // Hash map to save the visited node and it's respective clone
            // as key and value respectively. This helps to avoid cycles.
            val visited = hashMapOf<Node,Node>()

            // Put the first node in the queue
            val queue: LinkedList<Node> = LinkedList<Node>()
            queue.add(node)
            // Clone the node and put it in the visited dictionary.
            visited[node] = Node(node.`val`)

            // Start BFS traversal
            while (!queue.isEmpty()) {
                // Pop a node say "n" from the from the front of the queue.
                val n: Node = queue.remove()
                // Iterate through all the neighbors of the node "n"
                for (neighbor in n.neighbors) {
                    if (!visited.containsKey(neighbor)) {
                        // Clone the neighbor and put in the visited, if not present already
                        visited[neighbor!!] = Node(neighbor.`val`)
                        // Add the newly encountered node to the queue.
                        queue.add(neighbor)
                    }
                    // Add the clone of the neighbor to the neighbors of the clone node "n".
                    visited[n]!!.neighbors.add(visited[neighbor])
                }
            }

            // Return the clone of the node from visited.
            return visited[node]
        }
    }

}