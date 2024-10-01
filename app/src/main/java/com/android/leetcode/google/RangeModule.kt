package com.android.leetcode.google

import java.util.TreeMap

object RangeModule {

    data class Range(var start: Int, var end: Int)

        val ranges = TreeMap<Int, Int>()

        fun addRange(left: Int, right: Int) {
            var start = left
            var end = right

            val leftEntry = ranges.floorEntry(left)
            val rightEntry = ranges.floorEntry(right)

            if (leftEntry != null && leftEntry.value >= left) {
                start = leftEntry.key
            }
            if (rightEntry != null && rightEntry.value > right) {
                end = rightEntry.value
            }

            ranges.subMap(start, end).clear()
             ranges[start] = end
        }

        fun queryRange(left: Int, right: Int): Boolean {
            val leftEntry = ranges.floorEntry(left)
            return leftEntry != null && leftEntry.value >= right
        }

        fun removeRange(left: Int, right: Int) {
            val leftEntry = ranges.floorEntry(left)
            val rightEntry = ranges.floorEntry(right)

            if (leftEntry != null && leftEntry.value > left) {
                ranges[leftEntry.key] = left
            }
            if (rightEntry != null && rightEntry.value > right) {
                ranges[right] = rightEntry.value
            }

            ranges.subMap(left, right).clear()
        }
}

object RangeModule2 {
    var list = mutableListOf<IntRange>()

    fun addRange(left: Int, right: Int) {
        if (list.isEmpty()) {
            list.add(IntRange(left,right))
        } else {
            list = insert(IntRange(left,right),list)
        }
    }

    private fun insert(insert: IntRange, list: MutableList<IntRange>): MutableList<IntRange> {
        val newList = mutableListOf<IntRange>()
        var pointer1 = 0
        var pointer2 = 0

        fun merge(range: IntRange) {
            if (newList.isEmpty() || newList.last().last < range.first) {
                newList.add(range)
            } else {
                val top = newList.removeLast()
                newList.add(IntRange(top.first,maxOf(range.last,top.last)))
            }
        }

        while (pointer2 < list.size || pointer1 < 1) {
            if (pointer2 == list.size) {
                merge(insert)
                pointer1++
            }else if (pointer1 == 1) {
                merge(list[pointer2])
                pointer2++
            }else {
                if (insert.first <= list[pointer2].first) {
                    merge(insert)
                    pointer1++
                } else {
                    merge(list[pointer2])
                    pointer2++
                }
            }
        }

        return newList
    }

    fun queryRange(left: Int, right: Int): Boolean {
        if(list.isNotEmpty()) {
            list.forEach {
                if(left in it && right in it) return true
            }
        }
        return false
    }
    /*
    ****
   *******
   ***
       ****
    *****

     */

    fun removeRange(left: Int, right: Int) {
        var count = 0
        while (count < list.size) {
            val range = list[count]
            if(maxOf(left,range.first) < minOf(right,range.last)) {
                when {
                    (left < range.first && right > range.last) || (left == range.first && right == range.last) -> {
                        list.removeAt(count)
                    }
                    left > range.first && right < range.last -> {
                        val pop = list.removeAt(count)
                        val first = IntRange(pop.first, left)
                        val second = IntRange(right,pop.last)
                        list = insert(first,list)
                        list = insert(second, list)
                        count = count + 2
                    }
                    left !in range && right in range -> {
                        val pop = list.removeAt(count)
                        val insert = IntRange(right,pop.last)
                        list = insert(insert, list)
                        count++
                    }
                    left in range && right !in range -> {
                        val pop = list.removeAt(count)
                        val insert = IntRange(pop.first,left)
                        list = insert(insert, list)
                        count++
                    }
                    left == range.first && right in range -> {
                        val pop = list.removeAt(count)
                        val insert = IntRange(right,pop.last)
                        list = insert(insert, list)
                        count++
                    }
                    right == range.last && left in range -> {
                        val pop = list.removeAt(count)
                        val insert = IntRange(pop.first,left)
                        list = insert(insert, list)
                        count++
                    }
                }
            } else {
                count ++
            }
        }
    }
}

object RangeModule3 {
    /*               *****
    *  map       ****
    *  insert      ****
    * */

    val map = TreeMap<Int,Int>()

    fun addRange(left: Int, right: Int) {
        var start = left
        var end = right

        map.floorEntry(left)?.let {
            if (it.value >= left) start = it.key
        }

        map.floorEntry(right)?.let {
            if (it.value >= right) end = it.value
        }

        map.subMap(start,end).clear()
        map[start] = end
    }

    fun queryRange(left: Int, right: Int): Boolean {
        val key = map.floorEntry(left)
        return key != null && key.value >= right
    }

    /*
    map
     ********
              ********
         *******
         *******
     */

    fun removeRange(left: Int, right: Int) {

        val leftEntry = map.floorEntry(left)
        val rightEntry = map.floorEntry(right)

        if (leftEntry != null && leftEntry.value > left) {
            map[leftEntry.key] = left
        }
        if (rightEntry != null && rightEntry.value > right) {
            map[right] = rightEntry.value
        }
    }

    fun print() {
        map.forEach {
            println("key ${it.key} + value ${it.value}")
        }
    }


}

fun main() {

//    with(RangeModule) {
//        addRange(5,8)
//        queryRange(3,4)
//        removeRange(5,6)
//        removeRange(3,6)
//        addRange(1,3)
//        queryRange(2,3)
//        addRange(4,8)
//        queryRange(2,3)
//        removeRange(4,9)
//    }

    with(RangeModule3) {
        addRange(10,20)
        removeRange(14,16)
        queryRange(10,14)
        queryRange(13,15)
        queryRange(16,17)
    }

//    RangeModule.queryRange(4,7)
//    println(RangeModule2.addRange(3,5))
//    println(RangeModule2.addRange(1,2))
//    println(RangeModule2.removeRange(1,2))
//    println(RangeModule2.removeRange(5,9))
//    RangeModule2.queryRange(6,7)
//    println(RangeModule2.addRange(5,6))
//    RangeModule2.queryRange(1,3)
//    println(RangeModule2.removeRange(1,8))
}