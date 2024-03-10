package com.android.leetcode.graph

import java.util.LinkedList

object CourseSchedule {
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {

        val preToCourse = hashMapOf<Int, MutableList<Int>>()

        for(i in prerequisites) {
            var cour = i[0]
            var pre = i[1]
            preToCourse[pre] = preToCourse.getOrDefault(pre,mutableListOf()).apply {
                this.add(cour)
            }
        }

        var courseToNumPre = hashMapOf<Int,Int>()

        for(i in 0 until numCourses) {
            courseToNumPre.putIfAbsent(i,0)
        }

        for(i in prerequisites) {
            var cour = i[0]
            courseToNumPre[cour] = courseToNumPre[cour]!! + 1
        }
        var queue = LinkedList<Int>()
        for(i in 0 until numCourses) {
            if(courseToNumPre[i] == 0) {
                queue.offer(i)
                courseToNumPre.remove(i)
            }
        }

        if(queue.isEmpty()) return false

        while(queue.isNotEmpty()) {
            var cur = queue.poll()!!
            if(preToCourse.contains(cur)) {
                for(cour in preToCourse[cur]!!) {
                    courseToNumPre[cour] = courseToNumPre[cour]!! - 1
                    if(courseToNumPre[cour]!! == 0) {
                        queue.offer(cour)
                        courseToNumPre.remove(cour)
                    }
                }
            }
        }

        return if(courseToNumPre.isEmpty()) true else false
    }
}

object SolutionDFS {
    val preToCourse = hashMapOf<Int, MutableList<Int>>()
    var seen = hashMapOf<Int,Boolean>()

    fun dfs(node: Int): Boolean {
        if (seen.containsKey(node)) {
            return seen[node] == false // Return false if currently being visited
        }
        seen[node] = false // Mark as currently being visited
        for (n in preToCourse[node] ?: emptyList()) {
            if (dfs(n)) return true
        }
        seen[node] = true // Mark as visited
        return false
    }

    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {

        for(i in prerequisites) {
            var cour = i[0]
            var pre = i[1]
            preToCourse[pre] = preToCourse.getOrDefault(pre,mutableListOf()).apply {
                this.add(cour)
            }
        }

        for(i in 0 until numCourses) {
            if(dfs(i)) {
                return false
            }
        }

        return true
    }
}

fun main() {

    SolutionDFS.canFinish(2, arrayOf(intArrayOf(1,0)))
}