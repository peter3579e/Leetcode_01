package com.android.leetcode.backTrack

object CombinationSumII {
    //[1,1,2,5,6,7,10]
//    1
//    1,1 1,2 1,5 1,6 1,7 1,10
//    1,1,6
    var ans = mutableListOf<List<Int>>()
    fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
        candidates.sorted() // time complexity O(NlogN)
        backTrack(0,candidates,mutableListOf<Int>(), target)
        return ans
    }

    fun backTrack(sum: Int, array: IntArray, tempList: MutableList<Int>, target: Int) {

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

fun main() {
    print(CombinationSumII.combinationSum2(intArrayOf(10,1,2,7,6,1,5),8))
}