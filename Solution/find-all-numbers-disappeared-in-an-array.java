//site: https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/

// 原地操作的做法
// 在输入数组本身以某种方式标记已访问过的数字，然后再找到缺失的数字
// 在原数组上做标记，但是不能影响后序的判断
// 用负值就可以保留信息了
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        // 不使用额外空间的方法
        // 在原来的数组上做标记，用负值可以标记的同时保留原信息
        for(int i = 0; i < nums.length; i++){
            // nums[i]对于的位置为大于0，则改成负的
            int index = Math.abs(nums[i]) - 1;
            if(nums[index] > 0){
                nums[index] *= -1;
            }
        }
        // 遍历一遍后，出现过的数都将对应位置变成负数了
        for(int i = 1; i <= nums.length; i++){
            // 还是大于0说明没出现过
            if(nums[i - 1] > 0){
                res.add(i);
            }
        }
        return res;
    }
}
