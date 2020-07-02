// site: https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/

class Solution {
    public static int maxSubArray(int[] nums) {
        int max = (int)Double.NEGATIVE_INFINITY;
        int index = 0;
        int sum = 0;
        int i;
        while(index < nums.length){
            for(i = index; i < nums.length; i++){
                sum += nums[i];
                max = sum > max ? sum : max;
                if(sum < 0){
                    sum = 0;
                    index = i + 1;
                    break;
                }
            }
            if(i == nums.length){
                break;
            }
        }
        return max;
    }
}

// 原地操作，如果从index开始往后加数字，出现小于0的情况，则对后续的子序列和没有帮助，所以index跳转到i+1。关键：退出条件
