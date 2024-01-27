package com.android.leetcode.listNode

import java.util.LinkedList

class LRUCache(capacity: Int) {
    val head = Node(0,0)
    val tail = Node(0,0)
    val capacity = capacity
    var size = 0

    init{
        head.next = tail
        tail.prev = head
    }

    fun get(key: Int): Int {
        var cur = head.next
        while (cur?.key != 0) {
            if (cur?.key == key) {
                remove(cur)
                insert(cur)
                return cur.value
            }
            cur = cur?.next
        }
        return -1
    }

    fun put(key: Int, value: Int) {
        var cur = head.next
        while (cur?.key != 0) {
            if (cur?.key == key) {
                cur.value = value
                remove(cur)
                insert(cur)
                break
            }
            cur = cur?.next
        }
        while(size >= capacity && cur?.key != key){
            remove(tail.prev!!)
        }
        if (cur?.key != key) insert(Node(key,value))
    }

    private fun remove(node: Node){
        val prev = node.prev
        val next = node.next
        prev?.next = node.next
        next?.prev = node.prev
        size --
    }

    private fun insert(node: Node){
        val headNext = head.next
        head.next =  node
        headNext?.prev = node
        node.next = headNext
        node.prev = head
        size ++
    }

}

class LRUCacheMap(capacity: Int) {
    val head = Node(0,0)
    val tail = Node(0,0)
    val capacity = capacity
    var map = hashMapOf<Int,Node>()

    init{
        head.next = tail
        tail.prev = head
    }

    fun get(key: Int): Int {
        if (map[key] != null){
                var node = map[key]
                remove(node!!)
                insert(node)
                return node.value
        }
        return -1
    }

    fun put(key: Int, value: Int) {
        if (map[key] != null) {
            remove(map[key]!!)
        }
        if (map.size >= capacity) remove(tail.prev!!)

        insert(Node(key,value))
    }

    private fun remove(node: Node){
        map.remove(node.key)
        val prev = node.prev
        val next = node.next
        prev?.next = node.next
        next?.prev = node.prev
    }

    private fun insert(node: Node){
        val headNext = head.next
        map[node.key] = node
        head.next =  node
        headNext?.prev = node
        node.next = headNext
        node.prev = head
    }

}

data class Node(
    val key: Int,
    var value: Int,
    var prev: Node? = null,
    var next: Node? = null
)

fun main() {
    var lruCache = LRUCacheMap(2)

    with(lruCache) {
        put(2,1)
        put(1,1)
        put(2,3)
        put(4,1)
        print(get(1))
        print(get(2))
    }

}
