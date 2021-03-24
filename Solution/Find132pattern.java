// site: https://leetcode-cn.com/problems/132-pattern/

// 单调栈没搞懂，待学习

public class Find132pattern {
    public boolean find132pattern(int[] nums) {
        // 暴力法
        // 希望左边的数 1 小，右边的 3 比 1 大，但是小于 2
        // 枚举遍历 2，同时要更新比当前位置 j 小的最小的数字
        int n = nums.length;
        if(n < 3){
            return false;
        }
        // 左边最小值初始化为 nums[0]
        int numsi = nums[0];
        // 枚举位置 j
        for(int j = 1; j < n; j++){
            // 反向枚举位置 k
            for(int k = n - 1; k > j; k--){
                if(numsi < nums[k] && nums[j] > nums[k]){
                    return true;
                }
            }
            // 更新[0, j]的最小值
            numsi = Math.min(numsi, nums[j]);
        }
        return false;
    }
}
