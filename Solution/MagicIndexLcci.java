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

// 利用非降序序列特性优化直接遍历
class Solution {
    public int findMagicIndex(int[] nums) {
        // 优化遍历
        int i = 0;
        while(i < nums.length){
            if(nums[i] == i){
                return i;
            }
            // 如果nums[i] 严格大于 i，则 i 跳到nums[i]
            // 因为序列是非递减序列，所以当nums[i] > i，魔法索引的位置必定不在[i, nums[i])内
            // 所以i直接跳转到nums[i]，减少必定不满足条件的判断
            if(nums[i] > i){
                i = nums[i];
            }
            else{
                i++;
            }

        }
        return -1;
    }
}
