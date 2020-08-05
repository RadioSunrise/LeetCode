// site: https://leetcode-cn.com/problems/house-robber-ii/

// 打家劫舍第二题，House是围成一圈的，不是第一题中一条直线，所以头(0号房屋)和尾(n-1)号房屋是相邻的
// 不能同时偷这两家，所以问题可以化为： 1.偷House[0] -- Hosue[n - 2]；2偷.House[1] 到 House[n - 1]
// 转化成第一题的解法

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return nums[0];
        }
        if(n == 2){
            return Math.max(nums[0], nums[1]);
        }
        // 因为House[0]和House[n - 1]是相邻的，所以这两家不能同时偷
        // 因此只能选择偷House[0] -- Hosue[n - 2] 或 House[1] 到 House[n - 1]
        // 这两种情况都是直线无循环的，用打劫1的方法做，最后取较大的

        // 1.从0到n-2里面偷
        int Max0 = robInLineArray(Arrays.copyOfRange(nums, 0, n - 1));
        
        // 2.从1到n-1里面偷
        int Max1 = robInLineArray(Arrays.copyOfRange(nums, 1, n));
        
        // 返回一个大的
        return Math.max(Max0, Max1);
    }
    /**
    * 将直线排列的打劫问题抽象出来一个函数
    */
    public int robInLineArray(int []nums){
        int curr = 0; // dp[i] 和 dp[i - 1]
        int prev = 0; // dp[i - 2]
        int temp;
        for(int num : nums){
            // 暂存curr
            temp = curr;
            curr = Math.max(prev + num, curr);
            prev = temp;
        }
        return curr;
    }
}

// 第一版写得比较复杂
// 可以把第一题的代码改得好一点
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return nums[0];
        }
        if(n == 2){
            return Math.max(nums[0], nums[1]);
        }
        // 因为House[0]和House[n - 1]是相邻的，所以这两家不能同时偷
        // 因此只能选择偷House[0] -- Hosue[n - 2] 或 House[1] 到 House[n - 1]
        // 这两种情况都是直线无循环的，用打劫1的方法做，最后取较大的

        // 1.从0到n-2里面偷
        int Max0 = 0;
        
        int dpTwoAgo = 0;
        int dpOneAge = nums[0];
        // 从House[0]偷到House[n-2]
        for(int i = 2; i <= n-1; i++){
            Max0 = Math.max(dpTwoAgo + nums[i - 1], dpOneAge);
            dpTwoAgo = dpOneAge;
            dpOneAge = Max0;
        }
        // 2.从1到n-1里面偷
        int Max1 = 0;
        dpTwoAgo = 0;
        dpOneAge = nums[1];
        // 从House[1]偷到House[n-1]
        for(int i = 3; i <= n; i++){
            Max1 = Math.max(dpTwoAgo + nums[i - 1], dpOneAge);
            dpTwoAgo = dpOneAge;
            dpOneAge = Max1;
        }
        return Math.max(Max0, Max1);
    }
}
