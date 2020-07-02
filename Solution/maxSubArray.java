// site: https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/

// 原地操作版
class Solution {
    public static int maxSubArray(int[] nums) {
        int max = (int)Double.NEGATIVE_INFINITY;
        int index = 0;
        int sum = 0;
        int i;
        while(index < nums.length){
            for(i = index; i < nums.length; i++){
                sum += nums[i];
                max = sum > max ? sum : max;
                if(sum < 0){
                    sum = 0;
                    index = i + 1;
                    break;
                }
            }
            if(i == nums.length){
                break;
            }
        }
        return max;
    }
}

// 原地操作，如果从index开始往后加数字，出现小于0的情况，则对后续的子序列和没有帮助，所以index跳转到i+1。关键：退出条件

// 动态规划版
class Solution {
    public static int maxSubArray(int[] nums) {
        int max = nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            dp[i] = nums[i] + Math.max(dp[i-1], 0);
            max = Math.max(dp[i], max);
        }
        return max;
    }
}
// dp数组的意思：dp[i]表示以nums[i]结尾的最大子序列和
// 转移方程：如果dp[i-1]小于0，则表示[0...i-1]的最大子序列和小于0，则dp[i-1] + nums[i] < nums[i]，所以dp[i] = nums[i] + max(dp[i-1], 0)
// base case: dp[i]只依赖与dp[i - 1]，所以base case是dp[0] = nums[0]
// 可以进一步优化空间复杂度，直留下两个数temp1和temp2记录dp[i-1]和dp[i]就可以了

public static int maxSubArray(int[] nums) {
        int max = nums[0];
        int temp1, temp2;
        temp1 = nums[0];
        for(int i = 1; i < nums.length; i++){
            temp2 = nums[i] + Math.max(temp1, 0);
            max = Math.max(temp2, max);
            temp1 = temp2;
        }
        return max;
    }
