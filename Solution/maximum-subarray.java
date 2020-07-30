// site: https://leetcode-cn.com/problems/maximum-subarray/submissions/

// 统计最大子序列和
// 贪心的思想，如果当前和sum大于0，说明对后续子序列和有贡献，否则舍去

class Solution {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int sum = 0;
        for(int num : nums){
            // 如果Sum > 0，继续累加
            if(sum > 0){
                sum += num;
            }
            else{
                // 否则sum等于当前数字
                // 从当前位置开始计算，如果当前数字小于0，那么在下一次循环也会被更新成下一个数字
                sum = num;
            }
            max = Math.max(sum, max);
        }
        return max;
    }
}

// 动态规划
// dp[i]为以第i个数字结尾的序列的最大子序列和
// 目的是求max{dp[i] | 0 <= i <= n - 1}
// 转移方程：dp[i] = max{dp[i-1] + nums[i], nums[i]}，即可以选择：1.之前的结果加上nums[i] 2.nums[i]单独成为1段
// dp[i]只和dp[i-1]有关，所以可以用一个int来代替dp table
// base case: dp[-1] = 0
class Solution {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int dp = 0;
        for(int num : nums){
            dp = Math.max(dp + num, num);
            System.out.println("now dp is " + dp);
            max = Math.max(dp, max);
        }
        return max;
    }
}
