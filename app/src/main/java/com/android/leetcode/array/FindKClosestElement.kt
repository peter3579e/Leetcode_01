package com.android.leetcode.array

import java.util.PriorityQueue

object FindKClosestElement {

    fun findClosestElements(arr: IntArray, k: Int, x: Int): List<Int> {
        var map = hashMapOf<Int,Int>()

        for (i in 0 until arr.size) {
            map[i] = Math.abs(arr[i]-x)
        }
        var queue = PriorityQueue {n1: Int, n2:Int ->
            if(map[n1]!! == map[n2]!!) {
                arr[n1].compareTo(arr[n2])
            }else {
                map[n1]!!.compareTo(map[n2]!!)
            }
        }

        map.forEach {(key,value) ->
            queue.offer(key)
        }

        var ans = mutableListOf<Int>()

        for(i in 0 until k) {
            ans.add(arr[queue.poll()])
        }

        return ans.sorted()
    }
}
object Optimal {
    fun findClosestElements(arr: IntArray, k: Int, x: Int): List<Int> {
        var curIndex = binarySearch(arr, x)
        var left = curIndex - 1
        var right = curIndex

        while(right-left-1 < k) {

            if(left == -1) {
                right++
                continue
            }

            if(right == arr.size || Math.abs(arr[left]-x) <= Math.abs(arr[right]-x)) {
                left --
            }else{
                right++
            }
        }

        val result = mutableListOf<Int>()

        for (i in left + 1 until right) {
            result.add(arr[i])
        }

        return result
    }

    fun binarySearch(arr:IntArray, x: Int): Int {
        var left = 0
        var right = arr.size
        var mid = 0
        while (left < right) {
            mid = (left + right) / 2
            if (arr[mid] >= x) {
                right = mid
            } else {
                left = mid + 1
            }
        }
        return left
    }
}

// this failed
object mySolution {
    fun findClosestElements(arr: IntArray, k: Int, x: Int): List<Int> {

        var left = 0
        var right = arr.size - 1

        while(left < right) {
            var mid = (left+right) / 2
            if(x == arr[mid]) {
                left = x
                break
            }else if(arr[mid] > x) {
                right = mid
            }else {
                left = mid + 1
            }
        }

        var ans = mutableListOf<Int>()
        var l = left
        var r = left

        while (ans.size < k) {
            if (!isVaild(l - 1, arr)) {
                ans.add(arr[r])
                r++
            }else if (!isVaild(r + 1, arr)) {
                ans.add(arr[r])
                l--
            }

            if (isVaild(l - 1, arr) && isVaild(r + 1, arr)) {
                if (Math.abs(x - arr[l - 1]) <= Math.abs(x - arr[r + 1])) {
                    l--
                    ans.add(0,arr[l])
                } else if (Math.abs(x - arr[l - 1]) > Math.abs(x - arr[r + 1])) {
                    r++
                    ans.add(arr[r])
                }
            }
        }

        return ans
    }

    private fun isVaild(index:Int, arr: IntArray) = index < arr.size && index >= 0
}

fun main () {
//   var s = "123"
//    var newString = s.removeRange(1,2)
//    print(newString)
    print(FindKClosestElement.findClosestElements(intArrayOf(1,2), 1, 1))
}