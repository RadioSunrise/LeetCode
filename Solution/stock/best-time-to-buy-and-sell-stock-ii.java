// site: https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/

// 股票系列的第二题，因为无限次交易所有k和k-1是一样的
// 转移方程变为:
// dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
// dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
//             = max(dp[i-1][k][1], dp[i-1][k][0] - prices[i])

// 相当于k是不变的，所有遍历的时候也不用管k了，变成dp[i][0/1]就可以
// 在用两个变量代替dp表的时候，dp_i_0在第一个转移方程后是会变的，所以增加一个temp来暂存dp_i_0

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n == 0){
            return 0;
        }
        // base case
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;
        // 用于暂存dp[i-1][k][0]的值，因为用两个变量代替dp表会是dp_i_0变化
        int temp_i_0 = 0;
        for(int i = 0; i < n; i++){
            temp_i_0 = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, temp_i_0 - prices[i]);
        }
        return dp_i_0;
    }
}

// 因为k正无穷，所以也有其他的做法
// 波峰波谷法
// 因为可以无限次交易，所以每一段的波峰和波谷都要考虑
// 每一次都是波谷买入，波峰卖出，通过while循环来找
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n == 0){
            return 0;
        }
        int peak = prices[0];
        int valley = prices[0];
        int i = 0;
        int sum = 0;
        while(i < n - 1){
            // 找valley，直到prices[i+1]>price[i]
            while(i < n-1 && prices[i] >= prices[i + 1]){
                i++;
            }
            valley = i;
            // 继续出发，寻找波峰
            // 找peak，直到prices[i+1]<price[i]
            while(i < n - 1 && prices[i] <= prices[i + 1]){
                i++;
            }
            peak = i;
            sum += prices[peak] - prices[valley];
        }
        return sum;
    }
}

// 简单版的一遍扫描
// 因为可以连续买卖，所以把每一段上升线都加进去，考虑相邻的两个点累加就可以了
// 在斜坡上爬升并持续增加从连续交易中获得的利润，而不是在谷之后寻找每个峰值
// 并没有真正地买入和卖出，只是等价的做法
class Solution {
    public int maxProfit(int[] prices) {
        int sum = 0;
        // 把所有上升的段都加进去
        // i从1开始
        for(int i = 1; i < prices.length; i++){
            if(prices[i] > prices[i-1]){
                sum += prices[i] - prices[i-1]; //上升段都累加
            }
        }
        return sum;
    }
}
