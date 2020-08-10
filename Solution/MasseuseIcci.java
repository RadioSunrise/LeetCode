// site: https://leetcode-cn.com/problems/the-masseuse-lcci/

// 按摩师，其实跟打家劫舍1是同一道题，就是说法不同

// 两种dp的方式，一种是将接和不接当做第二个维度（类似股票和最大乘积数组）

// 第一种：dp[i][0]表示第i不接受预约，dp[i][1]表示第i天接收预约
// 转移方程：
// 1. 第i天不接，则昨天可以接也可以不接 -> dp[i][0] = max(dp[i - 1][0], dp[i - 1][1])
// 2. 第i天接，则昨天必定不能接 -> dp[i][1] = dp[i-1][0] + nums[i]
class Solution {
    public int massage(int[] nums) {
        // 二维dp，dp[i][j]表示[0, ..., i]天的最大值，第二维代表0/1分别代表第i天接和不接预约
        // 只和前一天的状态有关系
        // 1. 第i天不接，则昨天可以接也可以不接 -> dp[i][0] = max(dp[i - 1][0], dp[i - 1][1])
        // 2. 第i天接，则昨天必定不能接 -> dp[i][1] = dp[i-1][0] + nums[i]
        int n = nums.length;
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return nums[0];
        }
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for(int i = 1; i < n; i++){
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }
}

// 第二种，一维的dp
// 因为第i天可以接和不接
// 第i天比较这两种情况哪个大就可以了，可以不把接受或不接受额外多一个维度
// dp[i]的意义就变为，[0, ..., i]天中的最大值
// 转移方程：1.第i天接，则从dp[i-2]转移过来，因为dp[i-1]包含了i-1天接或者不接的情况
//           2.第i天不接，则从dp[i-1]转移，前i天和前i-1天是一样的, i-1天接或者不接都可以

class Solution {
    public int massage(int[] nums) {
        // 一维dp，dp[i]表示[0, ..., i]天中的最大值
        // dp[i] = max(dp[i - 2] + nums[i], dp[i - 1])，第一部分表示i天接，则从i-2转移
        // 第二部分表示i天不接，则等于[0, ..., i-1]的结果
        int n = nums.length;
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return nums[0];
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for(int i = 2; i < n; i++){
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[n - 1];
    }
}
