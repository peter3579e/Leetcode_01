package com.android.leetcode.graph


object CourseScheduleII {
    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        if (numCourses == 0) return intArrayOf(0)

        val preToCour = hashMapOf<Int, MutableList<Int>>()
        for (arr in prerequisites) {
            var cour = arr[0]
            var pre = arr[1]
            preToCour[pre] = preToCour.getOrDefault(pre,mutableListOf()).apply {
                this.add(cour)
            }
        }

        var visited = hashMapOf<Int,Boolean>()
        var ans = mutableListOf<Int>()

        fun dfs(n: Int): Boolean {
            if (visited.contains(n)) {
                return visited[n]!! == false
            }
            visited[n] = false
            for (cour in preToCour[n] ?: listOf()) {
                if(dfs(cour)) return true
            }
            ans.add(n)
            visited[n] = true
            return false
        }

        for (i in 0 until numCourses) {
            if(dfs(i)) return intArrayOf()
        }

        return ans.reversed().toIntArray()
    }
}

class SparseVector(nums: IntArray) {
    // Map the index to value for all non-zero values in the vector
    var mapping: MutableMap<Int, Int> = HashMap()

    init {
        for (i in nums.indices) {
            if (nums[i] != 0) {
                mapping[i] = nums[i]
            }
        }
    }

    fun dotProduct(vec: SparseVector): Int {
        var result = 0

        // iterate through each non-zero element in this sparse vector
        // update the dot product if the corresponding index has a non-zero value in the other vector
        for (i in mapping.keys) {
            if (vec.mapping.containsKey(i)) {
                result += mapping[i]!! * vec.mapping[i]!!
            }
        }
        return result
    }
}