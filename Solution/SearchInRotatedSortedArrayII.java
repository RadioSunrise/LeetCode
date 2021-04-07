// site: https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/

// 有重复情况的33题，需要缩减区间，不是完全的二分

public class SearchInRotatedSortedArrayII {
    public boolean search(int[] nums, int target) {
        // 二分
        // 这一题是有重复数字的，可能会 nums[left] = nums[mid] = nums[right]
        // 就不能简单分出是否有序区间了
        int n = nums.length;
        if(n == 0){
            return false;
        }
        int left = 0;
        int right = n - 1;
        // 结束条件是left > right
        while(left <= right){
            // 下取整
            int mid = left + ((right - left) / 2);
            if(nums[mid] == target){
                return true;
            }
            // 特殊判断，区间的两端和中间是否相等，相等则两边缩减1个位置，再新区间内继续二分
            if(nums[left] == nums[mid] && nums[mid] == nums[right]){
                left++;
                right--;
            } else if(nums[left] <= nums[mid]){
                // 判断区间 [left, mid] 是否有序
                // 在有序区间内
                // 当 nums[mid] > target >= nums[left]，转向左区间
                if(nums[mid] > target && nums[left] <= target){
                    right = mid - 1;
                } else {
                    // target > mid
                    left = mid + 1;
                }
            } else {
                // [left, mid] 非有序，则 [mid, right] 有序
                if(nums[mid] < target && target <= nums[right]){
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }
}
