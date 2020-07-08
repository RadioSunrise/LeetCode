// site: https://leetcode-cn.com/problems/single-number/submissions/

// 题目要求空间复杂度O(1)，用异或运算
// a ^ a = 0, a ^ 0 = 0, 且异或满足交换律和结合律
// 数组中线相同的元素异或之后等于0， 0 异或 唯一出现一次的元素，结果等于出现一次的元素

class Solution {
    public int singleNumber(int[] nums) {
        // 用异或
        int result = 0;
        for(int i : nums){
            result = result ^ i;
        }
        return result;
    }
}
