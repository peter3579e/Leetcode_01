package com.android.leetcode.graph

object NestedListWeightSum {

    private var sum = 0

    fun depthSum(nestedList: List<NestedInteger>, level: Int = 1): Int {

        for(list in nestedList) {
            if(list.isInteger()) {
                sum = sum + (level*list.getInteger()!!)
            } else {
                depthSum(nestedList, level+1)
            }
        }

        return sum
    }
}

//mock
class NestedInteger {

    fun isInteger():Boolean {
        return true
    }

    fun getInteger(): Int {
        return 1
    }
}