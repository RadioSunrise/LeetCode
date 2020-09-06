// site: https://leetcode-cn.com/problems/chou-shu-lcof/

// 返回第n个丑数
// 动态规划的想法，每个丑数都是用比较小的丑数乘2/3/5得到的
// dp[i]是第i+1个丑数，三个  **因子指针** pos指的是2/3/5要乘多少
class Solution {
    public int nthUglyNumber(int n) {
        // 特判
        if(n <= 5){
            return n;
        }
        // 动态规划
        // 每个丑数都是用比较小的丑数乘2/3/5得到的，
        int[] dp = new int[n];
        dp[0] = 1;
        // 2,3,5的指针
        int pos2 = 0, pos3 = 0, pos5 = 0;
        for(int i = 1; i < n; i++){
            int n2 = dp[pos2] * 2;
            int n3 = dp[pos3] * 3;
            int n5 = dp[pos5] * 5;
            // 三种选择
            dp[i] = Math.min(Math.min(n2, n3), n5);
            // 判断选择了谁
            // 不用else if，因为可能会同时跟多个数相等
            // 指针前进，相当于维护三个有序队列，等价于乘数+1
            if(dp[i] == n2){
                pos2++;
            }
            if(dp[i] == n3){
                pos3++;
            }
            if(dp[i] == n5){
                pos5++;
            }
        }
        return dp[n - 1];
    }
}
