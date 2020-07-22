// site: https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/

// 在翻转数组中找最小的数，用二分查找的想法，两个边界缩减，最后用left和right区间夹住最小值
// 通过nums[mid]和右边界值nums[right]的比较来判断
// 左闭右闭区间
// 左中右三者的大小关系有几种情况：
// 1.nums[left] < nums[mid] < nums[right]，那么min在左边，搜素区间[left, mid]
// 2.nums[left] > nums[mid], nums[mid] < [right]，min在左边[left, mid]
// 3.nums[left] < nums[mid], nums[mid] > nums[right]，min在右边[mid + 1, right]，因为mid比right大，所以下一轮的搜索区间不包括mid，从mid+1开始
// 4.nums[left] > nums[mid] > nums[right]，单调递减，不可能发生
// 整合可能发生的前3中情况，发现：如果用nums[left] 和 nums[mid]比大小，不能唯一判断最小值在哪边
// 用nums[mid] 和 nums[right] 比较，如果mid小则min在左边，否则mid在右边，可以唯一判断
// 实现：细节有终止条件，搜索区间
class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        // 左闭右闭区间
        // 循环终止条件left = right
        while(left < right){
            // 下取整mid
            int mid = left + ((right - left) / 2);
            // 三种情况可以变成nums[mid] 和 nums[right]的比较
            // 如果nums[mid] < nums[right]，则min在[left, mid]中
            if(nums[mid] < nums[right]) {
                // 最后mid也能是min值，所示right收缩到mid
                // 搜索区间[left, mid]
                right = mid;
            }
            // 这题里面不存在重复数字，所以nums[mid] 和 nums[right]不会相等
            else if(nums[mid] > nums[right]) {
                // 这种情况下mid毕竟不是最小值
                // 所以搜索区间是[mid + 1, right]
                left = mid + 1;
            }
        }
        // 结束的时候mid = left = right
        return nums[left];
    }
}


// 原数组：sortA + sortB，且整体有序(A < B)
// 旋转之后：sortB + sortA

// 自己写的：判断比较麻烦，分为三个部分，在B和在A都判断，比较复杂，容易错
class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        // 左闭右闭区间
        while(left < right){
            int mid = left + ((right - left) / 2);
            // nums[mid]小于nums[left]说明在被翻转段
            if(nums[mid] < nums[left]){
                right = mid;
            }
            // 说明在未翻转段，left收缩
            else if(nums[mid] > nums[right]){
                left = mid + 1;
            }
            // 在有序序列中，缩减右边界
            else if(nums[mid] >= nums[left] && nums[mid] <= nums[right] ){
                right = mid;
            }
        }
        return nums[left];
    }
}
