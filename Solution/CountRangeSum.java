// site: https://leetcode-cn.com/problems/count-of-range-sum/

// 找出数组中区间和在某个范围内的个数

// 只有O(n^2)的暴力法，待增加好一点的做法
class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        // 暴力法n^2
        int count = 0;
        int n = nums.length;
        for(int i = 0; i < n; i++){
            long sum = 0;
            for(int j = i; j < n; j++){
                sum += nums[j];
                if(sum <= upper && sum >= lower){
                    count++;
                }
            }
        }
        return count;
    }
}
