// site: https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof/

// 在排序数组中找没出现的数字，也是可以二分的

class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        // 特判，如果缺最后一个数，直接返回
        if(nums[n - 1] != n){
            return n;
        }
        int left = 0;
        int right = n - 1;
        // 终止条件left = right
        while(left < right){
            // 用的是下取整，所以mid是拿不到right的，因此之前的特判是处理缺失的数字是不是最后一个数
            int mid = left + ((right - left)) / 2;
            // 考虑不行的情况
            // nums[mid] != mid，那么mid以及mid右边的都是不符合的，要找的是不符合的最小的数
            // 下一轮的搜索区间[left, mid]
            if(nums[mid] != mid){
                right = mid;
            }
            // 如果mid = nums[mid]那么说明[left, mid]都是符合的，就不用找了
            // 下一轮的搜索区间[mid + 1, right]
            else {
                left = mid + 1;
            }
        }
        // left == right，指向的就是没有出现的那个数字
        return left;
    }
}
