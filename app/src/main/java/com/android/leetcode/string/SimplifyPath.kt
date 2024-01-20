package com.android.leetcode.string

object SimplifyPath {
    //fail of not fully understand the question
    fun simplifyPath2(path: String): String {

        var ans = StringBuilder()

        var previous = StringBuilder()

        for(i in 0..path.length-1) {
            if(path[i] == '/') previous.append(i)
            if(previous[0] == path[i]) {
                previous.clear()
                continue
            }
            if(path[i] == '.') {
                continue
            }
            ans.append(path[i])
        }

        return ans.toString()
    }

    // Time complexity is O(N) where N is the length of array after removing the /
    // Space complexity is O(N) where N is the length of array after removing the /
    fun simplifyPath(path: String): String {
        var array = mutableListOf<String>()

        for(i in path.split('/')) {
            if (i == ".") {
                continue
            }else if (i == "..") {
                if(array.isNotEmpty()) array.removeAt(array.size-1)
                continue
            } else if (!i.isNullOrBlank()) {
                array.add(i)
            }
        }
        return array.joinToString(prefix = "/", separator = "/")
    }
    /*
    * Time Complexity:
The primary operation that contributes to the time complexity is the loop that iterates through each component obtained by splitting the input string (path.split('/')).
The loop has a time complexity of O(n), where n is the length of the input path string.
Other operations within the loop, such as adding, removing, or checking elements in the array, are constant time or involve checking if the array is empty.
Space Complexity:
The space complexity is primarily determined by the additional space used to store the components in the array.
The size of the array is directly proportional to the number of non-empty components in the path.
In the worst case, when the path does not contain unnecessary components, the space complexity is O(m), where m is the number of non-empty components.
The split function also creates an intermediate list of strings during the split operation, contributing to additional space usage.
In summary:

Time Complexity: O(n)
Space Complexity: O(m), where m is the number of non-empty components in the simplified path.
The joinToString operation at the end has a time complexity of O(m), where m is the number of components in the simplified path, but it doesn't affect the overall big-O analysis since it's linear with respect to the size of the simplified path.
* */
}

fun main() {
    SimplifyPath.simplifyPath("/a/./b/../../c/")
}