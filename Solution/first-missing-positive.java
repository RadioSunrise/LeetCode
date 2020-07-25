// site: https://leetcode-cn.com/problems/first-missing-positive/

// 原地哈希，把数组当成hash表，遍历数组，将每个nums[i]放到下标为nums[i]-1的位置，比如说遇到3，把它放到下标为2的位置
// 将原本下标为2的元素交换到数字3的位置，继续判断和交换

class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i++){
            // 如果这个数是在1到n的，且位置没有放对
            // 用while而不是if，因为交换回来的数字也不一定是正确位置，还需要继续交换
            while(nums[i] >= 1 && nums[i] <= n && nums[i] != nums[nums[i] - 1]){
                // 因为有1到n的限制，所以不会出界
                swap(nums, i, nums[i] - 1);
            }
        }
        // 循环退出之后，判断每个位置i的数是不是i + 1，如果不是则说明他是没有出现的第一个正数
        for(int i = 0; i < n; i++){
            if(nums[i] != i + 1)
            {
                return i + 1;
            }
        }
        // 前面的if都没有执行过，则返回n + 1
        return n + 1;
    }
    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
