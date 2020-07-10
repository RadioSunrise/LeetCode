// site: https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/

// 还需要再学习这道题！！！
/*
class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if(n == 0){
            return 0;
        }
        // 如果k >= n/2, 跟k = +Infinity是一样的
        // 因为一次合理的交易至少要两天(一天买一天卖且卖出价大于买入价)
        if(k >= (n >>> 2))
        {
            int dp_i_0 = 0;
            int dp_i_1 = Integer.MIN_VALUE;
            int temp_i_0 = 0;
            for(int i = 0; i < n; i++){
                temp_i_0 = dp_i_0;
                dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
                dp_i_1 = Math.max(dp_i_1, temp_i_0 - prices[i]);
            }
            return dp_i_0;
        }
        // 否则需要遍历k
        // Need to read
        int[] T_ik0 = new int[k + 1];
        int[] T_ik1 = new int[k + 1];
        for(int i =0; i < k + 1; i++){
            T_ik1[i] = Integer.MIN_VALUE;
            T_ik0[i] = 0;
        }
        
        for (int price : prices) {
            for (int j = k; j > 0; j--) {
                T_ik0[j] = Math.max(T_ik0[j], T_ik1[j] + price);
                T_ik1[j] = Math.max(T_ik1[j], T_ik0[j - 1] - price);
            }
        }
        return T_ik0[k];
    }
}
*/

class Solution {
    public int maxProfit(int k, int[] prices) {
        if (k < 1 ) { return 0; }

        // k 超过了上限，也就变成了 无限次交易问题
         if (k > prices.length / 2) {
             return maxProfitOfII(prices);
         }
        // 状态定义
        int [][] dp = new int[k][2];

        // 初始化
        for (int i = 0; i < k; i++) {
            dp[i][0] = Integer.MIN_VALUE;
        }
        // 遍历每一天，模拟 k 次交易，计算并更新状态
        for (int p : prices) {

            dp[0][0] = Math.max(dp[0][0], 0 - p);                  // 第 1 次买
            dp[0][1] = Math.max(dp[0][1], dp[0][0] + p);           // 第 1 次卖

            for (int i = 1; i < k; i++) {
                dp[i][0] = Math.max(dp[i][0], dp[i - 1][1] - p);   // 第 i 次买
                dp[i][1] = Math.max(dp[i][1], dp[i][0] + p);       // 第 i 次卖
            }
        }
        return dp[k - 1][1];
    }

    // 解决无限次交易的方法
     public int maxProfitOfII(int[] prices) {
        int dp_i_0 = 0;
            int dp_i_1 = Integer.MIN_VALUE;
            int temp_i_0 = 0;
            for(int i = 0; i < prices.length; i++){
                temp_i_0 = dp_i_0;
                dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
                dp_i_1 = Math.max(dp_i_1, temp_i_0 - prices[i]);
            }
            return dp_i_0;
    }
}
