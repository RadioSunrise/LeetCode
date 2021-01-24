// site: https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence/

// 求数组的最长连续递增子序列的长度

// 原地计算也行
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        // 原地计算
        int n = nums.length;
        if(n <= 1){
            return n;
        }
        int res = 1;
        // start是递增序列的开始位置
        int start = 0;
        for(int i = 0; i < n; i++){
            if(i > 0 && nums[i] <= nums[i - 1]){
                start = i;
            }
            // 挑战最大值
            // 当前LCS的长度是 i - start + 1
            res = Math.max(res, i - start + 1);
        }
        return res;
    }
}

// 一维dp
public class LongestContinuousIncreasingSubsequence {
    public int findLengthOfLCIS(int[] nums) {
        // dp实现
        int n = nums.length;
        if(n <= 1){
            return n;
        }
        // dp[i]表示以nums[i]结尾的最长的连续递增子序列
        int[] dp = new int[n];
        // 1个字符的时候是1
        dp[0] = 1;
        int res = 1;
        for(int i = 1; i < n; i++){
            if(nums[i] > nums[i - 1]){
                dp[i] = dp[i - 1] + 1;
            }
            else {
                dp[i] = 1;
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
