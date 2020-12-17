// site: https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/

// 股票系列的 k = +Infinity with fee
// 和k = +Infinity一样的转移方程，在buy和sell中选择一个减去fee就可以了

class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        if(n == 0){
            return 0;
        }
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;
        int temp_i_0 = 0;
        for(int i = 0; i < n; i++){
            temp_i_0 = dp_i_0;
            // 买和卖的其中一个减去fee就可以了
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, temp_i_0 - prices[i] - fee);
        }
        return dp_i_0;
    }
}

// 2020-12-17
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        // base case
        // dp[0][0] = 0, dp[0][1] = -price[0] (第0天已经持有股票)
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for(int i = 1; i < n; i++){
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        // 最后一天手上没股票是最大的利润
        return dp[n - 1][0];
    }
}
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        // 一维dp
        int dp_0 = 0;
        int dp_1 = -prices[0];
        for(int i = 0; i < n; i++){
            int temp = dp_0;
            dp_0 = Math.max(dp_0, dp_1 + prices[i] - fee); 
            dp_1 = Math.max(dp_1, temp - prices[i]);
        }
        return dp_0;
    }
}
