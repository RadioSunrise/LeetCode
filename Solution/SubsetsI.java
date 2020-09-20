// https://leetcode-cn.com/problems/subsets/

// 求给定数组nums的全部子集，用生成mask的方式来求, nums中无重复数字
// mask的范围从0到(2^n - 1)，用&根据二进制位来把数字加入子集

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        int n = nums.length;
        if(n == 0){
            return res;
        }
        int total = (int)Math.pow(2, n);
        for(int mask = 0; mask < total; mask++){
            int temp = mask;
            List<Integer> subset = new LinkedList<>();
            int pos = 0;
            while(temp > 0){
                if((temp & 1) == 1){
                    subset.add(nums[pos]);
                }
                temp = temp >>> 1;
                pos++;
            }
            res.add(subset);
        }
        return res;
    }
}
