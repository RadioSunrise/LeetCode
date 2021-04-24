// site: https://leetcode-cn.com/problems/combination-sum-iv/

class Solution {
    fun combinationSum4(nums: IntArray, target: Int): Int {
        // 类似于背包问题，但不同的顺序也被当做不同的方案
        // 用 dp[i] 表示总和为 i 的选取方案的个数
        // 考虑选取方案中的最后一个数字 num, num 必定来自 nums，且 num <= i
        // 则对于元素和为(i - num)的方案，再加上 num 即为一个构成 i 的方案

        // 因此转移方程: dp[i] = Sigma(dp[i-num]), num ∈ nums
        // 边界条件: dp[0] = 1，最终结果是 dp[target]

        val dp = IntArray(target + 1)
        dp[0] = 1
        // 外层循环遍历综合值
        for (i in 1..target) {
            // 内层循环遍历 nums
            for (num in nums) {
                if (num <= i) {
                    dp[i] += dp[i - num]
                }
            }
        }
        // 因为内层循环考虑了整个 nums, 所以每一种顺序都会被考虑到
        return dp[target]
    }
}
