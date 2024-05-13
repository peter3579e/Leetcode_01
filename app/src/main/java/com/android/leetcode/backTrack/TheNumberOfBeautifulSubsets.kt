package com.android.leetcode.backTrack

object TheNumberOfBeautifulSubsets {
    fun beautifulSubsets(nums: IntArray, k: Int): Int {

        if(nums.size == 1 || nums.isEmpty()) return nums.size

        var count = 0

        fun canAdd(list: MutableList<Int>, num: Int): Boolean {
            list.forEach{
                if(Math.abs(it - num) == k) return false
            }
            return true
        }

        fun backTrack(index:Int, temp: MutableList<Int>) {
            if(temp.size == nums.size) {
                return
            }

            for(i in index until nums.size) {
                val cur = nums[i]
                if(canAdd(temp,cur)) {
                    count++
                    temp.add(cur)
                    backTrack(i+1,temp)
                    temp.removeLast()
                }
            }
        }

        backTrack(0,mutableListOf())
        return count
    }

    fun beautifulSubsets2(nums: IntArray, k: Int): Int {

        if(nums.size == 1 || nums.isEmpty()) return nums.size
        nums.sort()

        var count = 0
        val seen = hashMapOf<Int,Int>()

        fun backTrack(index:Int) {
            if(index == nums.size) {
                return
            }

            for(i in index until nums.size) {
                val cur = nums[i]
                if(seen.getOrDefault(cur - k, 0) == 0) {
                    count++
                    seen[cur] = seen.getOrDefault(cur,0) + 1
                    backTrack(i+1)
                    seen[cur] = seen[cur]!! - 1
                }
            }
        }

        backTrack(0)
        return count
    }
}

fun main() {
    print(TheNumberOfBeautifulSubsets.beautifulSubsets2(intArrayOf(4,2,5,9,10,3),1))
}