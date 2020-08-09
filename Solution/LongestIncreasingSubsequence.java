// site: https://leetcode-cn.com/problems/longest-increasing-subsequence/

// 求最长上升子序列
// 经典dp定义，dp[i]表示以nums[i]为结尾的xxxxx
// 在这一题里面则是：dp[i]表示以nums[i]为结尾的最长上升序列，也表示了nums[i]必定被选取，所以dp[i]至少为1
// 转移方程: dp[i]可能由任何一个dp[j](nums[i] > nums[j])转移过来，所以
//           dp[i] = 1 + max(dp[j]) | nums[i] > nums[j] 

// 自己的写的第一版
// 时间复杂度O(n^2)
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if(n <= 1){
            return n;
        }
        // dp[i]表示nums[i]结尾的序列的上升序列长
        // 转移方程：dp[i] = 1 + max(dp[j]) | nums[i] > nums[j]
        // base case: dp[0] = 1;
        int[] dp = new int[n];
        dp[0] = 1;
        int max = 0;
        int currentMax;
        for(int i = 1; i < n; i++){
            currentMax = 0;
            for(int j = 0; j < i; j++){
                // nums[i]比nums[j]大，就有可能从dp[j]转移过来
                if(nums[i] > nums[j] && currentMax < dp[j]){
                    currentMax = dp[j];
                }
            }
            dp[i] = 1 + currentMax;
            max = Math.max(dp[i], max);
        }
        return max;
    }
}

// 待添加O(n log n) 
