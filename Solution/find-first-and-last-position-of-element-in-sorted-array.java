// site: https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/

// 二分查找左右边界
// 使用左闭右开区间
// 初始化right = len，即搜索空间是[0, len)
// while循环是left < right，停止条件是left == right
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = leftEdgeSearch(nums, target);
        res[1] = rightEdgeSearch(nums, target);
        return res;
    }
    /**
    * 左边界搜索
    */
    public int leftEdgeSearch(int[] nums, int target){
        int len = nums.length;
        if(len == 0) {
            return -1;
        }
        int left = 0;
        int right = len;
        // 左闭右开搜索区间
        // 退出条件是left == right
        while(left < right) {
            int mid = left + ((right - left) >> 1);
            // 找到targrt
            // 搜索区间又边界收缩
            if(nums[mid] == target) {
                right = mid;
            }
            else if(nums[mid] < target){
                // 搜索区间 [mid + 1, right)
                left = mid + 1;
            }
            else if(nums[mid] > target){
                // 搜索区间 [left, mid)
                right = mid;
            }
        }
        // target比所有数都大
        if(left >= len){
            return -1;
        }
        // 没找到
        if(nums[left] != target){
            left = -1;
        }
        return left;
    }
    /**
    * 右边界搜索
    */
    public int rightEdgeSearch(int[] nums, int target){
        int len = nums.length;
        if(len == 0) {
            return -1;
        }
        int left = 0;
        int right = len;
        // 左闭右开区间
        // 终止条件 left == right
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target){
                // 找右边界，让搜索区间的左边界收缩
                left = mid + 1;
            }
            else if (nums[mid] < target) {
                // nums[mid]小，搜索区间[mid + 1, right)
                left = mid + 1;
            }
            else if (nums[mid] > target) {
                // 搜索区间 [left, mid)
                right = mid;
            }
        }
        // 如果target比所有值都小
        // 因为left指向的是右边界的下一个值，所以当left = 0表示没找到
        if(left <= 0) {
            // System.out.println("right is " + right + ", right > len, return - 1");
            return -1;
        }
        // 没找到，退出的时候left和right指向右边界的下一个值
        if(nums[left - 1] != target) {
            // System.out.println("right is " + right + ", not found");
            return -1;
        }
        return left - 1;
    }
}
