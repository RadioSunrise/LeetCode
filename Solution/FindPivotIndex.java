// site: https://leetcode-cn.com/problems/find-pivot-index/

class FindPivotIndex {
    public int pivotIndex(int[] nums) {
        // 统计总和，再从左到右判断
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        // 遍历
        int leftSum = 0;
        for(int i = 0; i < nums.length; i++){
            if(leftSum == (sum - nums[i] - leftSum)){
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }
}
