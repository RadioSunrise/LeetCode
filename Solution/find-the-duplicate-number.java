// https://leetcode-cn.com/problems/find-the-duplicate-number/

// 用二分法找重复数字，思路见注释
class Solution {
    public int findDuplicate(int[] nums) {
        // 二分法查找
        // 因为重复数字的范围是知道的，先猜一个数mid，统计原数组中小于等于mid的数的个数cnt
        // 如果cnt严格大于mid,则说明重复数字在[left, mid]中
        // 原因：如果没有重复数字出现，小于等于mid的数的个数应该是mid (1, 2 ,..., mid)
        // 所以重复数字的范围在[left, mid]
        int len = nums.length;
        int left = 1;
        int right = len - 1;
        // 结束条件left = right
        while(left < right){
            int mid = left + ((right - left) / 2);
            // 统计nums中 小于等于 mid 的个数cnt，如果严格大于则收缩右边界
            int cnt = countSmallerOrEqual(nums, mid);
            if(cnt > mid){
                // 下一轮搜索区间是[left, mid]，因为mid也有可能是重复数字
                right = mid;
            }
            else {
                // mid肯定不是重复数字，所以搜索区间可以跳过
                // 下一轮搜索区间[mid + 1, right]
                left = mid + 1;
            }
        }
        return left;
    }
    public int countSmallerOrEqual(int[] nums, int target){
        int count = 0;
        for(int num : nums){
            if(num <= target){
                count ++;
            }
        }
        return count;
    }
}

