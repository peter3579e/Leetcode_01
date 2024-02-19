package com.android.leetcode.array

object ValidateIPAddress {
    fun validateIPv4(IP: String): String {
        val nums = IP.split(".").toTypedArray()
        for (x in nums) {
            // Validate integer in range (0, 255):
            // 1. length of chunk is between 1 and 3
            if (x.length == 0 || x.length > 3) return "Neither"
            // 2. no extra leading zeros
            if (x[0] == '0' && x.length != 1) return "Neither"
            // 3. only digits are allowed
            for (ch in x.toCharArray()) {
                if (!Character.isDigit(ch)) return "Neither"
            }
            // 4. less than 255
            if (x.toInt() > 255) return "Neither"
        }
        return "IPv4"
    }

    fun validateIPv6(IP: String): String {
        val nums = IP.split(":".toRegex()).toTypedArray()
        val hexdigits = "0123456789abcdefABCDEF"
        for (x in nums) {
            // Validate hexadecimal in range (0, 2**16):
            // 1. at least one and not more than 4 hexdigits in one chunk
            if (x.length == 0 || x.length > 4) return "Neither"
            // 2. only hexdigits are allowed: 0-9, a-f, A-F
            for (ch in x.toCharArray()) {
                if (hexdigits.indexOf(ch!!) == -1) return "Neither"
            }
        }
        return "IPv6"
    }

    fun validIPAddress(IP: String): String {
        return if (IP.filter { ch: Char -> ch == '.' }.count() == 3) {
            validateIPv4(IP)
        } else if (IP.filter { ch: Char -> ch == ':' }.count() == 7) {
            validateIPv6(IP)
        } else "Neither"
    }
    /*
    IPV4
    length != 0
    x1 is digit
    "x1.x2.x3.x4" where 0 <= xi <= 255
    valid 192.168.1.0
    in valid 192.168.1.00
    IPV6
    "x1:x2:x3:x4:x5:x6:x7:x8" where 1 <= xi.length <= 4
    2001:0db8:85a3::8A2E:037j:7334 length is < 1
    02001:0db8:85a3:0000:0000:8a2e:0370:7334 invalid length exceed 4

    STEP:
    Check IPV4
    var array = queryIP.split('.')
    for array
      if (element isNotLetterOrDigit || element.toInt < 0 || element > 255) return false
      if(element < 0 || element > 255) return false
*/
    fun validIPAddress2(IP: String): String {
        return if (IP.filter { it == '.' }.count() == 3) {
            isVaildIPV4(IP)
        }else if (IP.filter { it == ':' }.count() == 7) {
            isVaildIPV6(IP)
        }else {
            "Neither"
        }
    }

    fun isVaildIPV4(string: String) : String {
        var arr = string.split(".")
        for (s in arr) {
            if (s.length == 0 || s.length != 1 && s[0] == '0' || s.length > 3) return "Neither"
            s.forEach {
                if(!it.isDigit()) return "Neither"
            }
            if (s.toInt() < 0 || s.toInt() > 255) return "Neither"
        }
        return "IPv4"
    }
    /*
        IPV6
    "x1:x2:x3:x4:x5:x6:x7:x8" where 1 <= xi.length <= 4
    2001:0db8:85a3::8A2E:037j:7334 length is < 1
    02001:0db8:85a3:0000:0000:8a2e:0370:7334 invalid length exceed 4
     */

    fun isVaildIPV6(string: String): String {
        var list = string.split(":")
        var map = hashMapOf<Char,Int>(
            '0' to 1,
            '1' to 1,
            '2' to 1,
            '3' to 1,
            '4' to 1,
            '5' to 1,
            '6' to 1,
            '7' to 1,
            '8' to 1,
            '9' to 1,
            'a' to 1,
            'b' to 1,
            'c' to 1,
            'd' to 1,
            'e' to 1,
            'f' to 1,
            'A' to 1,
            'B' to 1,
            'C' to 1,
            'D' to 1,
            'E' to 1,
            'F' to 1
        )
        for (s in list) {
            if (s.isEmpty() || s.length > 4) return "Neither"
            s.forEach {
                if(map[it] == null) return "Neither"
            }
        }
        return "IPv6"
    }
}

fun main() {
    print(ValidateIPAddress.validIPAddress2("20EE:FGb8:85a3:0:0:8A2E:0370:7334"))
}