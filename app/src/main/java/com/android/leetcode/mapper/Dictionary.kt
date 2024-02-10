package com.android.leetcode.mapper

import java.util.LinkedList
import java.util.Queue

object Dictionary {

    /*
    give a dictionary
    the data can consist
    Map <String, Any>

dict = {
            "Key1" : "1",
            "Key2" : {
                "a" : "2",
                "b" : "3",
                "c" : {
                    "d" : "3",
                    "e" : {
                        "" : "1"
                        "" : ""
                    }
                }
            }
        }



Map<String,String>

output: {
            "Key1" : "1",
            "Key2.a" : "2",
            "Key2.b" : "3",
            "Key2.c.d" : "3",
            "Key2.c.e" : "1"
        }

        STEPS:
        1. use dfs to go through each elements
        2. use for loop to go through each element at that level
        3. check if the value is String then return, if not trigger dfs

        if first time we append key if it is not empty
        if value is not string we append . key
        if key is not empty at the end need to remove the last added char
     */

    var ans = hashMapOf<String,String>()

    fun dictionaryDFS(map: Map<String,Any>, string: StringBuilder): Map<String,String> {

        var startLength  = string.length

        map.forEach{key,value ->
            if (string.isEmpty()) {
                string.append(key)
            } else if (key.isNotEmpty()) {
                string.append(".$key")
            }
            if (value !is String) {
                dictionaryDFS(value as Map<String,Any> , string)
            }else {
                ans[string.toString()] = value
            }
            if (key.isNotEmpty()) string.delete(startLength, string.length)
        }

        return ans
    }


//    dict = {
//        "Key1" : "1",
//        "Key2" : {
//            "a" : "2",
//            "b" : "3",
//            "c" : {
//            "d" : "3",
//            "e" : {
//            "" : "1"
//            "" : ""
//        }
//        }
//        }
//    }


}

fun main() {
    val data: MutableMap<String, Any> = mutableMapOf(
        "Key1" to "k1",
        "Key2" to mapOf(
            "a" to "ka",
            "b" to "kb",
            "c" to mapOf(
                "d" to "kd",
                "e" to mapOf(
                    "" to "ke123123123",
                    "" to "234"
                )
            )
        )
    )

    Dictionary.dictionaryDFS(data, StringBuilder()).forEach { key, value ->
        println("key is $key + value is $value")
    }
}