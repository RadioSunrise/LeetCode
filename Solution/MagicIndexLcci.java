// site: https://leetcode-cn.com/problems/magic-index-lcci/

// 因为不是严格升序或者降序的，所以不能直接二分
// 用分治法来做
// 两边递归的细节：因为要找最下的魔术索引，所以先找递归左半边，再到中间，最后是右边
// 不要三个都找到再比较，首先是复杂，其次是如果左边找到了直接返回左边就可以了，不用递归后面的过程

class Solution {
    public int findMagicIndex(int[] nums) {
        return splitFind(nums, 0, nums.length - 1);
    }
    /**
    * 分治法找魔术索引
    */
    public int splitFind(int[] nums, int left, int right){
        // 退出条件
        if (left > right){
            return -1;
        }
        // 如果最大的数比左下标left还小 或者 最小的数比右下标right还大
        if (nums[right] < left || nums[left] > right){
            return -1;
        }
        int res = -1; 
        int mid = left + ((right - left) / 2);
        // 先找左边，因为要找最小的魔术索引，所以左边找到了就返回左边的
        int leftMagic = splitFind(nums, left, mid - 1);
        if(leftMagic != -1){
            return leftMagic;
        }
        if(mid == nums[mid]){
            res = mid;
            return mid;
        }
        int rightMagic = splitFind(nums, mid + 1, right);
        // 不管是不是-1都可以返回了
        return rightMagic;
    }
}
