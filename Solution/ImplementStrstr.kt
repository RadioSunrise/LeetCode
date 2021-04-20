// site: https://leetcode-cn.com/problems/implement-strstr/submissions/

// KMP实现 indexOf
// 注意匹配串和模式串的长度为0时的返回值

ImplementStrstr Solution {
    fun strStr(haystack: String, needle: String): Int {
        val hLen = haystack.length
        val nLen = needle.length
        if (hLen == 0) {
            return if (nLen == 0) 0 else -1
        }
        if (nLen == 0) {
            return 0
        }
        // next 数组
        val next = IntArray(nLen)
        getNext(needle, next)
        // 遍历
        var i = 0
        var j = 0
        while (i < hLen && j < nLen) {
            // 字符相等则共同前进
            if (j == -1 || haystack[i] == needle[j]) {
                i++;
                j++;
            } else {
                // j != -1 且 字符匹配失败，i不变，j跳到next[j]
                j = next[j]
            }
        }
        // while 循环退出之后，有一个走到末尾了
        if (j == nLen) {
            return i - j
        }
        return -1
    }

    /**
    * 设置next数组
    */
    fun getNext(pattern: String, next: IntArray) {
        // 首位置0，表示最后的返回位置
        val patternLen = pattern.length
        next[0] = -1
        var k = -1
        var j = 0
        while (j < patternLen - 1) {
            // p[k]表示前缀，p[j]表示后缀
            if (k == -1 || pattern[k] == pattern[j]) {
                k++
                j++
                // 之前的next[j]就是k，next[j+1] = next[j] + 1 = k + 1
                next[j] = k
            } else {
                k = next[k]
            }
        }
    }
}
