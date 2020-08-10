// site: https://leetcode-cn.com/problems/maximum-product-subarray/submissions/

// 最大连续乘积子数组

// 因为有负数的存在，所以需要考虑的不只是最大值，还有最小值（乘负数会导致大小关系反转）

// 通过给dp表增加一个维度来表示最大和最小值，和股票买卖的思想有点类似

// 第一版没有空间压缩的
class Solution {
    public int maxProduct(int[] nums) {
        // 连续，考虑用nums[i]为结尾的状态
        // 开始的想法：dp[i]表示以nums[i]为结尾的最大连续子数组的乘积，表示dp[i]的肯定要选的
        // 和一般的dp有点不同，因为负数*正数会把前面的最大值反而变成最小值
        // 当nums[i]为负数的时候，想要最大值则反而需要找最小值来相乘
        // 所以除了存最大值之外dp[]，还需要存多一个以nums[i]为结尾的最小值
        // 实现的方法可以是为dp表增加多一个维度,dp[i][0]表示最大值，而dp[i][1]表示最小值

        // 转移方程，根据nums[i]的正负来判断
        // 1. i >= 0，最大值为自己和前一步的最大值，最小值考虑自己和前一步的最小值
        // dp[i][0] = max(nums[i], dp[i-1][0] * nums[i])
        // dp[i][1] = min(nums[i], dp[i-1][1] * nums[i])
        // 2. i < 0，最大值是自己活着自己和前面最小值的乘积，最小值则是自己或者自己和前面最大的乘积
        // dp[i][0] = max(nums[i], dp[i-1][1] * nums[i])
        // dp[i][1] = min(nums[i], dp[i-1][0] * nums[i])

        // base case:
        // dp[0]的最大和最小都是自己即nums[0]
        int len = nums.length;
        if(len == 1){
            return nums[0];
        }
        int[][] dp = new int[len][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        // max的初始值定为dp[0]
        int max = dp[0][0];
        for(int i = 1; i < len; i++){
            if(nums[i] == 0){
                dp[i][0] = 0;
                dp[i][1] = 0;
            }
            else if(nums[i] > 0){
                dp[i][0] = Math.max(nums[i], dp[i - 1][0] * nums[i]);
                dp[i][1] = Math.min(nums[i], dp[i - 1][1] * nums[i]);
            }
            else if(nums[i] < 0){
                dp[i][0] = Math.max(nums[i], dp[i - 1][1] * nums[i]);
                dp[i][1] = Math.min(nums[i], dp[i - 1][0] * nums[i]);
            }
            // 挑战最大值
            max = Math.max(max, dp[i][0]);
        }
        return max;
    }
}

// 空间压缩版的
class Solution {
    public int maxProduct(int[] nums) {
        // 连续，考虑用nums[i]为结尾的状态
        // 开始的想法：dp[i]表示以nums[i]为结尾的最大连续子数组的乘积，表示dp[i]的肯定要选的
        // 和一般的dp有点不同，因为负数*正数会把前面的最大值反而变成最小值
        // 当nums[i]为负数的时候，想要最大值则反而需要找最小值来相乘
        // 所以除了存最大值之外dp[]，还需要存多一个以nums[i]为结尾的最小值
        // 实现的方法可以是为dp表增加多一个维度,dp[i][0]表示最大值，而dp[i][1]表示最小值

        // 转移方程，根据nums[i]的正负来判断
        // 1. i >= 0，最大值为自己和前一步的最大值，最小值考虑自己和前一步的最小值
        // dp[i][0] = max(nums[i], dp[i-1][0] * nums[i])
        // dp[i][1] = min(nums[i], dp[i-1][1] * nums[i])
        // 2. i < 0，最大值是自己活着自己和前面最小值的乘积，最小值则是自己或者自己和前面最大的乘积
        // dp[i][0] = max(nums[i], dp[i-1][1] * nums[i])
        // dp[i][1] = min(nums[i], dp[i-1][0] * nums[i])

        // base case:
        // dp[0]的最大和最小都是自己即nums[0]
        int len = nums.length;
        if(len == 1){
            return nums[0];
        }
        // 因为只用前一个位置的值，可以压缩空间
        int[] dp = new int[2];
        dp[0] = nums[0];
        dp[1] = nums[0];
        // max的初始值定为dp[0]
        int max = dp[0];
        for(int i = 1; i < len; i++){
            if(nums[i] == 0){
                dp[0] = 0;
                dp[1] = 0;
            }
            else if(nums[i] > 0){
                dp[0] = Math.max(nums[i], dp[0] * nums[i]);
                dp[1] = Math.min(nums[i], dp[1] * nums[i]);
            }
            else if(nums[i] < 0){
                int temp = dp[0];
                dp[0] = Math.max(nums[i], dp[1] * nums[i]);
                dp[1] = Math.min(nums[i], temp * nums[i]);
            }
            // 挑战最大值
            max = Math.max(max, dp[0]);
        }
        return max;
    }
}
