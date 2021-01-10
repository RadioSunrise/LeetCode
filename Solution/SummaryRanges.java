// https://leetcode-cn.com/problems/summary-ranges/submissions/

// 汇总区间，注意low和high的选择以及i的移动，low赋值之后i++要前进

class Solution {
    public List<String> summaryRanges(int[] nums) {
        int n = nums.length;
        List<String> res = new LinkedList<>();
        int i = 0;
        while(i < n){
            int low = i;
            i++;
            // 当相邻数字的差大于1则划分区域
            while(i < n && nums[i] == nums[i - 1] + 1){
                i++;
            }
            // high = i - 1 回到前一个满足条件的i
            int high = i - 1;
            if(low != high){
                res.add(nums[low] + "->" + nums[high]);
            }
            else {
                res.add(nums[low] + "");
            }
        }
        return res;
    }
}
