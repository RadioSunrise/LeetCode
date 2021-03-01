// site: https://leetcode-cn.com/problems/range-sum-query-immutable/

// 前缀和的应用

// 计算好前缀和和快一点

// 新版本，前缀和数组多开一个位置，更加方便，不用把原数组存起来的
public class RangeSumQueryImmutable {

    /**
    * 前缀和数组
    */ 
    private int[] preSum;

    public NumArray(int[] nums) {
        int len = nums.length;
        // 计算前缀和
        // preSum[i + 1] = nums[0] + ... + nums[i]
        // preSum[i + 2] = nums[0] + ... + nums[i] + nums[i + 1] = preSum[i] + nums[i+1]
        preSum = new int[len + 1];
        // preSum[i + 1] = nums[0] + ... + nums[i]
        for(int i = 0; i < len; i++){
            preSum[i + 1] = preSum[i] + nums[i];
        }
    }
    
    public int sumRange(int i, int j) {
        // 以及计算好前缀和了
        // nums[i] + ... + nums[j] = preSum[j + 1] - preSum[i]
        return preSum[j+1] - preSum[i];
    }
}

// 旧版本
class NumArray {

    /**
    * 数组
    */ 
    private int[] nums;
    /**
    * 前缀和数组
    */ 
    private int[] preSum;

    public NumArray(int[] nums) {
        this.nums = nums;
        int len = nums.length;
        // 计算前缀和
        // preSum[i] = nums[0] + ... + nums[i]
        // preSum[i + 1] = nums[0] + ... + nums[i] + nums[i + 1] = preSum[i] + nums[i+1]
        preSum = new int[len];
        int sum = 0;
        for(int i = 0; i < len; i++){
            preSum[i] = sum + nums[i];
            sum = preSum[i];
        }
    }
    
    public int sumRange(int i, int j) {
        // 以及计算好前缀和了
        // nums[i] + ... + nums[j] = preSum[j] - preSum[i] + nums[i]
        return preSum[j] - preSum[i] + nums[i];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
