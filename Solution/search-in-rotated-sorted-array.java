// site: https://leetcode-cn.com/problems/search-in-rotated-sorted-array

// 旋转排序数组的二分查找

// 直接二分查找法

// 分两种情况，根据判断nums[mid]和nums[left]、nums[right]的关系判断左边[left, mid]是不是有序的
// 1. 左边有序序列，根据nums[mid]和target判断哪边收缩 -> (1)当nums[mid] >= target >= nums[left]的时候，右边才会收缩，其他情况是左边收缩
//    否则一个比nums[mid]小的数，是可能在mid左边也可能在mid右边的

// 2. 右边有序（1中判断左边非有序那么右边就肯定有序） -> 当 mid[right] >= target > nums[mid]，能够确定在目标右边，左边才会收缩，其他情况是右边收缩
//    否则一个比nums[mid]大的数，是可能在mid右边也可以在左边的
class Solution {
    public int search(int[] nums, int target) {
        int len = nums.length;
        if(len == 0){
            return -1;
        }
        int left = 0;
        int right = len - 1;
        // 闭区间
        while(left <= right){
            int mid = left + ((right - left) / 2);
            // nums[mid] 找到
            if(nums[mid] == target){
                return mid;
            }
            // 判断两边哪边有序
            // [left, mid]有序
            if (nums[left] <= nums[mid]) {
                // 根据nums[mid]和target的大小关系
                // 当 nums[mid] > target >= nums[left]才会在左边
                if(nums[mid] > target && target >= nums[left]) {
                    right = mid - 1;
                }
                else {
                    // nums[mid] > target
                    left = mid + 1;
                }
            }
            // [left, mid]非有序，[mid, right]有序
            else {
                // 右边有序，当 nums[right] >= target > nums[mid]才会在右边
                if(nums[mid] < target && target <= nums[right]){
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}
