// site: https://leetcode-cn.com/problems/maximum-average-subarray-i/

public class MaximumAverageSubarrayI {
    /**
    * 滑动窗口实现
    */ 
    public double findMaxAverage(int[] nums, int k) {
        // maxSum最大的子数组元素和, res = maxSum / k
        int maxSum = 0;
        int n = nums.length;
        // 累加用的变量
        int sum = 0;
        // 统计第一个长为k的子数组的元素和
        for(int i = 0; i < k; i++){
            sum += nums[i];
        }
        // 滑动窗口，出去一个进来一个
        maxSum = sum;
        for(int i = k; i < n; i++){
            // 新的子数组以i结尾
            sum = sum - nums[i - k] + nums[i];
            maxSum = Math.max(sum, maxSum);
        }
        return ((double)(maxSum) / k);
    }
}
