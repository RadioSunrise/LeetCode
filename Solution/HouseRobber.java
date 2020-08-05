// site:  https://leetcode-cn.com/problems/house-robber/submissions/

// 打家劫舍问题第一题
// dp做法
// dp[i] 表示前i家(0, ..., i - 1)家最大价钱
// 因为不能连续偷两家，所以dp[i]的选择有两种
// 1.偷这一家(i-1)，再加上dp[i - 2](隔一家) = nums[i - 1] + dp[i - 2]
// 2.不偷这一家，等于dp[i - 1]
// 选1和2中比较大的情况
// 因为只跟前两个状态有关，所有用两个变量存着就可以了，不用开一个数组

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return nums[0];
        }
        int dpTwoAgo = 0;
        int dpOneAgo = nums[0];
        int res = 0;
        /*
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        
        for(int i = 2; i <= n; i++){
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
        }
        */
        for(int i = 2; i <= n; i++){
            res = Math.max(nums[i - 1] + dpTwoAgo, dpOneAgo);
            dpTwoAgo = dpOneAgo;
            dpOneAgo = res;
        }
        // return dp[n];
        return res;
    }
}
