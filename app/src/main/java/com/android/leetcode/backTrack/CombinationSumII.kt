package com.android.leetcode.backTrack

object CombinationSumII {
    //[1,1,2,5,6,7,10]
//    1
//    1,1 1,2 1,5 1,6 1,7 1,10
//    1,1,6
    var ans = mutableListOf<List<Int>>()
    fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
        var newArray = candidates.sortedArray()
        backTrack(0,newArray,mutableListOf<Int>(), target)
        return ans
    }

    fun backTrack(sum: Int, array: IntArray, tempList: MutableList<Int>, target: Int) {
        // 1,1,2,5,6,7,10

        if(sum == target){
            ans.add(tempList.toList())
            return
        }else if(sum > target) return

        // time complexity O(N log N + m * 2^n) and space complexity is O(n)
        /*
        "2^n represents the number of possible combinations" means that for each element in the array,
        you have two choices (include or exclude), and when you have n such elements, the total number of possible combinations becomes 2^n
         */
        for(i in 0..array.size-1) {
            var cur = array[i]
            if (i > 0 && cur == array[i-1]) continue
            tempList.add(cur)
            backTrack(sum+cur, array.copyOfRange(i+1, array.size), tempList, target)
            tempList.removeAt(tempList.size-1)
        }
    }
}


object Solution3 {
    var ans = mutableListOf<List<Int>>()
    var temp = mutableListOf<Int>()
    var sum = 0
    fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
        var sort = candidates.sortedArray()
        backTrack(sort, target, 0)
        return ans
    }

    fun backTrack(arr: IntArray, target: Int, index: Int) {
        if (sum > target) return
        else if (sum == target) {
            ans.add(temp.toList())
            return
        }
        for (i in index until arr.size) {
            var cur = arr[i]
            if (i != index && cur == arr[i - 1]) continue
            sum = sum + cur
            temp.add(cur)
            backTrack(arr, target, i + 1)
            temp.removeAt(temp.size - 1)
            sum = sum - cur
        }
    }
}

fun main() {
    print(Solution3.combinationSum2(intArrayOf(3,1,3,5,1,1),8))
}