// site: https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/

// 二分查找，找到有序数组中某个数target的左右边界，用两个不同的二分查找来找

// mid需不需要上取整取决于有没有left = mid，如果有的话 mid = left + ((right - left + 1) >> 1)
// 否则 mid = left + (right- left) >> 1

// 判断的时候可以分开三种情况判断，等于、小于、大于，三种情况的移动可以不同，根据具体问题来判断边界的收缩，区间都是闭区间
// 三种收缩的方式是可以不同的

class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums.length == 0){
            return new int[] {-1, -1};
        }
        int left = findLeftEdge(nums, target);
        if(left == -1){
            return new int[] {-1, -1};
        }
        // left 能找到right一定可以找到的
        int right = findRightEdge(nums, target);
        return new int[]{left, right};
    }
    /**
    * 找左边界
    */
    public int findLeftEdge(int[] nums, int target){
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while(left < right){
            int mid = left + ((right - left) >> 1);
            // 找到了，找的是左边界，还是要继续向左移动，下一轮[left, mid]
            if(nums[mid] == target){
                right = mid;
            }
            // 小于则必定不是解，下一轮[mid + 1, right]
            else if(nums[mid] < target){
                left = mid + 1;
            }
            // 大于，右边界收缩，且mid一定不对，下一轮[left, mid - 1]
            else if(nums[mid] > target){
                right = mid - 1;
            }
        }
        // 判断有没有找到
        if(nums[left] != target){
            return -1;
        }
        else {
            return left;
        }
    }
    /**
    * 找右边界
    */
    public int findRightEdge(int[] nums, int target){
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while(left < right){
            int mid = left + ((right - left + 1) >> 1);
            // 找到了，找右边界，所以往右走，下一轮[mid, right]
            if(nums[mid] == target){
                left = mid;
            }
            // 小于，往右边走，下一轮[mid + 1, right]
            else if(nums[mid] < target){
                left = mid + 1;
            }
            // 大于，往左走，下一轮[left, mid - 1]
            else if(nums[mid] > target){
                right = mid - 1;
            }
        }
        // 能进这个函数说明一定是找得到的target的，所以可以不用判断找不找得到
        if(nums[left] != target){
            return -1;
        }
        else {
            return left;
        }
    }
}
