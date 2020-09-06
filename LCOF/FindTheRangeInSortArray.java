// site: https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/submissions/

// 在排序数组中搜索目标数字出现的次数
// 二分查找两个边界

// 自己写的版本
class Solution {
    public int search(int[] nums, int target) {
        // 二分查找边界问题
        int n = nums.length;
        if(n == 0){
            return 0;
        }
        int left = leftRange(nums, target);
        // 没找到提前返回，也不用找右边界了
        if(left == -1){
            return 0;
        }
        int right = rightRange(nums, target);
        return right - left + 1;
    }

    /**
     * 排序数组nums中找target的左边界
     * @param nums
     * @param target
     * @return 返回左边界，target没有出现则返回-1
     */
    public int leftRange(int nums[], int target){
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        // while终止条件，left == right
        while(left < right){
            int mid = left + ((right - left) / 2);
            // 找左边界，等于m往左边缩
            // 下一轮搜索区间 [left, mid]
            if(nums[mid] >= target){
                right = mid;
            }
            // 下一轮搜索区间 [mid + 1, right]
            else {
                left = mid + 1;
            }
        }
        // 当前位置是不是target
        return ((nums[left] == target) ? left : -1);
    }

    /**
     * 排序数组nums中找target的右边界
     * @param nums
     * @param target
     * @return 返回右边界，target没有出现则返回-1
     */
    public int rightRange(int nums[], int target){
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        // while终止条件，left == right
        while(left < right){
            // let = mid，上取整
            int mid = left + ((right - left + 1) / 2);
            // nums[mid]大于target
            // 下一轮搜索区间[left, mid - 1]
            if(nums[mid] > target){
                right = mid - 1;
            }
            // nums[mid]小于等于target, left移动
            // 下一轮搜索区间[mid, right]
            else {
                left = mid;
            }
        }
        // 当前位置是不是target
        return ((nums[left] == target) ? left : -1);
    }
}
