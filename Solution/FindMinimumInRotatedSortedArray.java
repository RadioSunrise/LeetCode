// site: https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/

// 寻找旋转排序数组的最小值，无重复

// 注意二分的条件和对比的元素，对比 nums[mid] 和 nums[right]

public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        // 旋转升序数组的二分实现
        // 对于数组最右侧的数 x，数组最小值右边的数都小于等于 x，左边的都大于 x
        int n = nums.length;
        int left = 0;
        int right = n - 1;

        // 旋转过的升序数组，需要判断区间是否存在非递增
        // 用 mid 和 right 比较
        // 最后的结果相当于找旋转点 
        // 结束条件 left == right
        while(left < right){
            // 右边收缩的时候为了不错过 right 的值，right = mid; 配合的则是 left = mid + 1
            // mid 是下取整
            int mid = left + (right - left) / 2;
            // 判断区间
            if(nums[mid] < nums[right]){
                // nums[mid] < nums[right]，则 [mid + 1, right] 不可能是最小
                // 下一轮的区间是 [left, mid]
                right = mid;
            } else {
                // nums[mid] > nums[right]
                // 说明[left, mid] 是旋转后的前段, [left, mid] 都不可能
                // 即下一轮的搜索区间[mid + 1, right]
                left = mid + 1;
            }
        }
        // 结束的时候 left = right，即最小值的位置
        return nums[left];
    }
}
