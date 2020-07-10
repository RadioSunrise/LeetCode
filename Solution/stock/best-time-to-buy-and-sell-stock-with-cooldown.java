// site: https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown

// 股票系列 k = +Infinity with cooldown
// 和k = +Infinity的差别在于buy之前的一天必定是rest
// 就是说转移方程2的buy选择要从i-2转移而来
// dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i]) // max(今天休息，今天买(至少2天前卖的))
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n == 0){
            return 0;
        }
        // base case
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;
        int temp_i_0 = 0;
        int dp_pre_i = 0; // dp[i-2][0]
        // 这i天要选择buy，昨天必定是rest，所以要从i-2转移来
        // 而rest[i] = sell[i-1]的
        for(int i = 0; i < n; i++){
            temp_i_0 = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_pre_i - prices[i]);
            dp_pre_i = temp_i_0;
        }
        return dp_i_0;
    }
}
