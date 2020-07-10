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
