// site: https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
// 整体思路：https://github.com/labuladong/fucking-algorithm/blob/master/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92%E7%B3%BB%E5%88%97/%E5%9B%A2%E7%81%AD%E8%82%A1%E7%A5%A8%E9%97%AE%E9%A2%98.md

// 

// 股票系列的第一题，可以看做交易次数k = 1
// 因此dp表可以简化，除去k=1的情况
// 二维dp表的写法，需要判断设置base_case，比较慢而且空间复杂度高
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n == 0){
            return 0;
        }
        int[][] dp = new int[n][2];
        for(int i = 0; i < n; i++){
            if(i - 1 == -1){
                dp[i][0] = 0;
                dp[i][1] = -1 * prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], -1 * prices[i]);
        }
        return dp[n-1][0];
    }
}

// 改进空间复杂度的写法，因为只需要前一组的数据，所以维护两个变量就可以了
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n == 0){
            return 0;
        }
        // int[][] dp = new int[n][2];
        // base case: dp[-1][0] = 0; dp[-1][1] = -Infinity
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            /*if(i - 1 == -1){
                dp[i][0] = 0;
                dp[i][1] = -1 * prices[i];
                continue;
            }*/
            // dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            // dp[i][1] = Math.max(dp[i-1][1], -1 * prices[i]);
            dp_i_1 = Math.max(dp_i_1, -1 * prices[i]);
        }
        return dp_i_0;
    }
}
