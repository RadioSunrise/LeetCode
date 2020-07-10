// site: https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/

// 股票问题3， k固定2次
// 之前的题目k是无穷或者1，所以可以简化掉，不需要对k进行遍历
// 这里的k=2，比较少所以不写遍历直接写就可以了，其中用了dp[i][0][0] = 0的base case
// 返回的是dp[i][k][0] 即 dp[i][2][0]
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n == 0){
            return 0;
        }
        int dp_i_1_0 = 0; int dp_i_1_1 = Integer.MIN_VALUE;
        int dp_i_2_0 = 0; int dp_i_2_1 = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            dp_i_1_0 = Math.max(dp_i_1_0, dp_i_1_1 + prices[i]);
            dp_i_1_1 = Math.max(dp_i_1_1, -1 * prices[i]); // 用dp[i][0][0] = 0的base case
            dp_i_2_0 = Math.max(dp_i_2_0, dp_i_2_1 + prices[i]);
            dp_i_2_1 = Math.max(dp_i_2_1, dp_i_1_0 - prices[i]);
        }
        return dp_i_2_0;
    }
}
