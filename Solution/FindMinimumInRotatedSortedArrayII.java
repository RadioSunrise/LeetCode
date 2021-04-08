// site: https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/submissions/

// 153 的重复版，注意重复的操作

class Solution {
    public int findMin(int[] nums) {
        // 和 153 相似，多了一个可能重复的条件
        // 比较的和循环的控制是一样的 nums[mid] 和 nums[right] 比较
        // 重复情况 nums[mid] == nums[right] 的处理是将 right 收缩一个位置
        // 缩减重复区间

        int n = nums.length;
        int left = 0;
        int right = n - 1;
        // left == right 的时候停止
        while(left < right){
            // 收缩的设置是 right = mid, left = mid + 1, mid 下取整
            int mid = left + (right - left) / 2;
            // nums[mid] < nums[right]，说明递增区间， [mid + 1, right] 都不可能
            // 下一轮是 [left, mid]
            if(nums[mid] < nums[right]){
                right = mid;
            } else if (nums[mid] > nums[right]){
                // nums[mid] > nums[right]，说明非递增区间， [left, mid] 都不可能
                // 下一轮是 [mid + 1, right]
                left = mid + 1;
            } else if (nums[mid] == nums[right]){
                // 出现重复了，right 收缩一个位置
                // 因为有代替品 nums[mid]，不会漏值，所以缩减重复区间
                right--;
            }
        }
        return nums[left];
    }
}
