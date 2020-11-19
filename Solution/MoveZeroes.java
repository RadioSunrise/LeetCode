// site: https://leetcode-cn.com/problems/move-zeroes/submissions/

// 把0移动到数组的末尾，其他元素保持原来的排序

// 双指针交换法，比较慢
class Solution {
    public void moveZeroes(int[] nums) {
        // i是0元素的指针, j是非0元素的指针
        int i = 0;
        int j = 0;
        int n = nums.length;
        // 其中一个到末尾位为止
        while(i < n && j < n){
            // 找一个为0的
            while(nums[i] != 0){
                i++;
                if(i >= n){
                    return;
                }
            }
            // 从找一个不是0的
            j = i;
            while(nums[j] == 0){
                j++;
                if(j >= n){
                    return;
                }
            }
            nums[i] = nums[j];
            nums[j] = 0;
        }
    }
}

// 用一个指针指向目前找到的非0元素的下一个位置，把非0的元素放在这里
// lastNotZeroAt 用于指示下一个非0元素放哪里，并不会丢失信息
class Solution {
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        int lastNotZeroAt = 0;
        // lastNotZeroAt 用于指示下一个非0元素放哪里
        for(int i = 0; i < n; i++){
            if(nums[i] != 0){
                nums[lastNotZeroAt] = nums[i];
                lastNotZeroAt++;
            }
        }
        // 全部非0都扫过了一遍，lastNotZeroAt后面的位置全部置0
        for(int i = lastNotZeroAt; i < n; i++){
            nums[i] = 0;
        }
    }
}
