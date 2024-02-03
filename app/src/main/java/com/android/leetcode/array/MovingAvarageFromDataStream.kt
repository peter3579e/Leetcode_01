package com.android.leetcode.array

import kotlin.math.min

class MovingAvarageFromDataStream(size: Int) {
    var size = size
    var array = mutableListOf<Int>()

    fun next(`val`: Int): Double {
        array.add(`val`)
        var ans = 0.0
        if(array.size < size) return array.sum()/array.size.toDouble()
        for(i in array.size-1 downTo array.size-size) {
            ans = ans + array[i].toDouble()/size.toDouble()
        }
        return ans
    }
}

// sliding window
class MovingAverage(size: Int) {
    var size = size
    var array = mutableListOf<Int>()

    fun next(`val`: Int): Double {
        array.add(`val`)
        var lo = 0
        var sum = 0
        var boolean = false
        for(i in 0..array.size-1) {
            sum = sum + array[i]
            while(i-lo > size-1) {
                boolean = true
                sum = sum - array[lo]
                lo++
            }
        }

        return if(boolean) sum.toDouble()/size.toDouble() else sum.toDouble()/array.size.toDouble()
    }

}

class MovingAverage2(size: Int) {
    var deque = ArrayDeque<Int>()
    var size = size
    var sum = 0

    fun next(`val`: Int): Double {
        var count = 0
        deque.add(`val`)
        count ++
        sum += `val`
        if (count > size) {
            sum -= deque.removeFirst()
        }
        return sum*1.0 / min(count,size)
    }
}

fun main() {


}