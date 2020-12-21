// https://leetcode-cn.com/problems/min-cost-climbing-stairs/

// 动态规划，注意最后要到的位置是末尾的下一个位置，所以dp数组共n+1个，返回第n 

// 空间O(n)的做法
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        // 动态规划
        // 第i个位置有两种选择，i-1 或者 i-2 来
        // dp[i]表示走到i需要的cost
        // dp[i] = min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2])
        int n = cost.length;
        // 最后要调到cost的后一个位置，所以是 n+1 的大小
        int[] dp = new int[n + 1];
        // base case
        dp[0] = 0;
        dp[1] = 0;
        for(int i = 2; i <= n; i++){
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        // 返回n
        return dp[n];
    }
}

// 空间O(1)的做法，用三个变量来代替
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        // 动态规划
        // 第i个位置有两种选择，i-1 或者 i-2 来
        // dp[i]表示走到i需要的cost
        // dp[i] = min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2])
        int n = cost.length;
        // 最后要调到cost的后一个位置，所以是 n+1 的大小
        // base case
        int dp_i_1 = 0;
        int dp_i_2 = 0;
        int dp_i = 0;
        for(int i = 2; i <= n; i++){
            dp_i = Math.min(dp_i_1 + cost[i - 1], dp_i_2 + cost[i - 2]);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        // 返回n
        return dp_i;
    }
}
