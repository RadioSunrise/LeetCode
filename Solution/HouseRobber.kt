// site: https://leetcode-cn.com/problems/house-robber/

// 打家劫舍 1 的 kotlin 实现

class HouseRobber {
    fun rob(nums: IntArray): Int {
        if (nums.size == 0){
            return 0
        }
        if (nums.size == 1){
            return nums[0]
        }
        // dp 实现
        // dp[i] 表示前 i 最多能偷多少
        // 转移方程: 每间房都有两种选择，偷 或者 不偷
        // dp[i] = dp[i - 1] 不偷，总金额和上一家一样
        // dp[i] = dp[i - 2] + nums[i] 偷，上一家不能偷
        // dp[0] = nums[0], dp[1] = max(dp[0], dp[1]) 前两家选一家来偷
        
        val len = nums.size
        val dp = IntArray(len) {0}
        dp[0] = nums[0]
        dp[1] = Math.max(nums[0], nums[1])
        for(i in 2 until len){
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i])
        }
        return dp[len - 1]
    }
}
