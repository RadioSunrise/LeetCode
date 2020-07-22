// site: https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/

// 和153很像，但是要多处理nums[mid] 和 nums[right] 相等的情况
// 因为left <= mid < right（下取整求mid），所以当nums[mid] = nums[right]的时候，且nums[right]不唯一，则min必定在[left, right-1]之中
class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        // 左闭右闭区间，包住min，不断缩小
        // 退出条件定在left = right
        while(left < right){
            int mid = left + ((right - left) / 2);
            // 和153题一样，如果nums[mid] 和 nums[right]比较
            // nums[mid] < nums[right]说明min在左边,[left, mid]区间
            if(nums[mid] < nums[right]){
                right = mid;
            }
            // nums[mid] > nums[right]，说明min在右边
            // nums[mid]已经比nums[right]大了，所以必然不是最小值
            // 搜索区间[mid + 1, right]
            else if(nums[mid] > nums[right]){
                left = mid + 1;   
            }
            // 相等，则right--，减一个位置进行收缩
            else if(nums[mid] == nums[right]){
                right--;
            }
        }
        return nums[left];
    }
}
