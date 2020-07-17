// site: https://leetcode-cn.com/problems/search-insert-position/

// 二分查找/插入

// 二分查找的思想
// 关键是条件和最后的返回值
// 搜索区间是关键，让搜索区间来定high的值
class Solution {
    public int searchInsert(int[] nums, int target) {
        int high = nums.length - 1;
        int low = 0;
        int mid = -1;
        // 小于等于，是可以等于的
        // (low == high) -> low = high = mid
        while(low <= high){
            mid = low + ((high - low) >> 1);
            if (nums[mid] == target){
                return mid;
            }
            else if (nums[mid] < target){
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        // 返回low
        // low的范围是[0, ..., n]
        // high的范围则是[-1, ..., n-1]
        return low;
    }
}

// 另一种写法
// 结合查找到和找不到的情况
// 问题变成：在一个有序数组中找第一个大于等于 target 的下标
// result的初始值定为n，可以免去边界的判断
class Solution {
    public int searchInsert(int[] nums, int target) {
        int high = nums.length - 1;
        int low = 0;
        int mid = -1;
        int result = nums.length;
        while(low <= high){
            mid = low + ((high - low) >> 1);
            if (target <= nums[mid]){
                result = mid;
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return result;
    }
}
