// site: https://leetcode-cn.com/problems/max-consecutive-ones/

class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int count = 0;
        int max = 0;
        for(int i = 0; i < n; i++){
            if(nums[i] == 1){
                count++;
            } else {
                // 挑战最大值
                max = Math.max(max, count);
                count = 0;
            }
        }
        // 还要再比一次
        max = Math.max(max, count);
        return max;
    }
}
