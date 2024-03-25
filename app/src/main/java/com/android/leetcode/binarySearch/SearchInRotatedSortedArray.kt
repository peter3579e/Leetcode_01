package com.android.leetcode.binarySearch

object SearchInRotatedSortedArray {
    fun search(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size - 1

        while (right > left) {
            var mid = (left + right) / 2
            if (nums[mid] > nums[right]) {
                left = mid + 1
            } else {
                right = mid
            }
        }

        return if (nums[left] == target) left
        else if (target < nums[left - 1]) {
            binarySerach(0, left - 1, nums, target)
        } else {
            binarySerach(left, nums.size - 1, nums, target)
        }
    }

    fun binarySerach(left: Int, right: Int, nums: IntArray, target: Int): Int {
        var l = left
        var r = right

        while (r >= l) {
            var mid = (l + r) / 2
            if (target == nums[mid]) return mid
            if (target > nums[mid]) {
                l = mid + 1
            } else {
                r = mid - 1
            }
        }

        return -1
    }
}

object Solution2 {
    fun search(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size-1

        while(left < right) {
            var mid = (left + right) / 2
            if(nums[mid] >= nums[right]) {
                left = mid + 1
            }else {
                right = mid
            }
        }

        var ans = binarySearch(0,left-1,target,nums)
        if(ans != -1) return ans

        return binarySearch(left,nums.size-1,target,nums)
    }


    private fun binarySearch(left: Int, right: Int, target: Int, nums:IntArray): Int {
        var l = left
        var r = right

        while(l <= r) {
            var mid = (l + r) / 2
            if(nums[mid] == target) {
                return mid
            }else if(nums[mid] > target) {
                r = mid - 1
            }else {
                l = mid + 1
            }
        }

        return -1
    }
}

object Solution4 {
    fun search(nums: IntArray, target: Int): Int {
        /*
        4 5 6 7 0 1 2

        7 0 1  2    4 5 6
        0 1     3        6
         */
        // find pivot
        var left = 0
        var right = nums.size - 1

        while(left < right) {
            val mid = left + (right - left) / 2
            if(nums[mid] > nums[mid+1]) {
                left = mid
                break
            } else if (nums[mid] < nums[nums.lastIndex] ) {
                right = mid
            } else {
                left = left + 1
            }
        }

        //check right hand
        val resultRight = binarySearch(left, nums.size-1, nums, target)
        if(resultRight != -1) return resultRight
        return binarySearch(0, left-1, nums, target)
    }

    private fun binarySearch(left: Int, right: Int, nums: IntArray, target: Int): Int {
        var l = left
        var r = right

        while( l <= r ) {
            val mid = l + (r-l)/2
            if(nums[mid] == target) return mid
            else if(nums[mid] < target) {
                l = mid + 1
            } else {
                r = mid - 1
            }
        }

        return -1
    }

    fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
        points.sortWith() { int1, int2 ->
            (Math.pow(int1[0].toDouble(),2.0) + Math.pow(int1[1].toDouble(),2.0)).compareTo((Math.pow(int2[0].toDouble(),2.0) + Math.pow(int2[1].toDouble(),2.0)))
        }
        var ans = points.toList().subList(points.size-1,points.size-1-k)
        return ans.toTypedArray()
    }
}

fun main() {
    var num = 937
    var string = num.toString().toCharArray()

    print(Solution4.search(intArrayOf(3,1), 1))
}