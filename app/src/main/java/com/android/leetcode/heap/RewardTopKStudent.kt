package com.android.leetcode.heap

import java.util.PriorityQueue

object RewardTopKStudent {
    fun topStudents(positive_feedback: Array<String>, negative_feedback: Array<String>, report: Array<String>, student_id: IntArray, k: Int): List<Int> {

        // space O(N)
        var map = hashMapOf<Int,Int>()

        var ans = mutableListOf<Int>()

        // space O(k)
        var queue = PriorityQueue { n1: Int, n2: Int ->
            if(map[n2]!! == map[n1]!!) {
                n2.compareTo(n1)
            } else {
                map[n1]!!.compareTo(map[n2]!!)
            }
        }

        // time O(N*M)
        for (i in 0..report.size-1) {
            for (j in report[i].split(" ")) {
                when {
                    positive_feedback.contains(j) -> map[student_id[i]] = map.getOrDefault(student_id[i], 0) + 3
                    negative_feedback.contains(j) -> map[student_id[i]] = map.getOrDefault(student_id[i], 0) - 1
                }
            }
        }

        //time O(N) -> O(NlogN)
        map.forEach { key, value ->
            //O(logN)
            queue.offer(key)
            if(queue.size > k) queue.poll()
        }

        // time O(k)
        while(queue.isNotEmpty()) {
            ans.add(queue.poll())
        }

        return ans.reversed()
        //chat gpt say time = O(N⋅M+N⋅log(k)+N)
        // time complexity is O(N*M)
        // space complexity is O(N+K)
    }
}

fun main () {
    RewardTopKStudent.topStudents(arrayOf("fkeofjpc","qq","iio"), arrayOf("jdh","khj","eget","rjstbhe","yzyoatfyx","wlinrrgcm"), arrayOf("rjstbhe eget kctxcoub urrmkhlmi yniqafy fkeofjpc iio yzyoatfyx khj iio","gpnhgabl qq qq fkeofjpc dflidshdb qq iio khj qq yzyoatfyx","tizpzhlbyb eget z rjstbhe iio jdh jdh iptxh qq rjstbhe","jtlghe wlinrrgcm jnkdbd k iio et rjstbhe iio qq jdh","yp fkeofjpc lkhypcebox rjstbhe ewwykishv egzhne jdh y qq qq","fu ql iio fkeofjpc jdh luspuy yzyoatfyx li qq v","wlinrrgcm iio qq omnc sgkt tzgev iio iio qq qq","d vhg qlj khj wlinrrgcm qq f jp zsmhkjokmb rjstbhe"), intArrayOf(96537918,589204657,765963609,613766496,43871615,189209587,239084671,908938263), 3)
}