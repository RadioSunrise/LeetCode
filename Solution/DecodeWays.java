// site: https://leetcode-cn.com/problems/decode-ways/

// 解码方式，动态规划实现

// 一维数组dp
class DecodeWays {
    fun numDecodings(s: String): Int {
        // 动态规划实现
        // dp[i] 表示 s[1,...,i] 有多少种解码方法, i = [1,...,n]，对应的末尾字符是 s[i-1]
        // 考虑s[i-1]，两种情况
        // 1. s[i-1] 单独编码
        //    需要 s[i-1] != '0'，则 dp[i] = dp[i - 1]
        // 2. s[i-1] 和 s[i-2]共同编码
        //    需要 s[i-2] != '0' 且 10 * s[i-2] + s[i-1] <= 26, dp[i] = dp[i-2]
        // 则 dp[i] 为上述两种情况相加

        val n = s.length
        val dp = IntArray(n + 1)
        // 边界条件，字符为空当做一种解码方式
        dp[0] = 1
        for (i in (1..n)) {
            // 第一种情况
            if (s[i - 1] != '0') {
                dp[i] += dp[i - 1]
            }
            // 第二种
            if (i >= 2 && s[i - 2] != '0' && (10 * (s[i - 2] - '0') + (s[i - 1] - '0')) <= 26) {
                dp[i] += dp[i - 2]
            }
        }
        return dp[n]
    }
}

// 节省空间的滚动数组写法
class Solution {
    fun numDecodings(s: String): Int {
        // 动态规划实现
        // dp[i] 表示 s[1,...,i] 有多少种解码方法, i = [1,...,n]，对应的末尾字符是 s[i-1]
        // 考虑s[i-1]，两种情况
        // 1. s[i-1] 单独编码
        //    需要 s[i-1] != '0'，则 dp[i] = dp[i - 1]
        // 2. s[i-1] 和 s[i-2]共同编码
        //    需要 s[i-2] != '0' 且 10 * s[i-2] + s[i-1] <= 26, dp[i] = dp[i-2]
        // 则 dp[i] 为上述两种情况相加

        val n = s.length
        // 滚动数组
        // dpi2 = dp[i - 2], dpi1 = dp[i - 1], dpi = dp[i]
        // 边界条件，字符为空当做一种解码方式
        var dpi2 = 0
        var dpi1 = 1
        var dpi = 0
        for (i in (1..n)) {
            // dpi 置 0
            dpi = 0
            // 第一种情况
            if (s[i - 1] != '0') {
                dpi += dpi1
            }
            // 第二种
            if (i >= 2 && s[i - 2] != '0' && (10 * (s[i - 2] - '0') + (s[i - 1] - '0')) <= 26) {
                dpi += dpi2
            }
            dpi2 = dpi1
            dpi1 = dpi
        }
        return dpi
    }
}
