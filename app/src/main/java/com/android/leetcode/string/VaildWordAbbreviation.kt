package com.android.leetcode.string

object VaildWordAbbreviation {
    fun validWordAbbreviation(word: String, abbr: String): Boolean {

        var wordi = 0
        var abbrj = 0

        while(wordi < word.length && abbrj < abbr.length) {
            var charj = abbr[abbrj]
            var num = StringBuilder()
            while(charj.isDigit()) {
                num.append(charj)
                abbrj++
                if (abbrj > abbr.length - 1) {
                    break
                }else {
                    charj = abbr[abbrj]
                }
                if(!charj.isDigit()) break
            }
            if(num.isNotEmpty()) {
                var int = num.toString().toInt()
                if(int > word.length-wordi) return false
                wordi = wordi + int
                if (wordi > word.length-1 && abbrj > abbr.length-1) return true
                if(word[wordi] != abbr[abbrj]) return false
            }
            abbrj++
            wordi++
        }

        return true
    }

    fun validWordAbbreviation2(word: String, abbr: String): Boolean {
        if(abbr.length > word.length) return false

        var i = 0
        var j = 0
        /*
        ae2
         */

        while(i < word.length && j < abbr.length) {
            if(word[i] != abbr[j]) {
                if(!abbr[j].isDigit()) return false
                if(abbr[j] == '0') return false
                var temp = StringBuilder()
                while (abbr[j].isDigit()) {
                    temp.append(abbr[j])
                    if(j+1 > abbr.length-1 || !abbr[j+1].isDigit())  {
                        break
                    }
                    j++
                }
                var num = temp.toString().toInt()
                i = i + num - 1
                if(i > word.length-1 || i + 1 > word.length-1 && j+1 < abbr.length || i + 1 < word.length && j + 1 > abbr.length-1) return false
               if(j+1 < abbr.length && i+1 < word.length) {
                    if(word[i+1] != abbr[j+1]) return false
                }
            }

            i++
            j++
        }
        if (j < abbr.length) return false
        return true
    }
    // Optimized
    fun validWordAbbreviation3(word: String, abbr: String): Boolean {
        if(abbr.length > word.length) return false

        var i = 0
        var j = 0

        while(i < word.length && j < abbr.length) {
            if(word[i] != abbr[j]) {
                if(!abbr[j].isDigit()) return false
                if(abbr[j] == '0') return false
                var temp = StringBuilder()
                while (j < abbr.length && abbr[j].isDigit()) {
                    temp.append(abbr[j])
                    j++
                }
                j = j - 1
                var num = temp.toString().toInt()
                i = i + num - 1
            }

            i++
            j++
        }
        if (j != abbr.length || i != word.length) return false
        return true
    }
}

fun main() {
    VaildWordAbbreviation.validWordAbbreviation2("cccc", "c2ca")
}