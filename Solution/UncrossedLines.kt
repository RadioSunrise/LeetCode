// site: https://leetcode-cn.com/problems/uncrossed-lines/

class UncrossedLines {
    fun maxUncrossedLines(nums1: IntArray, nums2: IntArray): Int {
        // 最长公共子序列的变形
        // dp[i][j] 表示 nums1[:i] 和 nums[:j] 的最长公共子序列
        // nums1[:i] 长度为 i 的前缀
        // dp 数组多开一个位置， dp[0][j] = dp[i][0] 等于 0
        // dp[i][j] 对应的原位置是 nums1[i-1] 和 nums2[j - 1]

        // 转移方程
        // dp[i][j] = 
        // 1. nums1[i-1] == nums2[j-2] -> dp[i-1][j-1] + 1
        // 2. nums1[i-1] != nums2[j-2] -> max(dp[i-1][j], dp[i][j-1])
        val m = nums1.size
        val n = nums2.size
        val dp = Array(m + 1) { IntArray(n + 1) }
        // 遍历
        for (i in (1..m)) {
            for (j in (1..n)) {
                dp[i][j] = if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i - 1][j - 1] + 1
                } else {
                    Math.max(dp[i - 1][j], dp[i][j - 1])
                }
            }
        }
        return dp[m][n]
    }
}
