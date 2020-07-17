// site: binary-search

// 二分查找
// 主要是搜索区间的设定 -> 循环停止的判断 -> 区间的判断 -> right的收缩

// right 初始化为len-1
// 左闭右闭
class Solution {
    public int search(int[] nums, int target) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        // 左闭右闭区间
        // 停止条件是left = right + 1
        while(left <= right){
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            }
            else if (nums[mid] < target) {
                // 搜索区间 [mid + 1, right]
                left = mid + 1;
            }
            else if (nums[mid] > target) {
                // 搜搜区间 [left, mid - 1]
                right = mid - 1;
            }
        }
        return -1;
    }
}

// right 初始化为len
// 左闭右开
class Solution {
    public int search(int[] nums, int target) {
        int len = nums.length;
        int left = 0;
        int right = len;
        // 左闭右开区间
        // 停止条件是left == right
        while(left < right){
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            }
            else if (nums[mid] < target) {
                // 搜索区间 [mid + 1, right)
                left = mid + 1;
            }
            else if (nums[mid] > target) {
                // 搜搜区间 [left, mid)
                right = mid;
            }
        }
        return -1;
    }
}
